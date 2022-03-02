package com.XiTwo_.pojo;

public class Student {
    private Integer number;
    private Integer id;
    private String name;
    private String sex;
    private String birth;
    private String nation;
    private String grade;

    public Student(Integer number, String name, String sex, String birth, String nation, String grade) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.nation = nation;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", nation='" + nation + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
