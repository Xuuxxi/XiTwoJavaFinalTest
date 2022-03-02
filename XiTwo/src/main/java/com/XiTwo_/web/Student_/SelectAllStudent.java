package com.XiTwo_.web.Student_;

import com.XiTwo_.Mapper.Class_Mapper;
import com.XiTwo_.Mapper.StudentMapper;
import com.XiTwo_.pojo.Class_;
import com.XiTwo_.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/SelectAllStudent")
public class SelectAllStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Class_Mapper mapper1 = sqlSession.getMapper(Class_Mapper.class);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        List<Student> students = mapper.selectAllStudent();

        for(Student student : students){
            writer.write("学号 " + student.getNumber() + " 姓名 " + student.getName() + " 性别 " + student.getSex() + " 生日 " + student.getBirth() + " 民族 " + student.getNation() + " 年级 " + student.getGrade() + "<br>");
            List<Class_> clas = mapper1.selectClass_ByStudentNumber(student.getNumber());
            if(!clas.isEmpty()){
                writer.write("该学生学习状况如下" + "<br>");

                for(Class_ cla : clas){
                    writer.write(cla.getName() + ":" + cla.getScore() + "<br>");
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
