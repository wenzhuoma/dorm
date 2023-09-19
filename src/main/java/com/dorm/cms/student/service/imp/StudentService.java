package com.dorm.cms.student.service.imp;

import com.dorm.cms.student.dao.StudentDao;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.service.IStudentService;
import com.dorm.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Resource
    private StudentDao studentDao;

    public long count()throws Exception {
        return  studentDao.count();
    }

    public long count2()throws Exception {
        return  studentDao.count2();
    }

    public Student findById(String id)throws Exception {
        return studentDao.findById(id);
    }

    public List<Student> findList()throws Exception {
        return studentDao.findList();
    }

    public void save(Student article)throws Exception {
        studentDao.save(article);
    }

    public void saveChara(Student article) throws Exception {
        studentDao.saveChara(article);
    }

    public void update(Student article)throws Exception {
        studentDao.update(article);
    }
    public void deleteByid(String id)throws Exception {
        studentDao.deleteByid(id);
    }


    public List<Student> findByPage(int currentPage, int pageSize, String cname, int cgrade, int cclazz, int cgender, String cdormId, int cchara) {
        List<Student> list = new ArrayList<Student>();
        PageHelper.startPage(currentPage, pageSize);
        //拼接查询语句

        list = studentDao.findListByQuery(cname, cgrade, cclazz, cgender, cdormId, cchara);

        /*String query = new String();
        if (Tools.notEmpty(cname)) {
            query = " AND STUDENT_NAME LIKE %" + cname + "%";
        }
        if (cgrade != 0) {
            query = query + " AND GRADE =" + cgrade;
        }
        if ("".equals(query)) {
            list = studentDao.findList();
        } else{
            list = studentDao.findListByQuery(query);
        }
        */
        PageInfo<Student> pageInfo=new PageInfo<Student>(list);
        return pageInfo.getList();
    }

    public List<Student> findByPage(int currentPage, int pageSize) {
        List<Student> list = new ArrayList<Student>();
        PageHelper.startPage(currentPage, pageSize);
        list = studentDao.indexList();
        PageInfo<Student> pageInfo=new PageInfo<Student>(list);
        return pageInfo.getList();
    }

    public long state0count()throws Exception {
        return  studentDao.state0count();
    }
    public long state1count()throws Exception {
        return  studentDao.state1count();
    }
    public long state2count()throws Exception {
        return  studentDao.state2count();
    }
}
