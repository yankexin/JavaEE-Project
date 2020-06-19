package com.JavaEE.homework.controller;

import com.JavaEE.homework.bo.ResponseBean;
import com.JavaEE.homework.constant.WebConstant;
import com.JavaEE.homework.entity.Homework;
import com.JavaEE.homework.entity.StuHomework;
import com.JavaEE.homework.entity.Student;
import com.JavaEE.homework.service.HomeworkService;
import com.JavaEE.homework.service.StuHomeworkService;
import com.JavaEE.homework.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;
    @Resource
    private HomeworkService homeworkService;
    @Resource
    private StuHomeworkService stuHomeworkService;

    private String student = "student";

    // 注册
    @ResponseBody
    @RequestMapping("/register.do")
    public Map<String, Object> register(@RequestBody Student student){
        ResponseBean responseBean = new ResponseBean();
        Student stu = studentService.selectByUsername(student.getUsername());
        if (stu == null) {
            studentService.insertSelective(student);
        } else {
            responseBean.putError("注册失败，该用户名已注册");
        }
        return responseBean.getResponseMap();
    }

    // 登录
    @ResponseBody
    @RequestMapping("/login.do")
    public Map<String, Object> login(@RequestBody Student student, HttpSession session){
        ResponseBean responseBean = new ResponseBean();
        Student stu = studentService.selectByUsername(student.getUsername());
        if (stu == null) {
            responseBean.putError("用户名/密码错误");
        } else {
            if (!stu.getPassword().equals(student.getPassword())) {
                responseBean.putError("用户名/密码错误");
            } else {
                session.setAttribute(WebConstant.SESSION_KEY_USER, stu);
            }
        }
        return responseBean.getResponseMap();
    }

    // 查看作业
    @RequestMapping("/homework/{id}.html")
    public String homeworkHtml (@PathVariable("id") int id, Model model) {
        Homework homework = homeworkService.selectById(id);
        model.addAttribute("homework", homework);
        return student + "/homework";
    }

    // 提交作业
    @ResponseBody
    @RequestMapping("/homework/sumbit")
    public Map<String, Object> sumbitStuHomework (@RequestBody StuHomework stuHomework, HttpSession session) {
        Student student = (Student) session.getAttribute(WebConstant.SESSION_KEY_USER);
        stuHomework.setStudentId(student.getId());
        stuHomeworkService.insertSelective(stuHomework);
        return new ResponseBean().getResponseMap();
    }

    // 我的作业
    @RequestMapping("/homework/my.html")
    public String stuHomeworkMyHtml(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute(WebConstant.SESSION_KEY_USER);
        List<StuHomework> stuHomeworkList = stuHomeworkService.selectByStudentId(student.getId());
        model.addAttribute("stuHomeworkList", stuHomeworkList);
        return "/student/homework_my";
    }

    // 修改作业
    @RequestMapping("/homework/edit/{id}.html")
    public String editHomeworkHtml (@PathVariable("id") int id, Model model) {
        StuHomework stuHomework = stuHomeworkService.selectById(id);
        model.addAttribute("stuHomework", stuHomework);
        return student + "/homework_edit";
    }

    @ResponseBody
    @RequestMapping("/homework/edit")
    public Map<String, Object> editStuHomework (@RequestBody StuHomework stuHomework) {
        stuHomeworkService.updateByPrimaryKeySelective(stuHomework);
        return new ResponseBean().getResponseMap();
    }

}