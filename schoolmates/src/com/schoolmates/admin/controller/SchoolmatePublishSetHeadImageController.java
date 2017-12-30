package com.schoolmates.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.Connection;
import com.schoolmates.model.SchoolmateMien;
import com.schoolmates.model.SchoolmatePublish;
import com.schoolmates.util.SqlConnectionUtil;

/**
 * SchoolmatePublishSetHeadImageController(设置校友期刊的头像)
 * @author Guozhu Zhu
 * @date 2017/12/24
 * @version 1.0
 *
 */
public class SchoolmatePublishSetHeadImageController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Connection conn = null;
	
    public java.sql.PreparedStatement ps = null;
    
    public java.sql.ResultSet rs = null;
    
    public int rs1;
    
    public SqlConnectionUtil sqlUtil = null;
    
    public SchoolmatePublish news = null;
    
    // 定义常量,保存文件路径
    private static final String FILE_PATH = "F:" + File.separator + "schoolmates"
                + File.separator + "upload" + File.separator;// 文件上传的路径
    
    private static final String FILE_TEMP = "F:" + File.separator + "schoolmates"
                + File.separator + "temp" + File.separator;;// 文件缓存路径

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}
	
	/**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    	 PrintWriter writer;
    	// 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        File fileTemp = new File(FILE_TEMP);
        if (!fileTemp.exists()){
          fileTemp.mkdirs();
        }
        
        File localDir = new File(FILE_PATH);
        if (!localDir.exists()) {
             localDir.mkdir();
        }
        
        factory.setRepository(fileTemp);
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
            String filePath = null;
            String value = null;
            String fileName = null;
            //产生一个通用唯一识别符
            String uuid = UUID.randomUUID().toString(); 
            String uniqueId = uuid.replaceAll("-", "");
            String localFilePath = null;
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段(即是处理文件)
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        fileName = uniqueId+fileName;
                        filePath = FILE_PATH + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                    } else {
                    	  String name = item.getFieldName();
                          //解决普通输入项的数据的中文乱码问题
                          value = item.getString("UTF-8");
                          //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                          System.out.println(name + "=" + value);
                          
                    }
                }
                   
                        
                        news = new SchoolmatePublish();
                        news.setHead_image(fileName);
                        news.setId(Integer.parseInt(value));
                    	   try {
                            	SqlConnectionUtil.init();
                                conn = SqlConnectionUtil.getConnection();
                                String sql="update schoolmate_publish set head_image='"+news.getHead_image()+"' where id="+news.getId();
                                ps=conn.prepareStatement(sql);
                                rs1=ps.executeUpdate();
                                if (rs1 == 0) {
                                	request.setAttribute("message",
                                            "文件上传失败!");
                                } else {
                                	request.setAttribute("message",
                                            "文件上传成功!");
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
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/message.jsp");
		dispatcher.forward(request, response);
    }
    
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page") == null ? "1" : request.getParameter("page");
		Integer p = (Integer.parseInt(page)-1);
		//起始索引位置
		Integer start = (p*10);
        try {
        	SqlConnectionUtil.init();
            conn = SqlConnectionUtil.getConnection();
            String sql="select*from schoolmate_publish where id="+request.getParameter("id");
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            List<SchoolmatePublish> newslist= new ArrayList<SchoolmatePublish>();
            SchoolmatePublish news=null;
            while(rs.next()){
                news=new  SchoolmatePublish();
                news.setId(rs.getInt("id"));
                newslist.add(news);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Admin/SchoolmatePublish/setHeadImage.jsp");
		dispatcher.forward(request, response);
	}

}
