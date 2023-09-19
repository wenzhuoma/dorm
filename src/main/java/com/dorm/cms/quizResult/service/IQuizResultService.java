package com.dorm.cms.quizResult.service;


import com.dorm.cms.quizResult.entity.QuizResult;

import java.util.List;

public interface IQuizResultService {

    public QuizResult findById(String id)throws Exception;

    public List<QuizResult> findList()throws Exception;

    public void save(QuizResult article)throws Exception;

}
