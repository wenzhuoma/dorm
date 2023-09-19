package com.dorm.web.dmanager;

import com.dorm.cms.dorm.entity.Dorm;
import com.dorm.cms.dorm.service.imp.DormService;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.service.imp.StudentService;
import com.dorm.role.admin.entity.Admin;
import com.dorm.role.admin.service.imp.AdminService;
import com.dorm.utils.Tools;
import com.dorm.web.base.BaseController;
import com.dorm.web.base.PageParam;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dmanager")
public class StudentController extends BaseController {

    @Autowired
    StudentService studentService;
    @Autowired
    DormService dormService;
    @Autowired
    AdminService adminService;

    @RequestMapping("/studentList")
    public ModelAndView studentList(HttpServletRequest request,PageParam pageParam, @RequestParam(value = "cname", required = false) String cname, @RequestParam(value = "cgrade", required = false) Integer  cgrade, @RequestParam(value = "cclazz", required = false) Integer  cclazz, @RequestParam(value = "cgender", required = false) Integer  cgender, @RequestParam(value = "cdormId", required = false) String  cdormId, @RequestParam(value = "cchara", required = false) Integer  cchara){
        ModelAndView mv = this.getModeAndView();

        if(cname == null){
            cname = "";
        }
        int intcgrade = 0;
        if(cgrade != null){
            intcgrade = cgrade;
        }
        int intcclazz = 0;
        if(cclazz != null){
            intcclazz = cclazz;
        }
        int intcgender = 2;
        if(cgender != null){
            intcgender = cgender;
        }
        if(cdormId == null){
            cdormId = "";
        }
        int intcchara = 0;
        if(cchara != null){
            intcchara = cchara;
        }
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = studentService.findByPage(1, 10000, cname, intcgrade, intcclazz, intcgender, cdormId, intcchara).size();
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

        List<Student> list = studentService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), cname, intcgrade, intcclazz, intcgender, cdormId, intcchara);
        mv.addObject("pageData", list);
        if (Tools.notEmpty(cname)) {
            mv.addObject("cname", cname);
        }
        mv.addObject("cgrade", cgrade);
        mv.addObject("cclazz", cclazz);
        mv.addObject("cgender", cgender);
        mv.addObject("cdormId", cdormId);
        mv.addObject("cchara", cchara);

       /** pageParam.setCount(list.size());
        if (list.size() > pageParam.getPageSize()) {
            pageParam.setSize(list.size() / pageParam.getPageSize());
        } else {
            pageParam.setSize(1);
        }**/

        mv.addObject("pageParam",pageParam);

        //获取宿舍
        try {
            ArrayList dormList = (ArrayList) dormService.findList();
            mv.addObject("dormList",dormList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获得所有满足条件的学生，放入session
        List<Student> assignStudentToDorm = studentService.findByPage(1,2000, cname, intcgrade, intcclazz, intcgender, cdormId, intcchara);
        request.getSession().setAttribute("assignStudentToDorm",assignStudentToDorm);

        mv.setViewName("student/studentList");
        return mv;
    }

    @RequestMapping("/studentAdd")
    public ModelAndView studentAdd(){
        ModelAndView mv = this.getModeAndView();
        mv.addObject("entity",new Student());

        try {
            List<Dorm> dormList = new ArrayList<Dorm>();
            dormList = dormService.findList();
            mv.addObject("dormList", dormList);
        }catch (Exception e){
            e.printStackTrace();
        }

        mv.setViewName("student/studentEdit");
        return mv;
    }

    @RequestMapping("/studentView")
    public ModelAndView studentView(String id){
        ModelAndView mv = this.getModeAndView();
        try {
            Student entity = studentService.findById(id);
            entity.setDormId("1");
            entity.setDormName("d101");
            mv.addObject("entity",entity);

            List<Dorm> dormList = new ArrayList<Dorm>();
            dormList = dormService.findList();
            mv.addObject("dormList", dormList);

        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("student/studentView");
        return mv;
    }

    @RequestMapping("/studentEdit")
    public ModelAndView studentEdit(String id){
        ModelAndView mv = this.getModeAndView();
        try {
            mv.addObject("entity",studentService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            List<Dorm> dormList = new ArrayList<Dorm>();
            dormList = dormService.findList();
            mv.addObject("dormList", dormList);
        }catch (Exception e){
            e.printStackTrace();
        }

        mv.setViewName("student/studentEdit");
        return mv;
    }

    @RequestMapping("/studentSave")
    public String studentSave(HttpServletRequest request, String id){
        Student entity = null;
        String dormName = null;
        try {
            if(Tools.notEmpty(id)){
                entity = studentService.findById(id);
                if(Tools.notEmpty(entity.getDormId())){
                    //补充宿舍名称
                    dormName = dormService.findById(entity.getDormId()).getDormName();
                    entity.setDormName(dormName);
                }

            }else{
                entity = new Student();
            }
            this.bindValidateRequestEntity(request,entity);



            if (Tools.isEmpty(entity.getId())){//id为空表示新增
                entity.setId(Tools.getUUID());
                studentService.save(entity);
                //创建学生帐号
                Admin admin = new Admin();
                admin.setId(entity.getId());
                admin.setName(entity.getNum());//学号为登录名
                admin.setUserName(entity.getStudentName());
                admin.setLinkTel(entity.getPhone());
                admin.setPassword(entity.getNum()+"123");//学生帐号密码默认为123456
                admin.setState(0);//非管理员
                adminService.save(admin);
            }else{//表示修改
                studentService.update(entity);
            }

        } catch (Exception e) {
            System.out.println("-----studentSave:----");
            e.printStackTrace();
        }
        return REDIRECT+"/dmanager/studentList";
    }

    @RequestMapping("/studentDelete")
    public String studentDelete(String id){
        if(Tools.notEmpty(id)){
            try {
                studentService.deleteByid(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return REDIRECT+"/dmanager/studentList";
    }

    //进入更改密码页面
    @RequestMapping("/studentEditPassword")
    public ModelAndView studentEditPassword(HttpServletRequest request, RedirectAttributes redirectAttributes){
        ModelAndView mv = this.getModeAndView();
        //获取登录用户
        Admin user = (Admin) request.getSession().getAttribute("admin");
        user.setPassword("");//页面不显示原密码
        mv.addObject("user", user);
        mv.addObject("pwd", new String());
        mv.addObject("rpwd", new String());
        //mv.addObject("message", new String());
        mv.setViewName("student/studentEditPassword");
        return mv;
    }

    //更改密码
    @RequestMapping("/studentSavePassword")
    public String studentSavePassword(Admin user, String pwd, String rpwd, HttpServletRequest request, RedirectAttributes redirectAttributes){
        try {
            System.out.println("ppwd---"+user.getPassword());
            System.out.println("pwd---"+pwd);
            System.out.println("rpwd---"+rpwd);
            String ppwd = user.getPassword();
            String spwd = adminService.findById(user.getId()).getPassword();
            System.out.println("spwd---"+spwd);
            if (!ppwd.equals(spwd)){
                redirectAttributes.addFlashAttribute("message","原密码不正确！");
                return REDIRECT+"/dmanager/studentEditPassword";
            }
            if(!pwd.equals(rpwd)){
                redirectAttributes.addFlashAttribute("message","两次密码不一致！");
                return REDIRECT+"/dmanager/studentEditPassword";
            }
            user.setPassword(rpwd);//更改后的密码
            adminService.savePwd(user);
            redirectAttributes.addFlashAttribute("message","密码保存成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return REDIRECT+"/dmanager/studentEditPassword";
    }
}
