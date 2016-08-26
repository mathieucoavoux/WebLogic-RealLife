package fr.comprehensiveit.weblogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.jdbc.OracleConnection;

public class DatabaseLogger {
	public int writeStartTime() {
		OracleConnection conn=null;
	    javax.sql.DataSource ds=null;
	    Hashtable env = new Hashtable();
	    env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
	    env.put(Context.PROVIDER_URL, "t3://192.168.0.250:23001");
	    PreparedStatement psmt;
	    try {
	    	Context context=new InitialContext( env );
		      //you will need to have create a Data Source with JNDI name testDS
		      ds=(javax.sql.DataSource) context.lookup ("testDS");
		      conn=(OracleConnection) ds.getConnection();
		      String sql = "insert into t_start2 (d_start) values(Current_TimeStamp)";
		      psmt = conn.prepareStatement(sql);
		      ResultSet rs = psmt.executeQuery();
		      conn.commit();
		      //psmt.close();
		      conn.close();
	    }catch(Exception ex){
		      //handle the exception
		      ex.printStackTrace();
		     return 1; 
		}
		return 0;
	}
}
