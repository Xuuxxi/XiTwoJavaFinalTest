package com.XiTwo_.Mapper;

import com.XiTwo_.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    @Insert("insert into tb_student (number,name, sex, birth, nation, grade) VALUES (#{number},#{name}, #{sex}, #{birth}, #{nation}, #{grade})")
    void addStudent(Student student);

    @Delete("delete from tb_student where number = #{number}")
    void deleteStudentByNumber(Integer number);

    @Select("select * from tb_student")
    List<Student> selectAllStudent();

    @Select("select * from tb_student where number = #{number}")
    Student selectStudentByNumber(Integer number);


    //实现模糊搜索，返回所有包含name的结果
    //或者可以直接修改传参 ？
    @Select("select * from tb_student where name like concat('%',#{name},'%')")
    List<Student> selectStudentByName(String name);

    @Update("UPDATE tb_student SET name=#{name} ,sex=#{sex} ,birth=#{birth} ,nation=#{nation} ,grade=#{grade} WHERE number=#{number};")
    void updateStudent(Student student);

    @Select("select * from tb_student limit #{begin}, #{size}")
    List<Student> selectStudentByPage(@Param("begin") int begin,@Param("size") int size);

    @Select("select count(*) from tb_student")
    int selectTotalCount();
}
