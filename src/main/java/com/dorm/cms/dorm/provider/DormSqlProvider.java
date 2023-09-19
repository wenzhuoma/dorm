package com.dorm.cms.dorm.provider;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

@Component
public class DormSqlProvider {

    public String count(){ return "SELECT count(*) FROM t_cms_dorm WHERE DELETE_STATUS=0 AND STATE=1"; }

    public String count2(){ return "SELECT count(*) FROM t_cms_dorm WHERE DELETE_STATUS=0 "; }

    public String findById() {
        return "SELECT * FROM t_cms_dorm WHERE ID = #{id} AND DELETE_STATUS=0";
    }

    public String findList(){
        return "SELECT * FROM t_cms_dorm WHERE DELETE_STATUS=0 ORDER BY DORM_NAME";
    }

    public String indexList(){
        return "SELECT * FROM t_cms_dorm WHERE DELETE_STATUS=0 AND STATE=1 ORDER BY DORM_NAME";
    }

    public String findListByQuery(){
        return "SELECT * FROM t_cms_dorm WHERE DELETE_STATUS=0 AND DORM_NAME LIKE #{query,jdbcType=VARCHAR} ORDER BY DORM_NAME";
    }
    public String findListByString(String cname, int cgender, int ccount){

        String sql = new SQL(){{
            SELECT("*");
            FROM("t_cms_dorm");
            if(!(cname == null || "".equals(cname))) {
                WHERE("dorm_name like '%" + cname + "%'");
            }
            if(cgender==0 || cgender==1){
                WHERE("gender="+cgender);
            }
            WHERE("DELETE_STATUS = 0");
        }}.toString();
        sql = sql + "ORDER BY DORM_NAME";
        System.out.println("findListByString--"+sql);
        return sql;

    }
    public String update(){
        return "UPDATE t_cms_dorm SET MODIFY_USER_ID=#{modifyUserId},MODIFY_TIME=NOW(),DORM_NAME=#{dormName}, " +
                "GENDER=#{gender},MANAGER=#{manager},DORM_INTRO=#{dormIntro}" +
                "WHERE id=#{id}";
    }

    public String save(){
        return "INSERT INTO t_cms_dorm(ID,ADD_USER_ID,ADD_TIME,DORM_NAME,GENDER,MANAGER,DORM_INTRO) " +
                "VALUES(#{id},#{addUserId},NOW(),#{dormName},#{gender},#{manager},#{dormIntro})";
    }

    public String deleteByid(){
        return "UPDATE t_cms_dorm SET DELETE_STATUS=1 WHERE id=#{id}";
    }


    public String state0count(){ return "SELECT count(*) FROM t_cms_dorm WHERE DELETE_STATUS=0 AND STATE=0"; }
    public String state1count(){ return "SELECT count(*) FROM t_cms_dorm WHERE DELETE_STATUS=0 AND STATE=1"; }
    public String state2count(){ return "SELECT count(*) FROM t_cms_dorm WHERE DELETE_STATUS=0 AND STATE=2"; }

}
