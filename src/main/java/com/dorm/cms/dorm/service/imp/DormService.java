package com.dorm.cms.dorm.service.imp;

import com.dorm.cms.dorm.dao.DormDao;
import com.dorm.cms.dorm.entity.Dorm;
import com.dorm.cms.dorm.service.IDormService;
import com.dorm.cms.student.entity.Student;
import com.dorm.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DormService implements IDormService {

    @Resource
    private DormDao dormDao;

    public long count()throws Exception {
        return  dormDao.count();
    }

    public long count2()throws Exception {
        return  dormDao.count2();
    }

    public Dorm findById(String id)throws Exception {
        return dormDao.findById(id);
    }

    public List<Dorm> findList()throws Exception {
        return dormDao.findList();
    }

    public void save(Dorm article)throws Exception {
        dormDao.save(article);
    }

    public void update(Dorm article)throws Exception {
        dormDao.update(article);
    }
    public void deleteByid(String id)throws Exception {
        dormDao.deleteByid(id);
    }

    public List<Dorm> findByPage(int currentPage, int pageSize, String query) {
        List<Dorm> list = new ArrayList<Dorm>();
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = dormDao.findListByQuery("%" + query + "%");
        } else {
            list = dormDao.findList();
        }
        PageInfo<Dorm> pageInfo=new PageInfo<Dorm>(list);
        return pageInfo.getList();
    }


    public List<Dorm> findByPageString(int currentPage, int pageSize, String name, int gender, int count) throws Exception {
        List<Dorm> list = new ArrayList<Dorm>();
        PageHelper.startPage(currentPage, pageSize);
        //拼接查询语句

        list = dormDao.findListByString(name, gender, count);
        PageInfo<Dorm> pageInfo=new PageInfo<Dorm>(list);
        return pageInfo.getList();
    }

    public List<Dorm> findByPage(int currentPage, int pageSize) {
        List<Dorm> list = new ArrayList<Dorm>();
        PageHelper.startPage(currentPage, pageSize);
        list = dormDao.indexList();
        PageInfo<Dorm> pageInfo=new PageInfo<Dorm>(list);
        return pageInfo.getList();
    }

    public long state0count()throws Exception {
        return  dormDao.state0count();
    }
    public long state1count()throws Exception {
        return  dormDao.state1count();
    }
    public long state2count()throws Exception {
        return  dormDao.state2count();
    }
}
