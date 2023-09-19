package com.dorm.cms.student.entity;

import com.dorm.base.entity.BaseDomain;
import com.dorm.cms.dorm.entity.Dorm;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
public class Student extends BaseDomain {
    private String num;
    private String studentName;
    private int gender;
    private int age;
    private int grade;
    private String studentIntro;
    private int clazz;
    private String phone;
    private String email;

    private int s1;//第1型完美主义者
    private int s2;//第2型助人者
    private int s3;//第3型成就者
    private int s4;//第4型艺术型
    private int s5;//第5型智慧型
    private int s6;//第6型忠诚型
    private int s7;//第7型快乐主义型
    private int s8;//第8型领袖型
    private int s9;//第9型和平型
    private int chara;//大分类1.感情中心 2.思想中心3.本能中心

    public int getS1() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }

    public int getS2() {
        return s2;
    }

    public void setS2(int s2) {
        this.s2 = s2;
    }

    public int getS3() {
        return s3;
    }

    public void setS3(int s3) {
        this.s3 = s3;
    }

    public int getS4() {
        return s4;
    }

    public void setS4(int s4) {
        this.s4 = s4;
    }

    public int getS5() {
        return s5;
    }

    public void setS5(int s5) {
        this.s5 = s5;
    }

    public int getS6() {
        return s6;
    }

    public void setS6(int s6) {
        this.s6 = s6;
    }

    public int getS7() {
        return s7;
    }

    public void setS7(int s7) {
        this.s7 = s7;
    }

    public int getS8() {
        return s8;
    }

    public void setS8(int s8) {
        this.s8 = s8;
    }

    public int getS9() {
        return s9;
    }

    public void setS9(int s9) {
        this.s9 = s9;
    }

    public int getChara() {
        return chara;
    }

    public void setChara(int chara) {
        this.chara = chara;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    private String dormId;
    private String dormName;
    // 学生与班级是多对一的关系，这里配置的是双向关联
    @ManyToOne(fetch= FetchType.LAZY,
            targetEntity= Dorm.class
    )
    @JoinColumn(name="dormId",referencedColumnName="id")
    private Dorm dorm;



    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dorm getDorm() {
        return dorm;
    }

    public void setDorm(Dorm dorm) {
        this.dorm = dorm;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentIntro() {
        return studentIntro;
    }

    public void setStudentIntro(String studentIntro) {
        this.studentIntro = studentIntro;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
