package com.XiTwo_.web.Student_;

import com.XiTwo_.Mapper.StudentMapper;
import com.XiTwo_.Mapper.UserMapper;
import com.XiTwo_.pojo.Student;
import com.XiTwo_.pojo.User;
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

@WebServlet(value = "/AddStudent")
public class AddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String numberStr = request.getParameter( "number" );
        int number =Integer.parseInt(numberStr == null || "".equals(numberStr)?"0":numberStr);
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birth = request.getParameter("birth");
        String nation = request.getParameter("nation");
        String grade = request.getParameter("grade");

        Student student = new Student(number, name, sex, birth, nation, grade);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        Student stu = mapper.selectStudentByNumber(number);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if(stu == null){
            writer.write("添加成功\n");
            mapper.addStudent(student);
            sqlSession.commit();
            sqlSession.close();
        }else{
            writer.write("添加失败，该学生已存在");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
