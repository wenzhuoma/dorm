package com.dorm.cms.quiz.provider;

import jxl.Sheet;
import jxl.Workbook;

import java.io.File;

public class InputQuizSQL {
    public static void main(String[] args){
        InputQuizSQL.getSql("f:/student_input.xls","t_cms_quiz");
    }
    public static void getSql(String file, String table){
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println("表的列数："+clos+" 表的行数:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String num=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String message=rs.getCell(j++, i).getContents();
                    message = message.replace(".",",");//.无法split，改为，再进行split
                    String[] messagesplit = message.split(",",0);
                    String type=rs.getCell(j++, i).getContents();
                    String id = "quiz" +num;
                    String sql="insert into "+table+" values('"+id+"', null, '2020-09-5 00:00:00',0,null,'2020-09-5 00:00:00',"+num+",'"+messagesplit[1]+"',"+type+");";
                    System.out.println(sql);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
