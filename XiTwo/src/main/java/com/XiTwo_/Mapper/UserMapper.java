package com.XiTwo_.Mapper;

import com.XiTwo_.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User selectUser(@Param("username") String username,@Param("password") String password);

    @Select("select * from tb_user where username = #{username}")
    User selectByUsername(String username);

    @Insert("insert into tb_user (username, password) values (#{username},#{password})")
    void addUser(User user);
}
