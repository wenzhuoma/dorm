package com.dorm.cms.quiz.provider;

import org.springframework.stereotype.Component;
@Component
public class QuizSqlProvider {

    public String findById() {
        return "SELECT * FROM t_cms_quiz WHERE ID = #{id}";
    }

    public String findList(){
        return "SELECT * FROM t_cms_quiz ORDER BY ADD_TIME DESC";
    }

    public String findByNum(){
        return "SELECT * FROM t_cms_quiz WHERE NUM = #{num}";
    }


}
