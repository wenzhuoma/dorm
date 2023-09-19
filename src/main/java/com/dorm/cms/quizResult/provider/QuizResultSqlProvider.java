package com.dorm.cms.quizResult.provider;

import org.springframework.stereotype.Component;
@Component
public class QuizResultSqlProvider {

    public String findById() {
        return "SELECT * FROM t_cms_quizresult WHERE ID = #{id}";
    }

    public String findList(){return "SELECT * FROM t_cms_quizresult ORDER BY ADD_TIME DESC";}

    public String save(){
        return "INSERT INTO t_cms_quizresult(ID,ADD_USER_ID,ADD_TIME,QUIZ_NUM,USER_ID,USER_NAME,RESULT) " +
                "VALUES(#{id},#{addUserId},NOW(),#{quizNum},#{userId},#{userName},#{result})";
    }
    public String state1count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=1"; }
    public String state2count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=2"; }
    public String state3count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=3"; }
    public String state4count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=4"; }
    public String state5count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=5"; }
    public String state6count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=6"; }
    public String state7count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=7"; }
    public String state8count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=8"; }
    public String state9count(){ return "SELECT count(*) FROM t_cms_quiz q,t_cms_quizresult r WHERE q.num=r.quiz_num and r.result=1 and user_name = #{userName} and type=9"; }
}
