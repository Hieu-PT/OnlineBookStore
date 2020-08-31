
package hieupt.daos;

import hieupt.conn.MyConnection;
import hieupt.dtos.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public BookDAO() {
    }
    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public List<BookDTO> searchBook(String name, double priceFrom, double priceTo, String category) throws Exception{
        List<BookDTO> result = null;
        BookDTO dto;
        String id, title, author, image;
        double price;
        int quantity;
        String first27Char;
        try{
            String sql = "Select ID, Title, Author, Price, Image, Quantity From tblBooks Where Title like ? AND (Price BETWEEN ? AND ?) AND Category like ? AND Status = 'Active' AND Quantity > 0";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
            preStm.setDouble(2, priceFrom);
            preStm.setDouble(3, priceTo);
            preStm.setString(4, "%" + category + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                id = rs.getString("ID");
                title = rs.getString("Title");
                author = rs.getString("Author");
                price = rs.getDouble("Price");
                image = rs.getString("Image");
                quantity = rs.getInt("Quantity");
                if(title.length() >= 27){
                    first27Char = title.substring(0,26) + "...";
                    title = first27Char;
                }
                dto = new BookDTO(id, title, author, price);
                dto.setImage(image);
                dto.setQuantity(quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public BookDTO findByPrimaryKey(String key) throws Exception {
        BookDTO dto = null;
        String title, author, category, image, description, status;
        double price;
        int quantity;
        Timestamp importDate;
        List<Integer> listQuantity = new ArrayList<>();
        String firstNChar;
        try{
            String sql = "Select Title, Author, Price, Category, ImportDate, Quantity, Image, Description, Status From tblBooks Where ID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, key);
            rs = preStm.executeQuery();
            if(rs.next()){
                title = rs.getString("Title");
                author = rs.getString("Author");
                price = rs.getDouble("Price");
                category = rs.getString("Category");
                importDate = rs.getTimestamp("ImportDate");
                quantity = rs.getInt("Quantity");
                image = rs.getString("Image");
                description = rs.getString("Description");
                status = rs.getString("Status");
                if(title.length() >= 40){
                    firstNChar = title.substring(0,39) + "...";
                    title = firstNChar;
                }
                dto = new BookDTO(key, title, author, category, image, description, status, price, quantity, importDate);
                for (int i = 1; i <= quantity; i++) {
                    listQuantity.add(i);
                }
                dto.setListQuantity(listQuantity);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public double getMaxPrice() throws Exception {
        double maxPrice = 0;
        try{
            String sql = "Select Price from tblBooks " +
                        "Group by Price " +
                        "Having Price >= ALL(Select Price From tblBooks)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if(rs.next()){
                maxPrice = rs.getDouble("Price");
            }
        } finally {
            closeConnection();
        }
        return maxPrice;
    }
    
    public boolean updateBook(BookDTO dto) throws Exception{
        boolean check = false;
        try{
            String sql = "Update tblBooks Set Title = ?, Author = ?, Price = ?, Category = ?, Quantity = ?, Image = ?, Description = ?, Status = ? Where ID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getTitle());
            preStm.setString(2, dto.getAuthor());
            preStm.setDouble(3, dto.getPrice());
            preStm.setString(4, dto.getCategory());
            preStm.setInt(5, dto.getQuantity());
            preStm.setString(6, dto.getImage());
            preStm.setString(7, dto.getDescription());
            preStm.setString(8, dto.getStatus());
            preStm.setString(9, dto.getId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean updateImage(String id, String image) throws Exception{
        boolean check = false;
        try{
            String sql = "Update tblBooks Set Image = ? Where ID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, image);
            preStm.setString(2, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean inActiveBook(String id) throws Exception{
        boolean check = false;
        try{
            String sql = "Update tblBooks Set Status = 'InActive' Where ID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean insertBook(BookDTO dto) throws Exception{
        boolean check = false;
        Calendar cal = Calendar.getInstance();
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        try{
            String sql = "Insert INTO tblBooks(ID, Title, Author, Price, Category, ImportDate, Quantity, Image, Description, Status) Values(?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getId());
            preStm.setString(2, dto.getTitle());
            preStm.setString(3, dto.getAuthor());
            preStm.setDouble(4, dto.getPrice());
            preStm.setString(5, dto.getCategory());
            preStm.setTimestamp(6, time);
            preStm.setInt(7, dto.getQuantity());
            preStm.setString(8, dto.getImage());
            preStm.setString(9, dto.getDescription());
            preStm.setString(10, "Active");
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<String> getListCategory() throws Exception{
        List<String> list = null;
        String category;
        try{
            String sql = "Select Category From tblBooks Where Category != 'Others' Group by Category";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                category = rs.getString("Category");
                list.add(category);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
