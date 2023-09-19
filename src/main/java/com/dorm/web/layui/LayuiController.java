package com.dorm.web.layui;

import com.dorm.role.admin.entity.Admin;
import com.dorm.role.admin.service.imp.AdminService;
import com.dorm.utils.Tools;
import com.dorm.web.base.BaseController;
import com.dorm.web.base.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LayuiController extends BaseController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/01-layui")
    public ModelAndView layui(PageParam pageParam, @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = this.getModeAndView();
        mv.setViewName("layuihtml/01-layui");
        return mv;
    }
}
