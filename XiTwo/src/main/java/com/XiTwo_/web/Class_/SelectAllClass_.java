package com.XiTwo_.web.Class_;

import com.XiTwo_.Mapper.Class_Mapper;
import com.XiTwo_.pojo.Class_;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/SelectAllClass_")
public class SelectAllClass_ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Class_Mapper mapper = sqlSession.getMapper(Class_Mapper.class);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        List<Class_> clas = mapper.selectAllClass_();

        sqlSession.close();

        for(Class_ cla : clas){
            writer.write("id " + cla.getId() + " 课程名称 " + cla.getName() + " 教师号 " + cla.getTeacherNumber() + " 学号 " + cla.getStudentNumber() + " 成绩 " + cla.getStudentNumber() + "<br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
