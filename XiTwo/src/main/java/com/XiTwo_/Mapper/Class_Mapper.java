package com.XiTwo_.Mapper;

import com.XiTwo_.pojo.Class_;
import com.XiTwo_.pojo.Student;
import com.XiTwo_.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Class_Mapper {

    @Insert("insert into tb_class (teacherNumber, studentNumber, name, score) VALUES (#{teacherNumber}, #{studentNumber}, #{name}, #{score})")
    void addClass_(Class_ c);

    @Delete("delete from tb_class where teacherNumber = #{teacherNumber}")
    void deleteClass_ByTeacherNumber(Integer teacherNumber);

    @Delete("delete from tb_class where studentNumber = #{studentNumber}")
    void deleteClass_ByStudentNumber(Integer studentNumber);

    @Delete("delete from tb_class where id = #{id}")
    void deleteClass_ById(Integer id);

    @Select("select * from tb_class")
    List<Class_> selectAllClass_();

    @Select("select * from tb_class where id=#{id}")
    Class_ selectClass_ById(Integer id);

    @Select("select * from tb_class where teacherNumber=#{teacherNumber}")
    List<Class_> selectClass_ByTeacherNumber(Integer teacherNumber);

    @Select("select * from tb_class where studentNumber=#{studentNumber}")
    List<Class_> selectClass_ByStudentNumber(Integer studentNumber);

    @Update("UPDATE tb_class SET teacherNumber = #{teacherNumber}, studentNumber = #{studentNumber}, name = #{name}, score = #{score} WHERE id=#{id};")
    void updateClass_(Class_ class_);

    @Select("select * from tb_class limit #{begin}, #{size}")
    List<Class_> selectClass_ByPage(@Param("begin") int begin, @Param("size") int size);

    @Select("select count(*) from tb_class")
    int selectTotalCount();
}
