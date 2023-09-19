package com.dorm.cms.student.provider;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

@Component
public class StudentSqlProvider {

    public String count(){ return "SELECT count(*) FROM t_cms_student WHERE DELETE_STATUS=0 AND STATE=1"; }

    public String count2(){ return "SELECT count(*) FROM t_cms_student WHERE DELETE_STATUS=0 "; }

    public String findById() {
        return "SELECT * FROM t_cms_student WHERE ID = #{id} AND DELETE_STATUS=0";
    }

    public String findList(){
        return "SELECT * FROM t_cms_student WHERE DELETE_STATUS=0 ORDER BY NUM";
    }

    public String indexList(){
        return "SELECT * FROM t_cms_student WHERE DELETE_STATUS=0 ORDER BY NUM";
    }

    public String findListByQuery(String cname, int cgrade, int cclazz, int cgender, String cdormId, int cchara){
       String sql = new SQL(){{
            SELECT("*");
            FROM("t_cms_student");
            if(!(cname == null || "".equals(cname))) {
                WHERE("student_name like '%" + cname + "%'");
            }
            if(cgrade!=0){
                WHERE("grade="+cgrade);
            }
           if(cclazz!=0){
               WHERE("clazz="+cclazz);
           }
           if(cgender==0 || cgender==1){
               WHERE("gender="+cgender);
           }
           if(!(cdormId == null || "".equals(cdormId) || "0".equals(cdormId))){
               WHERE("dorm_id='"+cdormId+"'");
           }
           if(cchara!=0){
               WHERE("chara="+cchara);
           }
           WHERE("DELETE_STATUS = 0");
        }}.toString();
       sql = sql + "order by num";
       System.out.println("findListByQuery--"+sql);
       return sql;

        //return "SELECT * FROM t_cms_student WHERE DELETE_STATUS=0 AND STATE=1 #{query} ORDER BY ADD_TIME DESC";
    }

    public String update(){
        return "UPDATE t_cms_student SET MODIFY_USER_ID=#{modifyUserId},MODIFY_TIME=NOW(),STUDENT_NAME=#{studentName}, " +
                "GENDER=#{gender},AGE=#{age},GRADE=#{grade},CLAZZ=#{clazz},DORM_ID=#{dormId},DORM_NAME=#{dormName},PHONE=#{phone},EMAIL=#{email}," +
                "CHARA=#{chara} WHERE id=#{id}";
    }

    public String save(){
        return "INSERT INTO t_cms_student(ID,ADD_USER_ID,ADD_TIME,NUM,STUDENT_NAME,GENDER,AGE,GRADE,CLAZZ,DORM_ID,DORM_NAME,PHONE,EMAIL,STUDENT_INTRO) " +
                "VALUES(#{id},#{addUserId},NOW(),#{num},#{studentName},#{gender},#{age},#{grade},#{clazz},#{dormId},#{dormName},#{phone},#{email},#{studentIntro})";
    }


    public String saveChara(){
        return "UPDATE t_cms_student SET s1=#{s1},s2=#{s2},s3=#{s3},s4=#{s4},s5=#{s5},s6=#{s6},s7=#{s7},s8=#{s8},s9=#{s9},chara=#{chara} WHERE id=#{id}";
    }

    public String deleteByid(){
        return "UPDATE t_cms_student SET DELETE_STATUS=1 WHERE id=#{id}";
    }


    public String state0count(){ return "SELECT count(*) FROM t_cms_student WHERE DELETE_STATUS=0 AND STATE=0"; }
    public String state1count(){ return "SELECT count(*) FROM t_cms_student WHERE DELETE_STATUS=0 AND STATE=1"; }
    public String state2count(){ return "SELECT count(*) FROM t_cms_student WHERE DELETE_STATUS=0 AND STATE=2"; }

}
