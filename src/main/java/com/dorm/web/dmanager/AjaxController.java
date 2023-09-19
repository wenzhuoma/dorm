package com.dorm.web.dmanager;

import com.dorm.cms.dorm.entity.Dorm;
import com.dorm.cms.dorm.service.imp.DormService;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.service.imp.StudentService;
import com.dorm.utils.Tools;
import com.dorm.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dmanager")
public class AjaxController extends BaseController {
    @Autowired
    DormService dormService;
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/confirmAssign",method = RequestMethod.POST)
    public String confirmAssign(HttpServletRequest request, String dormId) {
        try {
            if (Tools.isEmpty(dormId)) {
                dormId = "";
            }
            //获得dorm列表
            List<Dorm> dormList = (List<Dorm>) request.getSession().getAttribute("assignDorm");
            if (Tools.isEmpty(dormList)) {
                dormList = new ArrayList<Dorm>();
            }
            for (int i = 0; i < dormList.size(); i++) {
                if (dormId.equals(dormList.get(i).getId())) {
                    List<Student> studentList = dormList.get(i).getStudents();
                    for (int j = 0; j < studentList.size(); j++) {
                        studentService.update(studentList.get(j));
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(request.getParameter("dormId"));
        return "确认成功！";
    }
}
