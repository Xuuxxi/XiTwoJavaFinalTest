package com.XiTwo_.web.Teacher_;

import com.XiTwo_.Mapper.TeacherMapper;
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

@WebServlet(value = "/UpdateTeacher")
public class UpdateTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String numberStr = request.getParameter( "number" );
        int number =Integer.parseInt(numberStr == null || "".equals(numberStr)?"0":numberStr);
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birth = request.getParameter("birth");
        String nation = request.getParameter("nation");

        Teacher teacher = new Teacher(number, name, sex, birth, nation);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        Teacher tea = mapper.selectTeacherByNumber(number);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if(tea != null){
            writer.write("修改成功\n");
            mapper.updateTeacher(teacher);
            sqlSession.commit();
            sqlSession.close();
        }else{
            writer.write("修改失败，该老师不存在");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
