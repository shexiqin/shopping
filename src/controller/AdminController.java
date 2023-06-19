package controller;

import service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminController extends HttpServlet {
    AdminService adminService = new AdminService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat=request.getParameter("doWhat");
        if("adminLogin".equals(doWhat)){
            adminLogin(request,response);
        }
    }

    /**
     * 管理员登录校验
     * @param request  请求
     * @param response  响应
     * @throws ServletException  Servlet异常
     * @throws IOException  IO异常
     */
    private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String manager=request.getParameter("manager");
        String password=request.getParameter("password");
        int count=adminService.login(manager,password);
        if (manager == null || password == null || manager == "" || password == "" ){
            String msg="<script>alert('账号或密码为空')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/adminLogin.jsp").forward(request,response);
        }else {
            if (count>0){
                request.getRequestDispatcher("/noticeManageList.jsp").forward(request,response);
            }else {
                String msg="<script>alert('账号或密码错误，请重新输入！')</script>";
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/adminLogin.jsp").forward(request,response);
            }
        }

    }


}
