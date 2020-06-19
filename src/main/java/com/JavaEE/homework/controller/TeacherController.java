package com.JavaEE.homework.controller;

import com.JavaEE.homework.bo.ResponseBean;
import com.JavaEE.homework.constant.WebConstant;
import com.JavaEE.homework.entity.Homework;
import com.JavaEE.homework.entity.StuHomework;
import com.JavaEE.homework.entity.Teacher;
import com.JavaEE.homework.service.HomeworkService;
import com.JavaEE.homework.service.StuHomeworkService;
import com.JavaEE.homework.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;
    @Resource
    private HomeworkService homeworkService;
    @Resource
    private StuHomeworkService stuHomeworkService;

    private String teacher = "teacher";

    // 注册
    @ResponseBody
    @RequestMapping("/register.do")
    public Map<String, Object> register(@RequestBody Teacher teacher) {
        ResponseBean responseBean = new ResponseBean();
        Teacher tech = teacherService.selectByUsername(teacher.getUsername());
        if (tech == null) {
            teacherService.insertSelective(teacher);
        } else {
            responseBean.putError("注册失败，该用户名已注册");
        }
        return responseBean.getResponseMap();
    }

    // 登录
    @ResponseBody
    @RequestMapping("/login.do")
    public Map<String, Object> login(@RequestBody Teacher teacher, HttpSession session) {
        ResponseBean responseBean = new ResponseBean();
        Teacher tech = teacherService.selectByUsername(teacher.getUsername());
        if (tech == null) {
            responseBean.putError("用户名/密码错误");
        } else {
            if (!tech.getPassword().equals(teacher.getPassword())) {
                responseBean.putError("用户名/密码错误");
            } else {
                session.setAttribute(WebConstant.SESSION_KEY_USER, tech);
            }
        }
        return responseBean.getResponseMap();
    }

    // 编辑个人资料页面
    @RequestMapping("/edit.html")
    public String editHtml() {
        return teacher + "/edit_html";
    }

    // 编辑个人资料
    @ResponseBody
    @RequestMapping("/edit")
    public Map<String, Object> edit(@RequestBody Teacher teacher, HttpSession session) {
        ResponseBean responseBean = new ResponseBean();
        // 根据用户名去查询是否已经存在此用户
        Teacher tech = teacherService.selectByUsername(teacher.getUsername());
        // 不存在则修改
        if (tech == null) {
            teacherService.updateByPrimaryKeySelective(teacher);
            // 更新 session
            session.removeAttribute(WebConstant.SESSION_KEY_USER);
            session.setAttribute(WebConstant.SESSION_KEY_USER, teacher);
        } else {
            // 存在的话比较ID是否一致，一致则修改
            if (tech.getId() == teacher.getId()) {
                teacherService.updateByPrimaryKeySelective(teacher);
                // 更新 session
                session.removeAttribute(WebConstant.SESSION_KEY_USER);
                session.setAttribute(WebConstant.SESSION_KEY_USER, teacher);
            } else {
                responseBean.putError("用户名已存在，请重新输入");
            }
        }
        return responseBean.getResponseMap();
    }

    /*
        作业管理
     */
    @RequestMapping("/homework.html")
    public String homeworkHtml() {
        return teacher + "/homework";
    }

    // 数据
    @ResponseBody
    @RequestMapping("/homework/data")
    public Map<String, Object> homeworkData(@RequestParam("page") int page,
                                            @RequestParam("limit") int limit) {
        ResponseBean responseBean = new ResponseBean();
        List<Homework> homeworkList = homeworkService.selectPaging(page, limit);
        int count = homeworkService.tatolCount();
        responseBean.putPageData(homeworkList, count);
        return responseBean.getResponseMap();
    }

    // 发布作业
    @RequestMapping("/homework/add.html")
    public String addHomeworkHtml() {
        return teacher + "/homework_add";
    }

    @ResponseBody
    @RequestMapping("/homework/add")
    public Map<String, Object> addHomework(@RequestBody Homework homework, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute(WebConstant.SESSION_KEY_USER);
        homework.setTeacherId(teacher.getId());
        homework.setIssueTime(new Date());
        homeworkService.insertSelective(homework);
        return new ResponseBean().getResponseMap();
    }

    // 查看详情
    @RequestMapping("/homework/item/{id}.html")
    public String homeworkItemHtml (@PathVariable("id") int id, Model model) {
        Homework homework = homeworkService.selectById(id);
        model.addAttribute("homework", homework);
        return teacher + "/homework_item";
    }

    // 删除作业
    @ResponseBody
    @RequestMapping("/homework/delete")
    public Map<String, Object> deleteHomework(@RequestBody int id) {
        homeworkService.deleteByPrimaryKey(id);
        return new ResponseBean().getResponseMap();
    }

    /*
        批改作业，isCorrect-0：未批改，isCorrect-1：已批改
     */
    @RequestMapping("/homework/{isCorrect}.html")
    public String stuHomeworkHtml(@PathVariable("isCorrect") boolean isCorrect, Model model) {
        model.addAttribute("isCorrect", isCorrect);
        return teacher + "/stuHomework";
    }

    // 数据
    @ResponseBody
    @RequestMapping("/homework/correct/data")
    public Map<String, Object> stuHomeworkData(@RequestParam("page") int page,
                                                   @RequestParam("limit") int limit,
                                                   @RequestParam("isCorrect") boolean isCorrect) {
        ResponseBean responseBean = new ResponseBean();
        List<StuHomework> stuHomeworkList = stuHomeworkService.selectPaging(page, limit, isCorrect);
        int count = stuHomeworkService.tatolCount(isCorrect);
        responseBean.putPageData(stuHomeworkList, count);
        return responseBean.getResponseMap();
    }

    // 批改
    @RequestMapping("/homework/correct/{id}.html")
    public String correctStuHomeworkHtml (@PathVariable("id") int id, Model model) {
        StuHomework stuHomework = stuHomeworkService.selectById(id);
        model.addAttribute("stuHomework", stuHomework);
        return teacher + "/stuHomework_correct";
    }

    @ResponseBody
    @RequestMapping("/homework/correct")
    public Map<String, Object> correctStuHomework(@RequestBody StuHomework stuHomework) {
        stuHomeworkService.updateByPrimaryKeySelective(stuHomework);
        return new ResponseBean().getResponseMap();
    }

    // 查看详情
    @RequestMapping("/homework/correct/item/{id}.html")
    public String correctItemStuHomeworkHtml (@PathVariable("id") int id, Model model) {
        StuHomework stuHomework = stuHomeworkService.selectById(id);
        model.addAttribute("stuHomework", stuHomework);
        return teacher + "/stuHomework_correct_item";
    }

    // 删除
    @ResponseBody
    @RequestMapping("/stuHomework/delete")
    public Map<String, Object> deleteStuHomework(@RequestBody int id) {
        stuHomeworkService.deleteByPrimaryKey(id);
        return new ResponseBean().getResponseMap();
    }
}