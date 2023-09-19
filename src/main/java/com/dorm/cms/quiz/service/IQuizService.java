package com.dorm.cms.quiz.service;

import com.dorm.cms.quiz.entity.Quiz;

import java.util.List;

public interface IQuizService {

    public Quiz findById(String id)throws Exception;

    public Quiz findByNum(int num)throws Exception;

    public List<Quiz> findList()throws Exception;



}
