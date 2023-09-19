package com.dorm.cms.quizResult.service.imp;

import com.dorm.cms.quizResult.dao.QuizResultDao;
import com.dorm.cms.quizResult.entity.QuizResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuizResultService implements com.dorm.cms.quizResult.quizResult.service.IQuizResultService {

    @Resource
    private QuizResultDao quizResultDao;


    public QuizResult findById(String id)throws Exception {
        return quizResultDao.findById(id);
    }

    public List<QuizResult> findList()throws Exception {
        return quizResultDao.findList();
    }


    public void save(QuizResult article) throws Exception {
        quizResultDao.save(article);
    }
    //分析数据
    public long state1count(String userName)throws Exception {
        return quizResultDao.state1count(userName);
    }
    public long state2count(String userName)throws Exception {
        return quizResultDao.state2count(userName);
    }
    public long state3count(String userName)throws Exception {
        return quizResultDao.state3count(userName);
    }
    public long state4count(String userName)throws Exception {
        return quizResultDao.state4count(userName);
    }
    public long state5count(String userName)throws Exception {
        return quizResultDao.state5count(userName);
    }
    public long state6count(String userName)throws Exception {
        return quizResultDao.state6count(userName);
    }
    public long state7count(String userName)throws Exception {
        return quizResultDao.state7count(userName);
    }
    public long state8count(String userName)throws Exception {
        return quizResultDao.state8count(userName);
    }
    public long state9count(String userName)throws Exception {
        return quizResultDao.state9count(userName);
    }
}
