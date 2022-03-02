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

@WebServlet(value = "/DeleteClass_")
public class DeleteClass_ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Integer id = Integer.valueOf(request.getParameter("id"));

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Class_Mapper mapper = sqlSession.getMapper(Class_Mapper.class);

        Class_ cla = mapper.selectClass_ById(id);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if(cla != null){
            writer.write("删除成功\n");
            mapper.deleteClass_ById(id);
            sqlSession.commit();
            sqlSession.close();
        }else{
            writer.write("删除失败，该课程信息不存在");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
