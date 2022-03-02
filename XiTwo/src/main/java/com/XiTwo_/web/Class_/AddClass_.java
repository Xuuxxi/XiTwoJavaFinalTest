package com.XiTwo_.web.Class_;

import com.XiTwo_.Mapper.Class_Mapper;
import com.XiTwo_.pojo.Class_;
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

@WebServlet(value = "/AddClass_")
public class AddClass_ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String teacherNumberStr = request.getParameter( "teacherNumber" );
        int teacherNumber =Integer.parseInt(teacherNumberStr == null || "".equals(teacherNumberStr)?"0":teacherNumberStr);
        String studentNumberStr = request.getParameter( "studentNumber" );
        int studentNumber =Integer.parseInt(studentNumberStr == null || "".equals(studentNumberStr)?"0":studentNumberStr);
        String name = request.getParameter("name");
        String score = request.getParameter("score");

        Class_ class_ = new Class_(teacherNumber, studentNumber, name, score);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Class_Mapper mapper = sqlSession.getMapper(Class_Mapper.class);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        writer.write("添加成功\n");
        mapper.addClass_(class_);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
