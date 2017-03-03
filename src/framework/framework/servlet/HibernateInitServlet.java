package framework.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.db.DBUtil;
import framework.db.pojo.TXtRole;

public class HibernateInitServlet extends HttpServlet {

	public HibernateInitServlet() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	@Override
	public void init() throws ServletException {
		DBUtil db = new DBUtil();
		try{
			List<TXtRole> list = db.queryByPojo(TXtRole.class);
		}finally{
			//db.close();
		}
	}

}
