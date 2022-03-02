package com.XiTwo_.web.Teacher_;

import com.XiTwo_.Mapper.Class_Mapper;
import com.XiTwo_.Mapper.TeacherMapper;
import com.XiTwo_.pojo.Class_;
import com.XiTwo_.pojo.Teacher;
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

@WebServlet(value = "/SelectAllTeacher")
public class SelectAllTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Class_Mapper mapper1 = sqlSession.getMapper(Class_Mapper.class);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        List<Teacher> teachers = mapper.selectAllTeacher();


        for(Teacher teacher : teachers){
            writer.write("教师号 " + teacher.getNumber() + " 姓名 " + teacher.getName() + " 性别 " + teacher.getSex() + " 生日 " + teacher.getBirth() + " 民族 " + teacher.getNation() + "<br>");

            List<Class_> clas = mapper1.selectClass_ByTeacherNumber(teacher.getNumber());

            if (!clas.isEmpty()) {
                writer.write("教师授课情况如下" + "<br>");
                for (Class_ cla : clas) {
                    writer.write(cla.getName() + "<br>");
                }
            }
            writer.write("<br>");
        }
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
