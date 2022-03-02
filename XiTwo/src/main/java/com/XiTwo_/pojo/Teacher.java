package com.XiTwo_.pojo;

public class Teacher {
    private Integer id;
    private Integer number;
    private String name;
    private String sex;
    private String birth;
    private String nation;

    public Teacher(Integer number, String name, String sex, String birth, String nation) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", nation='" + nation + '\'' +
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
}
