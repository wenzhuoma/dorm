package com.dorm.cms.quizResult.dao;
import com.dorm.base.dao.BaseDao;
import com.dorm.cms.quizResult.entity.QuizResult;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuizResultDao extends BaseDao<QuizResult> {

    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "findById")
    public QuizResult findById(@Param("id") String id);

    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "findList")
    public List<QuizResult> findList();

    @InsertProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "save")
    public void save(QuizResult article);

    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state1count")
    public long state1count(@Param("userName") String userName);
    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state2count")
    public long state2count(@Param("userName") String userName);
    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state3count")
    public long state3count(@Param("userName") String userName);
    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state4count")
    public long state4count(@Param("userName") String userName);
    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state5count")
    public long state5count(@Param("userName") String userName);
    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state6count")
    public long state6count(@Param("userName") String userName);
    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state7count")
    public long state7count(@Param("userName") String userName);
    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state8count")
    public long state8count(@Param("userName") String userName);
    @SelectProvider(type = com.dorm.cms.quizResult.provider.QuizResultSqlProvider.class, method = "state9count")
    public long state9count(@Param("userName") String userName);
}
