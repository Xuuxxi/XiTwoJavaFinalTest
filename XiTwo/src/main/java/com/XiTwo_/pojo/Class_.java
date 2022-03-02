package com.XiTwo_.pojo;

public class Class_ {
    private Integer id;
    private Integer teacherNumber;
    private Integer studentNumber;
    private String name;
    private String score;

    public Class_(Integer teacherNumber, Integer studentNumber, String name, String score) {
        this.teacherNumber = teacherNumber;
        this.studentNumber = studentNumber;
        this.name = name;
        this.score = score;
    }

    public Class_(Integer id, Integer teacherNumber, Integer studentNumber, String name, String score) {
        this.id = id;
        this.teacherNumber = teacherNumber;
        this.studentNumber = studentNumber;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Class_{" +
                "teacherNumber=" + teacherNumber +
                ", studentNumber=" + studentNumber +
                ", name='" + name + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(Integer teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
