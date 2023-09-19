package com.dorm.cms.quiz.service.imp;

import com.dorm.cms.quiz.dao.QuizDao;
import com.dorm.cms.quiz.entity.Quiz;
import com.dorm.cms.quiz.service.IQuizService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuizService implements IQuizService {

    @Resource
    private QuizDao quizDao;


    public Quiz findById(String id)throws Exception {
        return quizDao.findById(id);
    }


    public Quiz findByNum(int num) throws Exception {
        return (Quiz) quizDao.findByNum(num);
    }

    public List<Quiz> findList()throws Exception {
        return quizDao.findList();
    }

}
