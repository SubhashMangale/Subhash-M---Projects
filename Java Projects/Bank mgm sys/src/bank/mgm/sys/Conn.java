/*
Steps for connecting database with java code.
step1: Regitster the driver 
step2: Create the connection
step3: 
*/
package bank.mgm.sys;
import java.sql.*;

public class Conn {
    Connection c; // connection class global object creation.
    Statement s;
    public Conn()
    {
        try
        {
            // Class.forName(com.mysql.cj.jdbc.Driver); // step1: connection driver register but this is not req and java do it automatically.
            //jdbc:mysql://localhost:3306/?user=root
            c  = DriverManager.getConnection("jdbc:mysql:///bank_management_system","root","Subhash@2063");
            s = c.createStatement();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}

