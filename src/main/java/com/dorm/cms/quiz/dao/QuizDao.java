package com.dorm.cms.quiz.dao;

import com.dorm.base.dao.BaseDao;
import com.dorm.cms.quiz.entity.Quiz;
import com.dorm.cms.quiz.provider.QuizSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuizDao extends BaseDao<Quiz> {

    @SelectProvider(type = QuizSqlProvider.class, method = "findById")
    public Quiz findById(@Param("id") String id);

    @SelectProvider(type = QuizSqlProvider.class, method = "findList")
    public List<Quiz> findList();

    @SelectProvider(type = QuizSqlProvider.class, method = "findByNum")
    public Quiz findByNum(int num);


}
