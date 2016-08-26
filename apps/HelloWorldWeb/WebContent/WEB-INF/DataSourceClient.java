package ds;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.*;
import java.util.Date;
import java.io.*;
import oracle.jdbc.OracleConnection;

public class DataSourceClient {
	public static String writeToDatabase(Map myMap) {
		OracleConnection conn=null;
	    javax.sql.DataSource ds=null;
	    Hashtable env = new Hashtable();
	    env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
	    env.put(Context.PROVIDER_URL, "t3://192.168.0.250:23001");
	    PreparedStatement psmt;
	    String result = "result: ";
	    try{
		      Context context=new InitialContext( env );
		      //you will need to have create a Data Source with JNDI name testDS
		      ds=(javax.sql.DataSource) context.lookup ("testDS");
		      conn=(OracleConnection) ds.getConnection();
		      //java.util.Properties prop = new java.util.Properties();
		      //System.out.println("Connection object details : "+conn);
		      String sql = "select name from table1 where id=2";
		      psmt = conn.prepareStatement(sql);
		      ResultSet rs = psmt.executeQuery();
		      
		      while(rs.next()) {
		    	  result=result.concat(rs.getString(1));
		      }
		      conn.commit();
		      //psmt.close();
		      conn.close();
		    }catch(Exception ex){
		      //handle the exception
		      ex.printStackTrace();
		      
		    }
	    /*
	    try{
	      Context context=new InitialContext( env );
	      //you will need to have create a Data Source with JNDI name testDS
	      ds=(javax.sql.DataSource) context.lookup ("testDS");
	      conn=(OracleConnection) ds.getConnection();
	      //java.util.Properties prop = new java.util.Properties();
	      //System.out.println("Connection object details : "+conn);
	      String sql = "update table1 set id=1 where id=2";
	      psmt = conn.prepareStatement(sql);
	      psmt.executeUpdate();
	      conn.commit();
	      //psmt.close();
	      conn.close();
	    }catch(Exception ex){
	      //handle the exception
	      ex.printStackTrace();
	      
	    }
	    */
	    
	    /*
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "insert into table1 (id,name) values(1,'lol');";
	      try{
	      PreparedStatement statement = conn.prepareStatement(sql);
	      ResultSet rs = statement.executeQuery();
	      */
	      /*
	      while (rs.next()) {
	        Date date = rs.getDate("SYSDATE");
	        System.out.println("The current date is " + dateFormat.format(date));
	      }
	      */
	      //conn.close();
	    /*
	      }catch(Exception e){
	        System.out.println(e);
	        e.printStackTrace();
	        e.getCause();
	      }
	    */
		return result;
	}
}
