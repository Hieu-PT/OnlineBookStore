
package hieupt.daos;

import hieupt.conn.MyConnection;
import hieupt.dtos.BookDTO;
import hieupt.dtos.OrderHistoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public CartDAO() {
    }
    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public int addNewOrders(String customerID, double total) throws Exception{
        int orderID = 0;
        Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
        try {
            String sql = "Insert into tblOrders(CustomerID, Total, OrderDate, PaymentStatus) values(?,?,?,?);SELECT SCOPE_IDENTITY() as OrderID;";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStm.setString(1, customerID);
            preStm.setDouble(2, total);
            preStm.setTimestamp(3, time);
            preStm.setString(4, "Unpaid");
            preStm.executeUpdate();
            rs = preStm.getGeneratedKeys();
            if (rs.next()) {
                orderID = rs.getInt("OrderID");
            }
        } finally {
            closeConnection();
        }
        return orderID;
    }
    
    public boolean addNewPromotionDetail(int orderID, HashMap<String, BookDTO> list) throws Exception{
        boolean check = false;
        try{
            String sql = "Insert into tblOrderDetails(OrderID, BookID, Price, Quantity, Total) Values(?,?,?,?,?)"
                    + " Update tblBooks Set Quantity = (Quantity - ?) Where ID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            for (Map.Entry<String, BookDTO> dto : list.entrySet()) {
                preStm.setInt(1, orderID);
                preStm.setString(2, dto.getKey());
                preStm.setDouble(3, dto.getValue().getPrice());
                preStm.setInt(4, dto.getValue().getCartQuantity());
                preStm.setDouble(5, dto.getValue().getTotal());
                preStm.setInt(6, dto.getValue().getCartQuantity());
                preStm.setString(7, dto.getKey());
                preStm.addBatch();
            }
            int[] result = preStm.executeBatch();
            check = result.length > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<OrderHistoryDTO> getOrderHistory(String name, String from, String to) throws  Exception{
        List<OrderHistoryDTO> list = null;
        OrderHistoryDTO dto;
        String sql;
        int id;
        String customerID, paymentStatus;
        double total;
        Timestamp orderDate;
        try{
            if(from.equals("") || to.equals(""))
                sql = "Select ID, CustomerID, Total, OrderDate, PaymentStatus From tblOrders Where CustomerID LIKE ?";
            else
                sql = "Select ID, CustomerID, Total, OrderDate, PaymentStatus From tblOrders Where CustomerID LIKE ? AND OrderDate BETWEEN '" + from + "' AND '" + to + "'";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                id = rs.getInt("ID");
                customerID = rs.getString("CustomerID");
                total = rs.getDouble("Total");
                orderDate = rs.getTimestamp("OrderDate");
                paymentStatus = rs.getString("PaymentStatus");
                dto = new OrderHistoryDTO(id, customerID, total, orderDate, paymentStatus);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean updatePaymentStatus(int id) throws Exception{
        boolean check = false;
        try{
            String sql = "Update tblOrders Set PaymentStatus = 'Paid' Where ID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public double getOrderTotal(int id) throws Exception {
        double total = 0;
        try{
            String sql = "Select Total From tblOrders Where ID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if(rs.next()){
                total = rs.getDouble("Total");
            }
        } finally {
            closeConnection();
        }
        return total;
    }
}
