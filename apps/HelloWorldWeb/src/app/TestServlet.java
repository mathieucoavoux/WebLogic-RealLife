package app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ds.DataSourceClient;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(
		urlPatterns="/TestServlet",
		initParams = { 
					@WebInitParam(name= "filter",value="id" ),
					@WebInitParam(name = "default", value = "1")
		})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	DataSourceClient dsc = new DataSourceClient();
		Map myMap = new HashMap();
		myMap.put("init_var", "myfirstinit");
		dsc.initApplication(myMap);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataSourceClient dsc = new DataSourceClient();
		Map myMap = new HashMap();
		//just for fun
		myMap.put("filter", request.getParameter("filter"));
		if (request.getParameter("filter").equals("name")) {
			myMap.put("name", request.getParameter("name"));
		}else if(request.getParameter("filter").equals("id")) {
			myMap.put("id", request.getParameter("id"));
		}else{
			response.getWriter().append("ERROR, filter is not recognized :").append(request.getParameter("filter"));
		}
		response.getWriter().append("Result: ").append(dsc.writeToDatabase(myMap));
		//end
		/*
		if(request.getParameter("filter") == null || request.getParameter(request.getParameter("filter")) == null) {
			myMap.put("filter", getInitParameter("filter"));
			myMap.put(getInitParameter("filter"), getInitParameter("default"));
		}else{
			myMap.put("filter", request.getParameter("filter"));
			if (request.getParameter("filter").equals("name")) {
				myMap.put("name", request.getParameter("name"));
			}else if(request.getParameter("filter").equals("id")) {
				myMap.put("id", request.getParameter("id"));
			}else{
				response.getWriter().append("ERROR, filter is not recognized :").append(request.getParameter("filter"));
			}
		}
		response.getWriter().append("Result: ").append(dsc.writeToDatabase(myMap));
		*/
		//response.getWriter().append();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
