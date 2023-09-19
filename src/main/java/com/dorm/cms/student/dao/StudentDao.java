package com.dorm.cms.student.dao;

import com.dorm.base.dao.BaseDao;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.provider.StudentSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentDao extends BaseDao<Student> {

    @SelectProvider(type = StudentSqlProvider.class, method = "findById")
    public Student findById(@Param("id") String id);

    @SelectProvider(type = StudentSqlProvider.class, method = "findList")
    public List<Student> findList();

    @SelectProvider(type = StudentSqlProvider.class, method = "indexList")
    public List<Student> indexList();

    @SelectProvider(type = StudentSqlProvider.class, method = "findListByQuery")
    public List<Student> findListByQuery(@Param("cname") String cname,@Param("cgrade") int cgrade, @Param("cclazz") int cclazz,  @Param("cgender") int cgender,  @Param("cdormId") String cdormId,  @Param("cchara") int cchara);

    @InsertProvider(type = StudentSqlProvider.class, method = "save")
    public void save(Student article);

    @InsertProvider(type = StudentSqlProvider.class, method = "saveChara")
    public void saveChara(Student article);

    @UpdateProvider(type = StudentSqlProvider.class, method = "update")
    public void update(Student article);

    @UpdateProvider(type = StudentSqlProvider.class, method = "deleteByid")
    public void deleteByid(@Param("id") String id);

    @SelectProvider(type = StudentSqlProvider.class, method = "count")
    public long count();

    @SelectProvider(type = StudentSqlProvider.class, method = "count2")
    public long count2();

    @SelectProvider(type = StudentSqlProvider.class, method = "state0count")
    public long state0count();
    @SelectProvider(type = StudentSqlProvider.class, method = "state1count")
    public long state1count();
    @SelectProvider(type = StudentSqlProvider.class, method = "state2count")
    public long state2count();

}
