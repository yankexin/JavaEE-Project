package com.JavaEE.homework.controller;

import com.JavaEE.homework.entity.Homework;
import com.JavaEE.homework.service.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private HomeworkService homeworkService;

    private String student = "student";

    // 首页
    @RequestMapping("/index.html")
    public String indexHtml (Model model) {
        List<Homework> homeworkList = homeworkService.selectByCondition(null);
        model.addAttribute("homeworkList", homeworkList);
        return student + "/index";
    }

    // 根据关键词模糊查询
    @RequestMapping("/search.html")
    public String searchHtml (@RequestParam("search") String search, Model model) {
        List<Homework> homeworkList = homeworkService.selectByCondition(search);
        model.addAttribute("homeworkList", homeworkList);
        return student + "/search";
    }

}
