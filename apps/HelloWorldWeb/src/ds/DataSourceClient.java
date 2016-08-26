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
	    String sql;
	    /**
	     * Create SQL query
	     * This is a sample to understand how WebLogic is working.
	     * You shall never let user's input directly into a SQL query or you will be exposed
	     * of a SQL injection vulnerability
	     */
	    String filter = (String) myMap.get("filter"); 
	    if(filter.equals("name")) {
	    	sql="select id from table1 where name='"+myMap.get("name")+"'";
	    }else if(myMap.get("filter").equals("id")) {
	    	sql="select name from table1 where id='"+myMap.get("id")+"'";
	    }else{
	    	return "ERROR";
	    }
	    try{
		      Context context=new InitialContext( env );
		      //you will need to have create a Data Source with JNDI name testDS
		      ds=(javax.sql.DataSource) context.lookup ("testDS");
		      conn=(OracleConnection) ds.getConnection();
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
	    
		return result;
	}
	
	public static void initApplication(Map myMap) {
		OracleConnection conn=null;
	    javax.sql.DataSource ds=null;
	    Hashtable env = new Hashtable();
	    env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
	    env.put(Context.PROVIDER_URL, "t3://192.168.0.250:23001");
	    PreparedStatement psmt;
	    String sql;
	    /**
	     * Create SQL query
	     * This is a sample to understand how WebLogic is working.
	     * You shall never let user's input directly into a SQL query or you will be exposed
	     * of a SQL injection vulnerability
	     */
	    sql="insert into table2 (col1) values('"+myMap.get("init_var")+"')";
	    try{
		      Context context=new InitialContext( env );
		      //you will need to have create a Data Source with JNDI name testDS
		      ds=(javax.sql.DataSource) context.lookup ("testDS");
		      conn=(OracleConnection) ds.getConnection();
		      psmt = conn.prepareStatement(sql);
		      ResultSet rs = psmt.executeQuery();
		      
		      conn.commit();
		      //psmt.close();
		      conn.close();
		    }catch(Exception ex){
		      //handle the exception
		      ex.printStackTrace();
		      
		    }

	}
}
