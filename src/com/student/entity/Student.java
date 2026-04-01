package com.student.entity;

import java.time.LocalDateTime;

public class Student {
    private Integer id;//学生ID
    private String studentId;//学号
    private String name;//学生姓名
    private String className;//班级
    private Double score;//成绩
    private LocalDateTime createdAt;//创建时间

    //无参构造方法
    public Student(){
    }

    //有参构造方法
    public Student(String studentId, String name, String className, Double score ) {
        this.studentId = studentId;
        this.name = name;
        this.className = className;
        this.score = score;
    }
    //Getter和Settter
    public Integer getId () {
        return id;
    }
    public void setId (Integer id){
        this.id = id;
    }

    public String getStudentId () {
        return studentId;
    }
    public void setStudentId (String studentId){
        this.studentId = studentId;
    }

    public String getName () {
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

    public String getClassName () {
        return className;
    }
    public void setClassName (String className){
        this.className = className;
    }

    public Double getScore () {
        return score;
    }
    public void setScore (Double score){
        this.score = score;
    }

    public LocalDateTime getCreatedAt () {
        return createdAt;
    }

    public void setCreatedAt (LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    // 重写 toString 方法，方便打印
    @Override
    public String toString() {
        return String.format("| %3d | %s | %s | %s | %.2f |",
                id, studentId, name, className, score);
    }
}
