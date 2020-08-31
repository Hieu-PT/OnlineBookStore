
package hieupt.daos;

import hieupt.conn.MyConnection;
import hieupt.dtos.DiscountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;

public class DiscountDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public DiscountDAO() {
    }

    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public DiscountDTO findByPrimaryKey(String key) throws Exception{
        DiscountDTO dto = null;
        int percent;
        try{
            String sql = "Select DiscountPercent From tblDiscountCodes Where ID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, key);
            rs = preStm.executeQuery();
            if(rs.next()){
                percent = rs.getInt("DiscountPercent");
                dto = new DiscountDTO(key, percent);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean createDiscountCode(DiscountDTO dto) throws Exception{
        boolean check = false;
        Calendar cal = Calendar.getInstance();
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        try{
            String sql = "Insert Into tblDiscountCodes(ID, DiscountPercent, CreateDate) Values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getID());
            preStm.setInt(2, dto.getDiscountPercent());
            preStm.setTimestamp(3, time);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean createDiscountRecord(String discountCode, int orderID, String username) throws  Exception{
        boolean check = false;
        try{
            String sql = "Insert Into tblDiscountCodeHistory(DiscountCode, OrderID, UserID) Values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountCode);
            preStm.setInt(2, orderID);
            preStm.setString(3, username);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean checkUsedCode(String username, String code) throws Exception{
        boolean check = false;
        try{
            String sql = "Select ID From tblDiscountCodeHistory Where DiscountCode = ? AND UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, code);
            preStm.setString(2, username);
            rs = preStm.executeQuery();
            if(rs.next()){
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
