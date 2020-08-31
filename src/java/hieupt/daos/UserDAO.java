
package hieupt.daos;

import hieupt.conn.MyConnection;
import hieupt.dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public UserDAO() {
    }
    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public UserDTO checkLogin(String username, String password) throws Exception {
        UserDTO dto = null;
        String role;
        String name;
        try {
            String sql = "Select RoleName, Fullname From tblUsers JOIN tblRoles ON tblUsers.Role = tblRoles.RoleID"
                + " Where Username = ? and Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if(rs.next()){
                name = rs.getString("Fullname");
                role = rs.getString("RoleName");
                dto = new UserDTO(name, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
