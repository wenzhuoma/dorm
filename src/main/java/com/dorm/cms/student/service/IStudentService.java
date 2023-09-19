package com.dorm.cms.student.service;

import com.dorm.cms.student.entity.Student;

import java.util.List;

public interface IStudentService {

    public long count()throws Exception;

    public Student findById(String id)throws Exception;

    public List<Student> findList()throws Exception;

    public void save(Student article)throws Exception;

    public void saveChara(Student article)throws Exception;

    public void update(Student article)throws Exception;

    public void deleteByid(String id)throws Exception;

    public List<Student> findByPage(int currentPage, int pageSize, String cname, int cgrade, int cclazz, int cgenger, String cdormId, int cchara)throws Exception;

    public List<Student> findByPage(int currentPage, int pageSize)throws Exception;
}
