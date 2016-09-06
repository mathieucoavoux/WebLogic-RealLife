package fr.comprehensiveit.weblogic.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;

import oracle.jdbc.OracleConnection;

public class DatabaseHandler {

	/**
	 * Get database properties
	 * @throws FileNotFoundException 
	 */
	public Hashtable getProperties() throws FileNotFoundException {
		Hashtable env = new Hashtable();
		FileInputStream propFile = new FileInputStream("cacheretriever.properties");
		Properties prop = new Properties(System.getProperties());
		env.put(Context.INITIAL_CONTEXT_FACTORY,prop.getProperty("INITIAL_CONTEXT_FACTORY"));
		env.put(Context.PROVIDER_URL,prop.getProperty("PROVIDER_URL"));
		return env;
	}
	
	/**
	 * Execute query to database
	 * 
	 * @param Map containing column name and column type to display
	 * @param Map containing filter column and filter 
	 * @return Map containing execution code and Map values
	 * @throws FileNotFoundException 
	 */
	public Map selectQuery(Map colName,Map colType,Map colFilter,Map filter,String table) throws FileNotFoundException {
		Map result = new HashMap();
		Map mapType = new HashMap();
		OracleConnection conn=null;
	    javax.sql.DataSource ds=null;
	    Hashtable env = getProperties();
	    PreparedStatement psmt;
	    int numColDisplay = colName.size();
	    String sql = "select";
	    for(int indColDisplay = 1;indColDisplay >= numColDisplay;indColDisplay++) {
	    	mapType.put(colName.get(indColDisplay), colType.get(indColDisplay));
	    	sql=sql+" "+colName.get(indColDisplay);
	    }
	    sql=sql+" from "+table;
	    
	    return result;
	}
}
