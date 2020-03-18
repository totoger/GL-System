package com.example.controller;

import com.example.pojo.Clazz;
import com.example.pojo.Grade;
import com.example.page.Page;
import com.example.service.ClazzService;
import com.example.service.GradeService;
import com.example.util.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RequestMapping("/clazz")
@Controller
public class ClazzController {
	
	@Autowired
	private GradeService gradeService;
	@Autowired
	private ClazzService clazzService;

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("clazz/clazz_list");
		List<Grade> findAll = gradeService.findAll();
		model.addObject("gradeList",findAll );
		model.addObject("gradeListJson", JSONArray.fromObject(findAll));
		return model;
	}
	
	/**
	 * ��ȡ�༶�б�
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(value="name",required=false,defaultValue="") String name,
			@RequestParam(value="gradeId",required=false) Long gradeId,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", "%"+name+"%");
		if(gradeId != null){
			queryMap.put("gradeId", gradeId);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", clazzService.findList(queryMap));
		ret.put("total", clazzService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * �༭�༶��Ϣ
	 * @param clazz
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Clazz clazz){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(clazz.getName())){
			ret.put("type", "error");
			ret.put("msg", "�༶���Ʋ���Ϊ�գ�");
			return ret;
		}
		if(clazz.getGradeId() == null){
			ret.put("type", "error");
			ret.put("msg", "�����꼶����Ϊ�գ�");
			return ret;
		}
		if(clazzService.edit(clazz) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�༶�޸�ʧ�ܣ�");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�༶�޸ĳɹ���");
		return ret;
	}
	
	
	/**
	 * ��Ӱ༶��Ϣ
	 * @param clazz
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Clazz clazz){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(clazz.getName())){
			ret.put("type", "error");
			ret.put("msg", "�༶���Ʋ���Ϊ�գ�");
			return ret;
		}
		if(clazz.getGradeId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ�������꼶��");
			return ret;
		}
		if(clazzService.add(clazz) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�༶���ʧ�ܣ�");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�༶��ӳɹ���");
		return ret;
	}
	
	/**
	 * ɾ���༶��Ϣ
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
			ret.put("msg", "��ѡ��Ҫɾ�������ݣ�");
			return ret;
		}
		try {
			if(clazzService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "�ð༶�´���ѧ����Ϣ������嶯��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�༶ɾ���ɹ���");
		return ret;
	}
	
}
