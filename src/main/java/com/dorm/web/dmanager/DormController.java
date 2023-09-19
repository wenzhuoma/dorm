package com.dorm.web.dmanager;

import com.alibaba.fastjson.JSONObject;
import com.dorm.cms.dorm.entity.Dorm;
import com.dorm.cms.dorm.service.imp.DormService;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.service.imp.StudentService;
import com.dorm.utils.Tools;
import com.dorm.web.base.BaseController;
import com.dorm.web.base.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dmanager")
public class DormController extends BaseController {

    @Autowired
    DormService dormService;
    @Autowired
    StudentService studentService;

    @RequestMapping("/dormList")
    public ModelAndView dormList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = this.getModeAndView();

        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = dormService.findByPage(1, 10000, query).size();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageParam.setCount(count);
            if (count <= 10) {
                pageParam.setSize(1);
            } else {
                pageParam.setSize(count % 10 == 0 ? count / 10 : count / 10 + 1);
            }
            pageParam.setPageNumber(1);
            pageParam.setPageSize(10);
        }
        List<Dorm> list = dormService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize(), query);
        mv.addObject("pageData", list);
        if (Tools.notEmpty(query)) {
            mv.addObject("query", query);
            pageParam.setCount(list.size());
            if (list.size() > pageParam.getPageSize()) {
                pageParam.setSize(list.size() / pageParam.getPageSize());
            } else {
                pageParam.setSize(1);
            }
        }
        mv.addObject("pageParam", pageParam);


        mv.setViewName("dorm/dormList");
        return mv;
    }

    @RequestMapping("/dormAdd")
    public ModelAndView dormAdd() {
        ModelAndView mv = this.getModeAndView();
        mv.addObject("entity", new Dorm());
        mv.setViewName("dorm/dormEdit");
        return mv;
    }

    @RequestMapping("/dormView")
    public ModelAndView dormView(String id) {
        ModelAndView mv = this.getModeAndView();
        try {
            mv.addObject("entity", dormService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("dorm/dormView");
        return mv;
    }

    @RequestMapping("/dormEdit")
    public ModelAndView dormEdit(String id) {
        ModelAndView mv = this.getModeAndView();
        try {
            mv.addObject("entity", dormService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("dorm/dormEdit");
        return mv;
    }

    @RequestMapping("/dormSave")
    public String dromSave(HttpServletRequest request, String id) {
        Dorm entity = null;
        try {
            if (Tools.notEmpty(id)) {
                entity = dormService.findById(id);
            } else {
                entity = new Dorm();
            }
            this.bindValidateRequestEntity(request, entity);

            if (Tools.isEmpty(entity.getId())) {
                entity.setId(Tools.getUUID());
                dormService.save(entity);
            } else {
                dormService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT + "/dmanager/dormList";
    }

    @RequestMapping("/dormDelete")
    public String dormDelete(String id) {
        if (Tools.notEmpty(id)) {
            try {
                dormService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/dmanager/dormList";
    }

    //进入分配页面
    @RequestMapping("/toDormChoose")
    public ModelAndView toDormChoose(HttpServletRequest request, PageParam pageParam, @RequestParam(value = "cname", required = false) String cname, @RequestParam(value = "cgender", required = false) Integer cgender, @RequestParam(value = "ccount", required = false) String ccount) throws Exception {
        ModelAndView mv = this.getModeAndView();
        try {
            //获得学生列表
            List<Student> studentToDormList = (List<Student>) request.getSession().getAttribute("assignStudentToDorm");
            if (Tools.isEmpty(studentToDormList)) {
                studentToDormList = new ArrayList<Student>();
            }
            System.out.println("学生个数：" + studentToDormList.size());
            mv.addObject("studentToDormList", studentToDormList);

            //统计各类性格学生数
            int c1 = 0;
            int c2 = 0;
            int c3 = 0;
            for (int i = 0; i < studentToDormList.size(); i++) {
                int currentc = studentToDormList.get(i).getChara();
                if (currentc == 1) {
                    c1++;
                } else if (currentc == 2) {
                    c2++;
                } else if (currentc == 3) {
                    c3++;
                }
            }
            mv.addObject("c1", c1);
            mv.addObject("c2", c2);
            mv.addObject("c3", c3);


            //List<Dorm> assignDormList = (List<Student>)
            for (int i = 0; i < studentToDormList.size(); i++) {
            }

            //获得宿舍列表
            if (pageParam.getPageNumber() < 1) {
                pageParam = new PageParam();
                long count = 0;
                try {
                    count = dormService.count2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pageParam.setCount(count);
                if (count <= 10) {
                    pageParam.setSize(1);
                } else {
                    pageParam.setSize(count % 10 == 0 ? count / 10 : count / 10 + 1);
                }
                pageParam.setPageNumber(1);
                pageParam.setPageSize(10);
            }
            if (cname == null) {
                cname = "";
            }
            int intcgender = 2;
            if (Tools.notEmpty(cgender)) {
                intcgender = cgender;
            }
            int intccount = 0;
            if (!(ccount == null || "".equals(ccount))) {
                intccount = Integer.getInteger(ccount);
            }

            //session中存放dorm列表
            List<Dorm> assignDorm = dormService.findByPageString(1, 500, cname, intcgender, intccount);
            request.getSession().setAttribute("assignDorm", assignDorm);

            mv.addObject("pageData", assignDorm);
            if (Tools.notEmpty(cname)) {
                mv.addObject("cname", cname);
            }
            mv.addObject("cgender", intcgender);
            mv.addObject("ccount", intccount);

            pageParam.setCount(assignDorm.size());
            if (assignDorm.size() > pageParam.getPageSize()) {
                pageParam.setSize(assignDorm.size() / pageParam.getPageSize());
            } else {
                pageParam.setSize(1);
            }

            mv.addObject("pageParam", pageParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("dorm/dormChoose");
        return mv;
    }

    //进入分配完页面
    @RequestMapping("/toDormAssignList")
    public ModelAndView toDormAssignList(HttpServletRequest request, PageParam pageParam, @RequestParam(value = "cname", required = false) String cname, @RequestParam(value = "cgender", required = false) Integer cgender, @RequestParam(value = "ccount", required = false) String ccount) throws Exception {
        ModelAndView mv = this.getModeAndView();
        try {
            //获得学生列表
            List<Student> studentToDormList = (List<Student>) request.getSession().getAttribute("assignStudentToDorm");
            if (Tools.isEmpty(studentToDormList)) {
                studentToDormList = new ArrayList<Student>();
            }
            System.out.println("学生个数：" + studentToDormList.size());
            //获得dorm列表
            List<Dorm> dormList = (List<Dorm>) request.getSession().getAttribute("assignDorm");
            if (Tools.isEmpty(dormList)) {
                dormList = new ArrayList<Dorm>();
            }
            System.out.println("宿舍个数：" + dormList.size());

            //分配宿舍
            for (int i = 0; i < dormList.size(); i++) {
                Dorm currentDorm = dormList.get(i);//当前宿舍
                System.out.println("宿舍名：" + currentDorm.getDormName());
                for (int j = 0; j < studentToDormList.size(); j++) {
                    System.out.println(studentToDormList.get(j).getStudentName() + "--" + studentToDormList.get(j).getDormId());
                    if (currentDorm.getStudents().size() >= 4) {
                        break;
                    }
                    String dormId = studentToDormList.get(j).getDormId();
                    if (!(Tools.isEmpty(dormId) || dormId == "")) {
                        continue;//已分配宿舍的跳过
                    }
                    int chara = 0;
                    if (currentDorm.getStudents().size() > 0) {
                        chara = currentDorm.getStudents().get(0).getChara();
                        if (studentToDormList.get(j).getChara() == chara) {
                            currentDorm.getStudents().add(studentToDormList.get(j));
                            studentToDormList.get(j).setDormId(currentDorm.getId());
                            studentToDormList.get(j).setDormName(currentDorm.getDormName());
                            System.out.println("学生：" + studentToDormList.get(j).getStudentName());
                        }
                    } else {
                        currentDorm.getStudents().add(studentToDormList.get(j));
                        studentToDormList.get(j).setDormId(currentDorm.getId());
                        studentToDormList.get(j).setDormName(currentDorm.getDormName());
                        System.out.println("学生：" + studentToDormList.get(j).getStudentName());
                    }
                }
            }
            for (int i = 0; i < dormList.size(); i++) {
                List<Student> studentList = dormList.get(i).getStudents();
                if (studentList != null) {
                    int nn = 4 - studentList.size();
                    for (int n = nn; n > 0; n--) {
                        studentList.add(new Student());
                    }
                } else {
                    for (int n = 4; n > 0; n--) {
                        studentList.add(new Student());
                    }
                }
                dormList.get(i).setStudents(studentList);
            }
            mv.addObject("dormList", dormList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("dorm/dormAssignList");
        return mv;
    }


}