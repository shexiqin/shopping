package controller;

import bean.TbNotice;
import com.alibaba.fastjson.JSONObject;
import com.jspsmart.upload.Request;
import service.NoticeService;
import util.FileUploadUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NoticeController extends HttpServlet {

    FileUploadUtil fileUploadUtil = new FileUploadUtil();
    NoticeService noticeService = new NoticeService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat=request.getParameter("doWhat");
        //业务划分
        //添加订单
        if("addNotice".equals(doWhat)){
            addNotice(request,response);
        //分页查看公告列表
        }else if ("noticeList".equals(doWhat)){
            noticeList(request,response);
        //公告详细
        }else if ("noticeView".equals(doWhat)){
            noticeView(request,response);
        //更新公告
        }else if ("updateNotice".equals(doWhat)){
            updateNotice(request,response);
        //跳转更新公告页面
        }else if ("toUpdateNotice".equals(doWhat)){
            toUpdateNotice(request,response);
        //删除公告
        }else if ("deleteNotice".equals(doWhat)){
            deleteNotice(request,response);
        //前台公告详情
        }else if("noticeDetail".equals(doWhat)){
            noticeDetail(request,response);
        //前台公告
        }else if ("showNotice".equals(doWhat)){
            showNotice(request,response);

        }
    }


    /**
     * 前台公告
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void showNotice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TbNotice> noticeList =  noticeService.getNoticeListLimit();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("noticeList",noticeList);
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject);
        writer.close();
    }


    /**
     * 前台公告详情
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void noticeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        TbNotice notice = noticeService.getNoticeById(id);
        request.setAttribute("notice",notice);
        request.getRequestDispatcher("/noticeInformation.jsp").forward(request,response);
    }


    /**
     * 删除公告
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        noticeService.delNoticeById(id);
        request.setAttribute("msg","<script>alert('删除成功');</script>");
        request.getRequestDispatcher("/noticeManageList.jsp").forward(request,response);
    }


    /**
     * 跳转更新公告页面
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void toUpdateNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        TbNotice notice = noticeService.getNoticeById(id);
        request.setAttribute("notice",notice);
        request.getRequestDispatcher("/noticeMessageModify.jsp").forward(request,response);
    }


    /**
     * 更新公告
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void updateNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> map = fileUploadUtil.uploadFile(getServletConfig(), request, response);
        String path = (String)map.get("path");
        Request req = (Request)map.get("req");
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String creatDate = req.getParameter("creatDate");
        String endDate = req.getParameter("endDate");
        String content = req.getParameter("content");
        TbNotice tbNotice = null;
        if(path == null){
            tbNotice = new TbNotice(id,title,content,creatDate,endDate,null);
        }else {
            tbNotice = new TbNotice(id,title,content,creatDate,endDate,path);
        }
        int a =noticeService.modifyNotice(tbNotice);
        if (a>0){
            request.setAttribute("msg","<script>alert('修改成功');</script>");
            request.getRequestDispatcher("noticeManageList.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","<script>alert('修改失败');</script>");
            toUpdateNotice(request,response);
        }
    }


    /**
     * 公告明细
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void noticeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        TbNotice notice = noticeService.getNoticeById(id);
        request.setAttribute("notice",notice);
        request.getRequestDispatcher("/noticeMessageView.jsp").forward(request,response);
    }


    /**
     * 异步分页查询公告列表
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void noticeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int eachPageCount = 5;
        //获取当前页
        String currentPageStr=request.getParameter("currentPage");
        int currentPage=1;
        if(currentPageStr!=null){
            currentPage=Integer.parseInt(currentPageStr);
        }
        //总条数
        int totalCount=noticeService.getNoticeCount();
        //总页数
        int totalPage=totalCount%eachPageCount==0?totalCount/eachPageCount:totalCount/eachPageCount+1;

        //当前页面的第一条
        int start=(currentPage-1)*eachPageCount+1;
        //当前页面最后一条
        int end=start+eachPageCount-1;
        List<TbNotice> noticeList = noticeService.getNoticeList(start,end);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("noticeList",noticeList);
        jsonObject.put("totalPage",totalPage);
        jsonObject.put("currentPage",currentPage);

        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();

    }


    /**
     * 添加订单
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> map = fileUploadUtil.uploadFile(getServletConfig(), request, response);
        String path = (String)map.get("path");
        Request req = (Request)map.get("req");
        String title = req.getParameter("title");
        String endDate = req.getParameter("endDate");
        String content = req.getParameter("content");
        String dateStr = getDateStr(new Date());
        TbNotice tbNotice = new TbNotice(null,title,content,dateStr,endDate,path);
        int a =noticeService.addNotice(tbNotice);
        if (a>0){
            request.setAttribute("msg","<script>alert('公告添加成功');</script>");
            request.getRequestDispatcher("/noticeManageList.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","<script>alert('公告添加失败');</script>");
            request.getRequestDispatcher("/toNoticeManageAdd.jsp").forward(request,response);
        }


    }


    /**
     * 获取格式化后的日期   pattern:yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return String字符串
     */
    public String getDateStr(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }

}
