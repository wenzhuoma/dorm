package com.dorm.cms.dorm.service;


import com.dorm.cms.dorm.entity.Dorm;

import java.util.List;

public interface IDormService {

    public long count()throws Exception;

    public Dorm findById(String id)throws Exception;

    public List<Dorm> findList()throws Exception;

    public void save(Dorm article)throws Exception;

    public void update(Dorm article)throws Exception;

    public void deleteByid(String id)throws Exception;

    public List<Dorm> findByPage(int currentPage, int pageSize, String query)throws Exception;
    public List<Dorm> findByPageString(int currentPage, int pageSize, String name, int gender, int count)throws Exception;

    public List<Dorm> findByPage(int currentPage, int pageSize)throws Exception;
}
