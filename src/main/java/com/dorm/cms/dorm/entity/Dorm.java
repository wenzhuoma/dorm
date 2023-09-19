package com.dorm.cms.dorm.entity;

import com.dorm.base.entity.BaseDomain;
import com.dorm.cms.student.entity.Student;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SuppressWarnings("serial")
public class Dorm extends BaseDomain {

    private String dormName;

    private int gender;

    private String manager;

    private String dormIntro;

    // 班级与学生是一对多的关联
    @OneToMany(
            fetch= FetchType.LAZY,
            targetEntity= Student.class,
            mappedBy="dorm"
    )
    private List<Student> students = new ArrayList<Student>();

    public String getDormName() {
        return dormName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDormIntro() {
        return dormIntro;
    }

    public void setDormIntro(String dormIntro) {
        this.dormIntro = dormIntro;
    }
}
