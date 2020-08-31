
package hieupt.conn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MyConnection implements Serializable{
    public static Connection getMyConnection() throws NamingException, SQLException{
        Context context = new InitialContext();
        Context tomcatCtx = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatCtx.lookup("BookMNDB");
        
        Connection con = ds.getConnection();
        return con;
    }
}
