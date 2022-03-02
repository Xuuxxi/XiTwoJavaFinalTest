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

@WebServlet(value = "/SelectTeacherByPage")
public class SelectTeacherByPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageT = request.getParameter("currentPage");
        String pageSizeT = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageT);
        int pageSize = Integer.parseInt(pageSizeT);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        List<Teacher> teachers = mapper.selectTeacherByPage(begin, size);

        int total = mapper.selectTotalCount();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        Class_Mapper mapper1 = sqlSession.getMapper(Class_Mapper.class);

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

        int count = total / size;
        if(total % size != 0) count = count + 1;


        writer.write("<br>");
        if(currentPage > 1) writer.write("<a href=\"/XiTwo/SelectTeacherByPage?currentPage="+(currentPage-1)+"&pageSize="+pageSize+"\">上一页</a>");
        writer.write("  第" + currentPage + "页，总共" +  count + "页 ");
        if(currentPage < count) writer.write("<a href=\"/XiTwo/SelectTeacherByPage?currentPage="+(currentPage+1)+"&pageSize="+pageSize+"\">下一页</a>");

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
