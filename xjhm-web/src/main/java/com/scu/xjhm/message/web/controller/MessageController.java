package com.scu.xjhm.message.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.dayatang.utils.Page;

import com.mysql.jdbc.StringUtils;
import com.scu.xjhm.message.facade.MessageFacade;
import com.scu.xjhm.message.facade.dto.MessageDTO;

import org.openkoala.koala.commons.InvokeResult;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@Controller
@RequestMapping("/message")
public class MessageController {
		
	@Inject
	private MessageFacade messageFacade;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
	
	/**
	 * 语音文件上传。
	 * 
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @version
	 * 20181108pm
	 *     1.new.
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadMessage", method = RequestMethod.POST)
	public InvokeResult uploadMessage(HttpServletRequest request) throws IllegalStateException, IOException{
		request.setCharacterEncoding("utf-8");//设置编码
		//配置文件上传的路劲
		//String path = request.getRealPath("/uploadMessage") + "/";
		String path = request.getSession().getServletContext().getRealPath("/uploadMessage") + "/";
		File dirFile = new File(path);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		System.out.println("1111, path = "+path);
		//获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(1024 * 1024);
		
		//高水平的API文件处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		Map resultMap = new HashMap();
		try {
			List<FileItem> list = upload.parseRequest(request);
			FileItem fileItem = null;
			for(FileItem item : list){
				//获取表单的属性名字
				String name = item.getFieldName();
				//如果获取的表单信息是普通的文本信息
				if(item.isFormField()){
					//获取用户输入的字符串
					String value = item.getString();
					request.setAttribute(name,value);
					System.out.println("name=" + name + ",value=" + value);
				}
				else {
					fileItem = item;
				}
			}
			
			//自定义上传音频的文件名
			//上传用户的用户名，电话号码，上传时间
			String userName = URLDecoder.decode((String) request.getAttribute("username")); 
			String userPhoneNumber = (String) request.getAttribute("userphonenumber");
			String time = (String) request.getAttribute("time");
			String fileName =  userName + userPhoneNumber +" "+ time +".mp3";
					
			String destPath = path + fileName;
			System.out.println("destPath=" + destPath);
			
			//真正写在磁盘上
			File file = new File(destPath);
			OutputStream out = new FileOutputStream(file);
			InputStream in = fileItem.getInputStream();
			int length = 0;
			byte[] buf = new byte[1024];
			//in.read(buf)每次读到的数据存放在buf数组中
			while((length = in.read(buf)) != -1){
				//在buf数组中取出数据写到（输入流）磁盘上
				out.write(buf,0,length);
			}
			in.close();
			out.close();
			
			String imageUrl = request.getContextPath()+"/uploadMessage/" + fileName;
			LOGGER.info("imageUrl:"+imageUrl);
			resultMap.put("message", "上传成功。");
			resultMap.put("imageUrl", imageUrl);
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			return InvokeResult.success(resultMap);
		}
		
		/*
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (!multipartResolver.isMultipart(request))  return InvokeResult.failure("无文件上传");
		// 转换成多部分request
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		// 取得request中的所有文件名
		Iterator<String> iter = multiRequest.getFileNames();
		if(!iter.hasNext())  return InvokeResult.failure("无文件上传");
		// 取得上传文件
		MultipartFile f = multiRequest.getFile(iter.next());
		if(f == null)  return InvokeResult.failure("无文件上传");
		// 取得当前上传文件的文件名称
		String myFileName = f.getOriginalFilename();
		// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
		if(StringUtils.isNullOrEmpty(myFileName))  return InvokeResult.failure("无文件上传");
		// 定义上传路径
		//String path = "E:\\TDDOWNLOAD\\" + myFileName;
		String realPath = request.getSession().getServletContext().getRealPath("uploadMessage");
		LOGGER.info("the realPath of uploadImage:"+realPath);
		File localFile = new File(realPath);
		if(!localFile.exists()) localFile.mkdir();
		localFile = new File(realPath + "/" + myFileName);
		f.transferTo(localFile);
		Map resultMap = new HashMap();
		String imageUrl = request.getContextPath()+"/uploadMessage/"+myFileName;
		LOGGER.info("imageUrl:"+imageUrl);
		resultMap.put("message", "上传成功。");
		resultMap.put("imageUrl", imageUrl);
		*/
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(MessageDTO messageDTO) {
		return messageFacade.createMessage(messageDTO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(MessageDTO messageDTO) {
		return messageFacade.updateMessage(messageDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(MessageDTO messageDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<MessageDTO> all = messageFacade.pageQueryMessage(messageDTO, page, pagesize);
		return all;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public InvokeResult remove(@RequestParam String ids) {
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        	        					idArrs[i] = Long.parseLong(value[i]);
						        }
        return messageFacade.removeMessages(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return messageFacade.getMessage(id);
	}
	
		
//    @InitBinder    
//    public void initBinder(WebDataBinder binder) {  
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
//        dateFormat.setLenient(false);    
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
//        //CustomDateEditor 可以换成自己定义的编辑器。  
//        //注册一个Date 类型的绑定器 。
//        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
//    }
	
}
