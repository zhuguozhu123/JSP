package com.schoolmates.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.schoolmates.model.News;
import com.schoolmates.model.SchoolmateActivity;
import com.schoolmates.util.SqlConnectionUtil;

/**
 * SchoolmateActivityEditPageController
 * @author Guozhu Zhu
 * @date 2017/12/23
 * @version 1.0
 *
 */
public class SchoolmateActivityEditPageController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	public Connection conn = null;
	
    public java.sql.PreparedStatement ps = null;
    
    public java.sql.ResultSet rs = null;
    
    public int rs1 ;
    
    public SqlConnectionUtil sqlUtil = null;
    
    public SchoolmateActivity activity = null;
	
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.reset(); 
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = response.getWriter(); 
		request.setCharacterEncoding("UTF-8");
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
		String date = format.format(d);
		String htmlData = request.getParameter("content") != null ? request.getParameter("content") : "";
		activity = new SchoolmateActivity();
		activity.setTitle(request.getParameter("title"));
		activity.setContent(htmlData);
		activity.setAuthor(request.getParameter("author"));
		activity.setId(Integer.parseInt(request.getParameter("id")));
		activity.setDate(date);
		System.out.println(htmlData);
		   try {
	        	SqlConnectionUtil.init();
	            conn = SqlConnectionUtil.getConnection();
	            String sql="update  schoolmate_activity set title='"+activity.getTitle()+"',content='"+activity.getContent()+"',author='"+activity.getAuthor()+"',date='"+activity.getDate()+"' where id="+activity.getId();
	            ps=conn.prepareStatement(sql);
	            rs1=ps.executeUpdate();
	            if (rs1 == 0) {
	            	pw.print("no"); 
	        		pw.flush(); 
	        		pw.close();
	            } else {
	            	pw.print("ok"); 
	        		pw.flush(); 
	        		pw.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            if(conn !=null){
	                try {
	                    conn.close();
	                } catch (Exception e2) {
	                    e2.printStackTrace();
	                }
	            }
	            if(ps !=null){
	                try {
	                    ps.close();
	                } catch (Exception e2) {
	                    e2.printStackTrace();
	                }
	            }
	        }
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page") == null ? "1" : request.getParameter("page");
		Integer p = (Integer.parseInt(page)-1);
		//��ʼ����λ��
		Integer start = (p*10);
        try {
        	SqlConnectionUtil.init();
            conn = SqlConnectionUtil.getConnection();
            String sql="select*from schoolmate_activity where id ="+request.getParameter("id");
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            List<SchoolmateActivity> newslist= new ArrayList<SchoolmateActivity>();
            while(rs.next()){
            	activity = new SchoolmateActivity();
                activity.setId(rs.getInt("id"));
                activity.setContent(rs.getString("content"));
                activity.setTitle(rs.getString("title"));
                activity.setDeleted(rs.getString("deleted"));
                activity.setDate(rs.getString("date"));
                activity.setAuthor(rs.getString("author"));
                activity.setClickCount(rs.getInt("clickCount"));
                activity.setTime(rs.getString("time"));
                System.out.println(activity.toString());
                newslist.add(activity);
            }
            request.setAttribute("newslist", newslist);
            request.setAttribute("page", p+1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(conn !=null){
                try {
                    conn.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if(ps !=null){
                try {
                    ps.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if(rs !=null){
                try {
                    rs.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Admin/SchoolmateActivity/editPage.jsp");
		dispatcher.forward(request, response);
	}

}
