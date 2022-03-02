package com.XiTwo_.Mapper;

import com.XiTwo_.pojo.Teacher;
import com.XiTwo_.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TeacherMapper {

    @Insert("insert into tb_teacher (number,name, sex, birth, nation) VALUES (#{number}, #{name}, #{sex}, #{birth}, #{nation})")
    void addTeacher(Teacher Teacher);

    @Delete("delete from tb_teacher where number = #{number}")
    void deleteTeacherByNumber(Integer number);

    @Select("select * from tb_teacher")
    List<Teacher> selectAllTeacher();

    @Select("select * from tb_teacher where number = #{number}")
    Teacher selectTeacherByNumber(Integer number);

    @Update("UPDATE tb_teacher SET name=#{name} ,sex=#{sex} ,birth=#{birth} ,nation=#{nation} WHERE number=#{number};")
    void updateTeacher(Teacher teacher);

    @Select("select * from tb_teacher limit #{begin}, #{size}")
    List<Teacher> selectTeacherByPage(@Param("begin") int begin, @Param("size") int size);

    @Select("select count(*) from tb_Teacher")
    int selectTotalCount();
}
