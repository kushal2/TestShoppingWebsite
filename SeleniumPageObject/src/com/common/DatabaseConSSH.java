package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.testng.annotations.Test;

	public class DatabaseConSSH {
		
	    private static  void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost,int nLocalPort,String strRemoteHost,int nRemotePort) throws JSchException {
	        final JSch jsch = new JSch();
	        Session session = jsch.getSession(strSshUser, strSshHost, 22);
	        session.setPassword(strSshPassword);

	        final Properties config = new Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);

	        session.connect();
	        //int assigned_port = session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	        session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	        //return assigned_port;
	    }
	    
	    
	    //@Test
	   // public static void testdatabase() {
	    public  String testdatabase(String query) {
        	String ActualData = null;

	        try {
	            String strSshUser = "qatester"; // SSH loging username
	            String strSshPassword = "qatester"; // SSH login password
	            String strSshHost = "107.170.213.234"; // hostname or ip or
	                                                            // SSH server
	            
	            String strRemoteHost = "127.0.0.1"; // hostname or
	           // String query = "select * from customers where customers_email_address='1a9d2@gmail.com';";                                                       // ip of
	                                                                    // your
	                                                                    // database
	                                                                    // server
	            int nLocalPort = 3366; // local port number use to bind SSH tunnel
	            int nRemotePort = 3306; // remote port number of your database
	            String strDbUser = "qatest"; // database loging username
	            String strDbPassword = "qatest"; // database login password

	            int assignedPort = 3366;
	            DatabaseConSSH.doSshTunnel(strSshUser, strSshPassword, strSshHost,nLocalPort, strRemoteHost, nRemotePort);

	            StringBuilder url =
	                    new StringBuilder("jdbc:mysql://localhost:");
	             
	            // use assigned_port to establish database connection
	            url.append(assignedPort).append ("/").append("itech").append ("?user=").
	                    append(strDbUser).append ("&password=").
	                    append (strDbPassword);
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	           
	            Connection con = DriverManager.getConnection(url.toString());
	            Statement stmt = con.createStatement();                  
	            int id = 0;
	       	 	ResultSet rs= stmt.executeQuery(query);                         
	       	 	while (rs.next())
	       	 	{id = rs.getInt(1);
	       	 	 ActualData = rs.getString("customers_firstname");
	        	 ActualData =ActualData+ rs.getString("customers_lastname");
	        	// ActualData =ActualData+ rs.getString("customers_dob");
	        	 ActualData =ActualData+ rs.getString("customers_email_address");
	        	 ActualData =ActualData+ rs.getString("customers_telephone");
	        	 }        
	       	 ResultSet rs1= stmt.executeQuery("select * from address_book where customers_id='"+id+"';");                         
	       	 	while (rs1.next())
	       	 	{
	       	 	 ActualData = ActualData+rs1.getString("entry_street_address");
	        	 ActualData =ActualData+ rs1.getString("entry_postcode");
	        	 ActualData =ActualData+ rs1.getString("entry_city");
	        	 ActualData =ActualData+ rs1.getString("entry_state");
	        	 }        
	       	 	
	            con.close();
       	 		//System.out.println(ActualData); 

	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
			return ActualData;
	    }

}
