package com.XiTwo_.web.Class_;

import com.XiTwo_.Mapper.Class_Mapper;
import com.XiTwo_.Mapper.Class_Mapper;
import com.XiTwo_.pojo.Class_;
import com.XiTwo_.pojo.Class_;
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

@WebServlet(value = "/SelectClass_ByPage")
public class SelectClass_ByPage extends HttpServlet {
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

        Class_Mapper mapper = sqlSession.getMapper(Class_Mapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        List<Class_> clas = mapper.selectClass_ByPage(begin, size);

        int total = mapper.selectTotalCount();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        Class_Mapper mapper1 = sqlSession.getMapper(Class_Mapper.class);

        for(Class_ cla : clas){
            writer.write("id " + cla.getId() + " 课程名称 " + cla.getName() + " 教师号 " + cla.getTeacherNumber() + " 学号 " + cla.getStudentNumber() + " 成绩 " + cla.getStudentNumber() + "<br>");
        }

        int count = total / size;
        if(total % size != 0) count = count + 1;

        writer.write("<br>");
        if(currentPage > 1) writer.write("<a href=\"/XiTwo/SelectClass_ByPage?currentPage="+(currentPage-1)+"&pageSize="+pageSize+"\">上一页</a>");
        writer.write("  第" + currentPage + "页，总共" +  count + "页 ");
        if(currentPage < count) writer.write("<a href=\"/XiTwo/SelectClass_ByPage?currentPage="+(currentPage+1)+"&pageSize="+pageSize+"\">下一页</a>");

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
