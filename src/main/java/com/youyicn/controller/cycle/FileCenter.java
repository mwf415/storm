package com.youyicn.controller.cycle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ActivesService;
import com.youyicn.service.cycle.RoomService;

/**
 * 主要实现文件的上传和下载功能
 */
@Controller
public class FileCenter {
	public Logger log = LoggerFactory.getLogger(File.class);
	@Autowired
	public RoomService roomService;
	@Autowired
	public UserService userService;
	
	@Autowired
	public ActivesService activesService;	
	/**
	 * 教学查房上传资料
	 */
	@RequestMapping("/file/upfile.htm")
	public String upfileCheRoom(Integer id,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("activesId", id);
		return "/file/file";
	}
	
	//教学查房文件上传
	@RequestMapping("/file/sumfile.htm")
	public  void cheRoomfileUp (HttpServletRequest request,HttpServletResponse response ,ModelMap model) throws Exception{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Integer activesId = (Integer) session.getAttribute("activesId");
		
		fileUp(request,activesId);
		Integer menuOrder = (Integer) session.getAttribute("menuOrder");
		String li = (String) session.getAttribute("li");
		String div = (String) session.getAttribute("div");
		response.sendRedirect("/cycle/activesCont/index.htm?li="+li+"&div="+div+"&type=t&menuOrder="+menuOrder);  
	}
	//教学查房下载列表
	@RequestMapping("/file/filelist.htm")
	public String cheRoomDownList(HttpServletRequest request,HttpServletResponse response, ModelMap model,Integer activesId) 
			throws IOException{
		//前台获取是乱码，那就根据用户登陆信息查询
		String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/");
		
		
		savePath =savePath+"\\file\\"+activesId+"\\";
		//用来存储下载列表的
		if(!"".equals(savePath) && null!= savePath  ){
			List<String> downList = downList(savePath);
			model.put("downList", downList);
			model.put("activesId", activesId);
        }else {
        	model.put("downList", null);
        }
        return "/file/chedownlist";
	}
	//教学查房下载
	@RequestMapping("/file/filedown.htm")
	public void cheRoomDown(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws UnsupportedEncodingException{
		String fileParm = request.getParameter("fileParm");
		String file [] = fileParm.split(":");
		String fileName=file[1];
		String activesId =file[0];
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String filePath = savePath	+ "WEB-INF\\file\\";
		fileDown(response,filePath,activesId,fileName);
	}
	
	//模板下载
	@RequestMapping("/file/mobanfiledown.htm")
	public void mobanDown(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws UnsupportedEncodingException{
		String fileParm = request.getParameter("fileParm");
		String file [] = fileParm.split(":");
		String fileName=file[1];
		String activesId =file[0];
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String filePath = savePath	+ "file\\";
		fileDown(response,filePath,activesId,fileName);
	}
	
	
	
	/**
	 * 下载列表  传一个参数  就可以获得相应功能下的文件列表
	 * 比如如果入科教育，传一个科室的编号就可以显示入科教育的文件
	 */
	private List<String> downList(String paramPath) {
		List<String> downList= new ArrayList<String>();
        
        File file = new File(paramPath);
        String list[] = file.list();
        if (list != null && list.length > 0){
            for (int i=0; i<list.length; i++){
                String fileName = list[i];
                downList.add(fileName);
            }
        }
		return downList;
	}
	
	
	/**
	 *  文件上传功能
	 */
	private void  fileUp(HttpServletRequest request,Integer activesId)
			throws IOException, FileNotFoundException {
		RequestContext requestContext = new ServletRequestContext(request);
		String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/");
		savePath =savePath+"\\file\\"+activesId+"\\";
		if (ServletFileUpload.isMultipartContent(requestContext)) {
			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			File file = new File(savePath);
			// 判断上传文件的保存目录是否存在
			if (!file.exists() && !file.isDirectory()) {
				System.out.println(savePath + "目录不存在，需要创建");
				// 创建目录
				file.mkdirs();
			}

			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	            List<MultipartFile> fileList = multipartRequest.getFiles("file");  
				for (MultipartFile item : fileList) {
					String filename = item.getOriginalFilename();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savePath + "\\"+ filename);
					byte buffer[] = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					
					Actives ac = activesService.getById(activesId);
					Integer fileNum ;
					if(null ==ac.getFileNum()){
						fileNum=0;
					}else{
						fileNum = ac.getFileNum();
					}
					fileNum = fileNum+1;
					activesService.updateFileNum(activesId, fileNum);;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
			
		} 
	}
	// 文件下载功能；
	private static void fileDown(HttpServletResponse response, String filePath,String randomNum,String fileName) throws UnsupportedEncodingException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));
		String savePath = filePath + randomNum+"\\"+fileName;
		try {
			InputStream inputStream = new FileInputStream(new File(savePath));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * 获取老师名字
	 */
	private User getuserSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User teacher = new User();
		if(obj instanceof String){
			String loginName = (String) obj;
			teacher  = userService.getByNum(loginName);
		}
		return teacher;
	}
	
}
