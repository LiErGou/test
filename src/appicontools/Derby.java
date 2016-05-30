/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.util.Properties;  
  
/** 
 * The Embedded mode is limited by that we can't run simultaneously  
 * two programs (two JVM instances) using a same database (databaseName is the same). 
 * <p> 
 * But we can instead use the NetworkServer mode to avoid this case,  
 * it is to say the "Client/Server" mode. 
 * In this mode, you have to first start the NetworkServer by this command : 
 * <pre> 
 * java org.apache.derby.drda.NetworkServerControl start [-h hostIP -p portNumber] 
 * </pre> 
 * @author HAN 
 * 
 */  
public class Derby {  
    private Connection con=null;  
    private String port=null;  
    private String ip=null;  
  
    /** 
     * The port will be set to default: 1527 
     */  
    public void setPortToDefault(){  
        port="1527";          
    }  
  
    public void setPort(String port){  
        this.port=port;  
    }  
  
    public void setServer(String ip){  
        this.ip=ip;  
    }  
  
    /** 
     * This express loading driver is not necessary for Java 6 and later, JDBC 4.0 and later. 
     * Because it can be added automatically by <code>DriverManager</code> when connecting to a database. 
     */  
    public void loadDriver(){  
        //load the driver  
        if(port==null){  
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");  
                System.out.println("The embedded driver is successfully loaded");  
            } catch (ClassNotFoundException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }else{  
            try{  
                Class.forName("org.apache.derby.jdbc.ClientDriver");  
                System.out.println("The client driver is successfully loaded");  
            }catch(ClassNotFoundException e){  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * create and connect a database 
     * @param databaseName 
     * @param user 
     * @param password 
     * @return a connection to the URL  
     */  
    public Connection createDatabaseAndGetConnection(String databaseName, String user, String password){  
        //create and connect the database  
        Properties props=new Properties();  
        props.put("user",user);   
        props.put("password",password);  
        if(port==null){  
            try {  
                con=DriverManager.getConnection("jdbc:derby:"+databaseName+";create=true", props);  
                System.out.println("Connection is successfully established, it use an Embedded database");  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }else if(ip==null){  
            try {  
                con=DriverManager.getConnection("jdbc:derby://localhost:"+port+"/"+databaseName+";create=true", props);  
                System.out.println("Connection is sucessfully established, it use an network database but stored in the local host via the port: "+port);  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }else{  
            try {  
                con=DriverManager.getConnection("jdbc:derby://"+ip+":"+port+"/"+databaseName+";create=true", props);  
                System.out.println("Connection is sucessfully established, it use an network database whose host ip is: "+ip+"and via the port: "+port);  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return con;  
    }  
  
    /** 
     * Shut down a specified database. But it doesn't matter that later we could also connect to another database. 
     * @param databaseName 
     */  
    public void shutdownDatabase(String databaseName){  
        if(port==null){   
            try {  
                DriverManager.getConnection("jdbc:derby:"+databaseName+";shutdown=true");                 
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                System.out.println("Database: "+databaseName+" shut down normally");  
            }  
        }else if(ip==null){  
            try {  
                DriverManager.getConnection("jdbc:derby://localhost:"+port+"/"+databaseName+";shutdown=true");                
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                System.out.println("Database: "+databaseName+" shut down normally");  
            }  
        }else{  
            try {  
                DriverManager.getConnection("jdbc:derby://"+ip+":"+port+"/"+databaseName+";shutdown=true");   
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                System.out.println("Database: "+databaseName+" shut down normally");  
            }  
        }  
    }  
      
    /** 
     * shut down all opened databases and close the Derby driver. 
     * The effect is that after the execution of this method, we will not permitted to use Derby again in the rest of our program. 
     * Or else, an exception of "can't find a suitable driver for [a database URL]" will be thrown. 
     */  
    public void shutdownAll(){  
        try {  
            DriverManager.getConnection("jdbc:derby:;shutdown=true");     
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            System.out.println("All databases shut down normally and Derby driver closed");  
        }  
    }  
  
    /** 
     * Just connect to a database desired by providing the appropriate parameters. 
     * @param databaseName 
     * @param user 
     * @param password 
     * @return 
     */  
    public Connection getConnection(String databaseName, String user, String password){  
        if(port==null){  
            try {  
                con=DriverManager.getConnection("jdbc:derby:"+databaseName,user,password);  
                System.out.println("Connection is sucessfully established, it use an Embedded database");  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }else if(ip==null){  
            try {  
                con=DriverManager.getConnection("jdbc:derby://localhost:"+port+"/"+databaseName,user,password);  
                System.out.println("Connection is sucessfully established, it use an network database but stored in the local host via the port: "+port);  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }else{  
            try {  
                con=DriverManager.getConnection("jdbc:derby://"+ip+":"+port+"/"+databaseName,user,password);  
                System.out.println("Connection is sucessfully established, it use an network database whose host ip is: "+ip+"and via the port: "+port);  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return con;  
    }  
}  