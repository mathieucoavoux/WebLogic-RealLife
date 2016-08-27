package fr.comprehensiveit.weblogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.jdbc.OracleConnection;

public class DatabaseLogger {
	public int writeStartTime() {
		OracleConnection conn=null;
	    javax.sql.DataSource ds=null;
	    Hashtable env = new Hashtable();
	    //Define weblogic context factory and URL
	    env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
	    env.put(Context.PROVIDER_URL, "t3://192.168.0.250:23001");
	    PreparedStatement psmt;
	    try {
	    	Context context=new InitialContext( env );
	    	//Try to get datasource testDS from WebLogic
	    	ds=(javax.sql.DataSource) context.lookup ("testDS");
	    	//Get a connection from pool
	    	conn=(OracleConnection) ds.getConnection();
	    	String sql = "insert into t_start2 (d_start) values(Current_TimeStamp)";
	    	psmt = conn.prepareStatement(sql);
	    	//Execute query
	    	psmt.executeQuery();
	    	conn.commit();
	    	conn.close();
	    }catch(Exception ex){
	    	try {
	    		conn.rollback();
	    	}catch(Exception ex2) {
	    		System.out.println("Can not rollback prepared statement. This might occur if connection failed before initialization.");
	    	}
	    	//handle the exception
	    	ex.printStackTrace();
	    	return 1; 
		}
		return 0;
	}
}
