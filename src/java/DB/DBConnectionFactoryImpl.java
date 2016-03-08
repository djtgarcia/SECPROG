package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactoryImpl extends DBConnectionFactory{

    @Override
    public Connection getConnection() {
        try {
            Class.forName(getDriverName());
            Connection conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            return conn;
        } catch (SQLException ex) {
        	System.out.println(ex);
//            Logger.getLogger(DBConnectionFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
        	System.out.println(ex);
//            Logger.getLogger(DBConnectionFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}
