package com.dorm.web.dmanager;

import com.dorm.cms.dorm.entity.Dorm;
import com.dorm.cms.dorm.entity.DormAndStudent;
import com.dorm.cms.dorm.service.imp.DormService;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.service.imp.StudentService;
import com.dorm.role.user.service.imp.UserService;
import com.dorm.utils.Tools;
import com.dorm.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dmanager")
public class PortalController extends BaseController {
    @Autowired
    DormService dormService;
    @Autowired
    StudentService studentService;
    @RequestMapping("/portal")
    public ModelAndView portal()throws Exception{
        ModelAndView mv = this.getModeAndView();
        mv.setViewName("portal/portalTagCloud");
        return mv;
    }
    @RequestMapping("/statistics")
    public ModelAndView statistics()throws Exception{
        ModelAndView mv = this.getModeAndView();
        mv.setViewName("portal/statistics");
        return mv;
    }

    @RequestMapping("/studentPortal")
    public ModelAndView studentPortal()throws Exception{
        ModelAndView mv = this.getModeAndView();
        mv.setViewName("portal/studentPortal");
        return mv;
    }
    public ModelAndView main() throws Exception {
        //获得女宿舍清单，包含学生信息
        List<DormAndStudent> fdsl = new ArrayList<DormAndStudent>();
        List<Dorm> fdormList = dormService.findByPageString(1,200,"",1,0);
        if(!fdormList.isEmpty()){
            for(int i=0;i<fdormList.size();i++){
                Dorm dorm = fdormList.get(i);
                List<Student> studentList = new ArrayList<Student>();
                studentList = studentService.findByPage(1,100,"",0,0,1,dorm.getId(),0);
                DormAndStudent ds = new DormAndStudent();
                ds.setDormId(dorm.getId());
                ds.setDormName(dorm.getDormName());
                if(studentList.size()>=1 && Tools.notEmpty(studentList.get(0))) {
                    ds.setStudentId1(studentList.get(0).getId());
                    ds.setStudentName1(studentList.get(0).getStudentName());
                }
                if(studentList.size()>=2 && Tools.notEmpty(studentList.get(1))) {
                    ds.setStudentId2(studentList.get(1).getId());
                    ds.setStudentName2(studentList.get(1).getStudentName());
                }
                if(studentList.size()>=3 && Tools.notEmpty(studentList.get(2))) {
                    ds.setStudentId3(studentList.get(2).getId());
                    ds.setStudentName3(studentList.get(2).getStudentName());
                }
                if(studentList.size()>=4 && Tools.notEmpty(studentList.get(3))) {
                    ds.setStudentId4(studentList.get(3).getId());
                    ds.setStudentName4(studentList.get(3).getStudentName());
                }
                fdsl.add(ds);
            }
        }
        //获得男宿舍清单，包含学生信息
        List<DormAndStudent> mdsl = new ArrayList<DormAndStudent>();
        List<Dorm> mdormList = dormService.findByPageString(1,200,"",0,0);
        if(!fdormList.isEmpty()){
            for(int i=0;i<mdormList.size();i++){
                Dorm dorm = fdormList.get(i);
                List<Student> studentList = new ArrayList<Student>();
                studentList = studentService.findByPage(1,100,"",0,0,0,dorm.getId(),0);
                DormAndStudent ds = new DormAndStudent();
                ds.setDormId(dorm.getId());
                ds.setDormName(dorm.getDormName());
                if(studentList.size()>=1 && Tools.notEmpty(studentList.get(0))) {
                    ds.setStudentId1(studentList.get(0).getId());
                    ds.setStudentName1(studentList.get(0).getStudentName());
                }
                if(studentList.size()>=2 && Tools.notEmpty(studentList.get(1))) {
                    ds.setStudentId2(studentList.get(1).getId());
                    ds.setStudentName2(studentList.get(1).getStudentName());
                }
                if(studentList.size()>=3 && Tools.notEmpty(studentList.get(2))) {
                    ds.setStudentId3(studentList.get(2).getId());
                    ds.setStudentName3(studentList.get(2).getStudentName());
                }
                if(studentList.size()>=4 && Tools.notEmpty(studentList.get(3))) {
                    ds.setStudentId4(studentList.get(3).getId());
                    ds.setStudentName4(studentList.get(3).getStudentName());
                }
                mdsl.add(ds);
            }
        }
        ModelAndView mv = this.getModeAndView();
        mv.addObject("fdsl",fdsl);
        mv.addObject("mdsl",mdsl);
        //传div换行信息
        String divTag = "<div class=\"layui-row layui-col-space1\">";
        mv.addObject("divTag",divTag);

        mv.setViewName("portal/portal");
        return mv;
    }



}
