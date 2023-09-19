package com.dorm.web.dmanager;

import com.dorm.cms.dorm.entity.Dorm;
import com.dorm.cms.dorm.service.imp.DormService;
import com.dorm.cms.student.dao.StudentDao;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.service.imp.StudentService;
import com.dorm.role.admin.entity.Admin;
import com.dorm.role.admin.service.imp.AdminService;
import com.dorm.utils.Tools;
import com.dorm.web.base.BaseController;
import com.dorm.web.base.PageParam;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dmanager")
public class BatchUploadController extends BaseController {

    @Autowired
    DormService dormService;
    @Autowired
    StudentService studentService;
    @Autowired
    AdminService adminService;

    //打开导入页
    @RequestMapping("/batchUpload")
    public ModelAndView batchUploadStudents() throws Exception {
        ModelAndView mv = this.getModeAndView();
        mv.setViewName("upload/batchUpload");
        return mv;
    }

    //导入宿舍
     @RequestMapping(value="/uploadDorm",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    // 不返回json数据就要添加ResponseBody标签
    public Map<String,Object> uploadDorm(@RequestParam("file") MultipartFile  file , HttpServletRequest request)  {
        Map map = new HashMap();
        if(file != null && !file.isEmpty()){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            String path = "E:/dorm" ;
            String xls = path+"/"+fileName;
            File dest = new File(xls);
            if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }

            try {
                file.transferTo(dest); //保存文件
                importDorm(xls);
                map.put("msg","OK");
                map.put("code",200);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put("msg","erro");
                map.put("code",0);
            }catch (Exception e){
                e.printStackTrace();
                map.put("msg","erro");
                map.put("code",0);
            }
        }else{
            map.put("msg","erro");
            map.put("code",0);
        }
        return map;
    }

    //导入学生
    @RequestMapping(value="/uploadStudent",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    // 不返回json数据就要添加ResponseBody标签
    public Map<String,Object> uploadStudent(@RequestParam("file") MultipartFile  file , HttpServletRequest request)  {
        Map map = new HashMap();
        if(file != null && !file.isEmpty()){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            String path = "E:/dorm" ;
            String xls = path+"/"+fileName;
            File dest = new File(xls);
            if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }

            try {
                file.transferTo(dest); //保存文件
                importStudent(xls);
                map.put("msg","OK");
                map.put("code",200);
            } catch (IllegalStateException e) {
                map.put("msg","erro");
                map.put("code",0);
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put("msg","erro");
                map.put("code",0);
            }catch (Exception e){
                e.printStackTrace();
                map.put("msg","erro");
                map.put("code",0);
            }
        }else{
            map.put("msg","erro");
            map.put("code",0);
        }
        return map;
    }

    //加入宿舍
    public void importDorm(String fileName) throws Exception{
        try {
            Workbook rwb=Workbook.getWorkbook(new File(fileName));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println("表的列数："+clos+" 表的行数:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    Dorm dorm = new Dorm();
                    //第一个是列数，第二个是行数
                    String dormName=rs.getCell(j++, i).getContents().trim();//默认最左边编号也算一列 所以这里得j++,宿舍名称
                    dorm.setDormName(dormName);
                    String gender=rs.getCell(j++, i).getContents().trim();//性别
                    if("男".equals(gender)){
                        dorm.setGender(0);
                    }else{
                        dorm.setGender(1);
                    }
                    String manager=rs.getCell(j++, i).getContents().trim();//管理员
                    dorm.setManager(manager);
                    String dormIntro=rs.getCell(j++, i).getContents().trim();//管理员
                    dorm.setDormIntro(dormIntro);

                    List<Dorm> dormList = dormService.findByPageString(1,5000,dorm.getDormName(),2,-1);
                    if(dormList.size()>0){//已存在宿舍名称，更新
                        Dorm dorm0 = dormList.get(0);
                        dorm0.setGender(dorm.getGender());
                        dorm0.setManager(dorm.getManager());
                        dorm0.setDormIntro(dorm.getDormIntro());
                        dormService.update(dorm0);
                    }else{
                        dorm.setId(Tools.getUUID());
                        dormService.save(dorm);
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //加入学生
    public void importStudent(String fileName) throws Exception{
        try {
            Workbook rwb=Workbook.getWorkbook(new File(fileName));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println("表的列数："+clos+" 表的行数:"+rows);
            for (int i = 1; i < rows; i++) {
                    int j = 0;
                    Student stud = new Student();
                    //第一个是列数，第二个是行数
                    String num = rs.getCell(j++, i).getContents().trim();//学号
                    stud.setNum(num);
                    System.out.println(num+"--"+j+"--"+i);

                    String name = rs.getCell(j++, i).getContents().trim();//姓名
                    stud.setStudentName(name);
                    System.out.println(name+"--"+j+"--"+i);
                    String gender = rs.getCell(j++, i).getContents().trim();//性别
                    if ("Male".equals(gender)) {
                        stud.setGender(0);
                    } else {
                        stud.setGender(1);
                    }

                    int grade = Integer.valueOf(rs.getCell(j++, i).getContents().trim());//年级
                    stud.setGrade(grade);
                    System.out.println(grade+"--"+j+"--"+i);

                    int clazz = Integer.valueOf(rs.getCell(j++, i).getContents().trim());//班级
                    stud.setClazz(clazz);
                    System.out.println(clazz+"--"+j+"--"+i);

                    stud.setId(Tools.getUUID());
                    studentService.save(stud);
                    //创建学生帐号
                    Admin admin = new Admin();
                    admin.setId(stud.getId());
                    admin.setName(stud.getNum());//学号为登录名
                    admin.setUserName(stud.getStudentName());
                    admin.setLinkTel(stud.getPhone());
                    admin.setPassword(stud.getNum()+"123");//学生帐号密码默认为123456
                    admin.setState(0);//非管理员
                    adminService.save(admin);


            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}