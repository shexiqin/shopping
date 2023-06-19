package controller;

import bean.TbType;
import com.alibaba.fastjson.JSONObject;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TypeController extends HttpServlet {

    TypeService typeService = new TypeService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat = request.getParameter("doWhat");
        //业务划分
        //查询支付方式
        if("getPayBy".equals(doWhat)){
            getPayBy(request,response);
        //查询运送方式
        }else if ("getCarryBy".equals(doWhat)){
            getCarryBy(request,response);
        //查询支付方式和运送方式
        }else if ("getPayAndCarry".equals(doWhat)){
            getPayAndCarry(request,response);
        }
    }


    /**
     * 查询支付方式和运送方式
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void getPayAndCarry(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TbType> list1 = typeService.getPayBy();
        List<TbType> list2 = typeService.getCarryBy();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payList",list1);
        jsonObject.put("carryList",list2);
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject);
        writer.close();
    }


    /**
     * 查询支付方式
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void getPayBy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TbType> list = typeService.getPayBy();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payList",list);

        PrintWriter writer = response.getWriter();
        writer.println(jsonObject);
        writer.close();
    }


    /**
     * 查询运送方式
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void getCarryBy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TbType> list = typeService.getCarryBy();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("carryList",list);

        PrintWriter writer = response.getWriter();
        writer.println(jsonObject);
        writer.close();
    }

}
