package com.dorm.cms.dorm.dao;

import com.dorm.base.dao.BaseDao;
import com.dorm.cms.dorm.entity.Dorm;
import com.dorm.cms.dorm.provider.DormSqlProvider;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.provider.StudentSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DormDao extends BaseDao<Dorm> {

    @SelectProvider(type = DormSqlProvider.class, method = "findById")
    public Dorm findById(@Param("id") String id);

    @SelectProvider(type = DormSqlProvider.class, method = "findList")
    public List<Dorm> findList();

    @SelectProvider(type = DormSqlProvider.class, method = "indexList")
    public List<Dorm> indexList();

    @SelectProvider(type = DormSqlProvider.class, method = "findListByQuery")
    public List<Dorm> findListByQuery(@Param("query") String query);

    @SelectProvider(type = DormSqlProvider.class, method = "findListByString")
    public List<Dorm> findListByString(@Param("cname") String cname, @Param("cgender") int cgender, @Param("ccount") int ccount);

    @InsertProvider(type = DormSqlProvider.class, method = "save")
    public void save(Dorm article);

    @UpdateProvider(type = DormSqlProvider.class, method = "update")
    public void update(Dorm article);

    @UpdateProvider(type = DormSqlProvider.class, method = "deleteByid")
    public void deleteByid(@Param("id") String id);

    @SelectProvider(type = DormSqlProvider.class, method = "count")
    public long count();

    @SelectProvider(type = DormSqlProvider.class, method = "count2")
    public long count2();

    @SelectProvider(type = DormSqlProvider.class, method = "state0count")
    public long state0count();
    @SelectProvider(type = DormSqlProvider.class, method = "state1count")
    public long state1count();
    @SelectProvider(type = DormSqlProvider.class, method = "state2count")
    public long state2count();

}
