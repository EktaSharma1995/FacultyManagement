/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_5_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ektasharma
 */
public class DBUtil {
    private static Connection connection;
    
    private DBUtil(){
    }
    
    public static Connection getConnection() throws DBException {
        if(connection != null){
            return connection;
        } else {
            try {
                String url = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";
                String userName = "N01231869";
                String password = "oracle";
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(url, userName, password);
                connection.setAutoCommit(false);
                return connection;
            }
            
            catch(ClassNotFoundException cnfe) {
                throw new DBException(cnfe);
            } catch (SQLException sqle){
                throw new DBException(sqle);
            }
        }
    }
    
     public static void closeConnection() throws DBException {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBException(e);
            } finally {
                connection = null;
            }
        }
    }
}
