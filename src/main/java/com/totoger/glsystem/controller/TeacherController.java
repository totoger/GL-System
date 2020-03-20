package com.totoger.glsystem.controller;

import com.totoger.glsystem.page.Page;
import com.totoger.glsystem.pojo.Teacher;
import com.totoger.glsystem.service.TeacherService;
import com.totoger.glsystem.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/teacher")
@Controller
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    /**
     * 教師管理列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName("teacher/teacher_list");
        return model;
    }

    /**
     * 获取教師列表
     *
     * @param username
     * @param page
     * @return
     */
    @RequestMapping(value = "/get_list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value = "username", required = false, defaultValue = "") String username,
            HttpServletRequest request,
            Page page
    ) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", "%" + username + "%");
        Object attribute = request.getSession().getAttribute("userType");
        if ("3".equals(attribute.toString())) {
            //说明是老师
            Teacher loginedStudent = (Teacher) request.getSession().getAttribute("user");
            queryMap.put("username", "%" + loginedStudent.getUsername() + "%");
        }
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", teacherService.findList(queryMap));
        ret.put("total", teacherService.getTotal(queryMap));
        return ret;
    }

    /**
     * 删除教師
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(value = "ids[]", required = true) Long[] ids
    ) {
        Map<String, String> ret = new HashMap<String, String>();
        if (ids == null || ids.length == 0) {
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的数据!");
            return ret;
        }
        if (teacherService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "删除失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;
    }

    /**
     * 编辑教師
     *
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Teacher teacher) {
        Map<String, String> ret = new HashMap<String, String>();
        if (teacher == null) {
            ret.put("type", "error");
            ret.put("msg", "数据绑定出错!");
            return ret;
        }
        if (StringUtils.isEmpty(teacher.getUsername())) {
            ret.put("type", "error");
            ret.put("msg", "工号不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(teacher.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "密码不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(teacher.getTname())) {
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空!");
            return ret;
        }
        Teacher existUser = teacherService.findByUserName(teacher.getUsername());
        if (existUser != null) {
            if (teacher.getId() != existUser.getId()) {
                ret.put("type", "error");
                ret.put("msg", "该工号已被占用!");
                return ret;
            }

        }
        if (teacherService.edit(teacher) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "修改失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功 !");
        return ret;

    }


    /**
     * 添加教師
     *
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Teacher teacher) {
        Map<String, String> ret = new HashMap<String, String>();
        if (teacher == null) {
            ret.put("type", "error");
            ret.put("msg", "数据绑定出错!");
            return ret;
        }
        if (StringUtils.isEmpty(teacher.getUsername())) {
            ret.put("type", "error");
            ret.put("msg", "教師名不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(teacher.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "密码不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(teacher.getTname())) {
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空!");
            return ret;
        }
        Teacher existUser = teacherService.findByUserName(teacher.getUsername());
        if (existUser != null) {
            ret.put("type", "error");
            ret.put("msg", "该教師已存在!");
            return ret;
        }
        if (teacherService.add(teacher) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功！");
        return ret;
    }


}