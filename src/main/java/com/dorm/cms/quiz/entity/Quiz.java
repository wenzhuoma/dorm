package com.dorm.cms.quiz.entity;

import com.dorm.base.entity.BaseDomain;

@SuppressWarnings("serial")
public class Quiz extends BaseDomain {
    private int num;
    private int type;
    private String message;

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
