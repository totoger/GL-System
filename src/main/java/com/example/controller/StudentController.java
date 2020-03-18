package com.example.controller;


import com.example.pojo.Clazz;
import com.example.pojo.Student;
import com.example.page.Page;
import com.example.service.ClazzService;
import com.example.service.StudentService;
import com.example.util.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RequestMapping("/student")
@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClazzService clazzService;
	/**
	 * ????????
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("student/student_list");
		List<Clazz> clazzList = clazzService.findAll();
		model.addObject("clazzList",clazzList );
		model.addObject("clazzListJson", JSONArray.fromObject(clazzList));
		return model;
	}
	
	/**
	 * ??????????
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(value="name",required=false,defaultValue="") String name,
			@RequestParam(value="clazzId",required=false) Long clazzId,
			HttpServletRequest request,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", "%"+name+"%");
		Object attribute = request.getSession().getAttribute("userType");
		if("2".equals(attribute.toString())){
			//????????
			Student loginedStudent = (Student)request.getSession().getAttribute("user");
			queryMap.put("username", "%"+loginedStudent.getUsername()+"%");
		}
		if(clazzId != null){
			queryMap.put("clazzId", clazzId);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", studentService.findList(queryMap));
		ret.put("total", studentService.getTotal(queryMap));
		return ret;
	}
	

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Student student){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(student.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "???????????????");
			return ret;
		}
		if(StringUtils.isEmpty(student.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "????????????????");
			return ret;
		}
		if(student.getClazzId() == null){
			ret.put("type", "error");
			ret.put("msg", "?????????????");
			return ret;
		}
		if(isExist(student.getUsername(), student.getId())){
			ret.put("type", "error");
			ret.put("msg", "????????????");
			return ret;
		}
		student.setSn(StringUtil.generateSn("S", ""));
		if(studentService.edit(student) <= 0){
			ret.put("type", "error");
			ret.put("msg", "??????????");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "??????????");
		return ret;
	}
	
	
	/**
	 * ?????????
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Student student){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(student.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "???????????????");
			return ret;
		}
		if(StringUtils.isEmpty(student.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "????????????????");
			return ret;
		}
		if(student.getClazzId() == null){
			ret.put("type", "error");
			ret.put("msg", "?????????????");
			return ret;
		}
		if(isExist(student.getUsername(), null)){
			ret.put("type", "error");
			ret.put("msg", "????????????");
			return ret;
		}
		student.setSn(StringUtil.generateSn("S", ""));
		if(studentService.add(student) <= 0){
			ret.put("type", "error");
			ret.put("msg", "??????????");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "??????????");
		return ret;
	}
	
	/**
	 * ?????????
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(value="ids[]",required=true) Long[] ids
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(ids == null || ids.length == 0){
			ret.put("type", "error");
			ret.put("msg", "????????????????");
			return ret;
		}
		try {
			if(studentService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
				ret.put("type", "error");
				ret.put("msg", "???????");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "????????????????????????N??");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "???????????");
		return ret;
	}

	@RequestMapping(value="/upload_photo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadPhoto(MultipartFile photo,
										   HttpServletRequest request,
										   HttpServletResponse response
			) throws IOException {
		Map<String, String> ret = new HashMap<String, String>();
		if(photo == null){
			//?????????
			ret.put("type", "error");
			ret.put("msg", "??????????");
			return ret;
		}
		if(photo.getSize() > 10485760){
			//?????????
			ret.put("type", "error");
			ret.put("msg", "???????????10M???????????10M??????");
			return ret;
		}
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1,photo.getOriginalFilename().length());
		if(!"jpg,png,gif,jpeg".contains(suffix.toLowerCase())){
			ret.put("type", "error");
			ret.put("msg", "??????????????????jpg,png,gif,jpeg?????????");
			return ret;
		}
		String savePath = request.getServletContext().getRealPath("/") + "\\upload\\";
		System.out.println(savePath);
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()){
			savePathFile.mkdir();//?????????????????????upload
		}
		//???????????????????
		String filename = new Date().getTime() + "." + suffix;
		photo.transferTo(new File(savePath + filename ));
		ret.put("type", "success");
		ret.put("msg", "??????????");
		ret.put("src", request.getServletContext().getContextPath() + "/upload/" + filename);
		return ret;
	}
	
	private boolean isExist(String username,Long id){
		Student student = studentService.findByUserName(username);
		if(student != null){
			if(id == null){
				return true;
			}
			if(student.getId().longValue() != id.longValue()){
				return true;
			}
		}
		return false;
	}
}
