package cn.edu.tju.ina.estuary.mvc.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tju.ina.estuary.doc.annotation.Description;
import cn.edu.tju.ina.estuary.doc.annotation.Param;
import cn.edu.tju.ina.estuary.doc.annotation.Return;
import cn.edu.tju.ina.estuary.doc.annotation.Title;
import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.service.InfoService;
import cn.edu.tju.ina.estuary.util.FileHelper;
import cn.edu.tju.ina.estuary.util.ResponseBuilder;

@Controller("uploadRestController")
@RequestMapping("/rest/upload")
public class UploadController extends BaseController {
	
    @Autowired
    private InfoService infoService;
    
    @Return("{'errcode':0, 'errmsg':'xxx', 'filename':'新上传的文件名'}")
    @Title("上传图片")
    @Description("用户上传普通图片的接口")
    @Param(name={"file"}, desc={"上传的图片"})
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> add(@RequestParam MultipartFile file, 
				Model model, HttpSession session) {
		User u = new User(getCurrentUserId());
		String subPath = new SimpleDateFormat("YYYYMM").format(new Date());
		String filename = u.getId() + "_" + Integer.toHexString((int) System.nanoTime()) + ".jpg";
		
		FileHelper fileHelper = new FileHelper(session);
		fileHelper.save(file, "uploads/" + subPath, filename);
		ResponseBuilder resp = new ResponseBuilder();
    	resp.code(ResponseBuilder.SUCCESS);
    	resp.add("filename", "uploads/" + subPath + "/" + filename);
		return resp.build();
	}
    
    @RequestMapping(value="/avatar", method = RequestMethod.POST)
    @ResponseBody
    @Return("{'errcode':0, 'errmsg':'xxx', 'filename':'新上传的文件名'}")
    @Title("上传头像")
    @Description("用户上传自己头像的接口")
    @Param(name={"file"}, desc={"上传的图片"})
	public Map<String, Object> add(@RequestParam MultipartFile file, HttpSession session) {
    	User u = new User(getCurrentUserId());
		String filename = u.getId() + "_" + Integer.toHexString((int) System.nanoTime()) + ".jpg";
		
		FileHelper fileHelper = new FileHelper(session);
		fileHelper.save(file, "uploads/", filename);
		ResponseBuilder resp = new ResponseBuilder();
    	resp.code(ResponseBuilder.SUCCESS);
    	resp.add("filename", "avatar/" + filename);
		return resp.build();
	}
    
}
