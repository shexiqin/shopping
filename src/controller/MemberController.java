package controller;

import bean.TbArealink;
import bean.TbMember;
import com.alibaba.fastjson.JSONObject;
import service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberController extends HttpServlet {
    MemberService service = new MemberService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取前台值
        String doWhat = request.getParameter("doWhat");

        //2.业务划分
        //登录业务时
        if ("login".equals(doWhat)){
            login(request,response);
        //注册业务时
        }else if ("regist".equals(doWhat)){
            regist(request,response);
        //获取所有城市
        }else if ("getCity".equals(doWhat)){
            getCity(request,response);
        //会员名校验
        }else if ("checkUserName".equals(doWhat)){
            checkUserName(request,response);
        //密码重置
        }else if ("reSetPassword".equals(doWhat)){
            reSetPassword(request,response);
        //会员管理分页一览
        }else if ("memberList".equals(doWhat)){
            memberList(request,response);
        //更改冻结状态
        }else if ("updateFreeze".equals(doWhat)){
            updateFreeze(request,response);
        //会员详细信息
        }else if ("memberView".equals(doWhat)){
            memberView(request,response);
        //去后台会员管理修改页面
        }else if ("updateMember".equals(doWhat)){
            updateMember(request,response);
        //提交会员管理修改
        }else if ("memberManageModify".equals(doWhat)){
            memberManageModify(request,response);
        //前台会员资料修改
        }else if ("memberModify".equals(doWhat)){
            memberModify(request,response);
        //退出登录
        }else if ("logout".equals(doWhat)){
            logout(request,response);
        }
        

    }


    /**
     * 提交会员管理修改
     * @param request 请求对象
     * @param response 响应对象
     */

    private void memberManageModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String username = request.getParameter("username");
        String truename = request.getParameter("truename");
        String passWord = request.getParameter("password");
        String city = request.getParameter("city");//城市
        String address = request.getParameter("member.address");
        String postCode = request.getParameter("member.postcode");//邮政编码
        String cardNo = request.getParameter("member.cardno");//证件号
        String cardType = request.getParameter("member.cardtype");//证件类型
        String tel = request.getParameter("member.tel");//电话号
        String email = request.getParameter("member.email");//邮箱
        String grade = request.getParameter("member.grade");
        //2.调用业务层做业务处理
        TbMember tbMember = new TbMember();
        tbMember.setId(userId);
        tbMember.setUsername(username);
        tbMember.setTruename(truename);
        tbMember.setPassword(passWord);
        tbMember.setCity(city);
        tbMember.setAddress(address);
        tbMember.setPostcode(postCode);
        tbMember.setCardno(cardNo);
        tbMember.setCardtype(cardType);
        tbMember.setTel(tel);
        tbMember.setEmail(email);
        tbMember.setGrade(grade);
        int i = service.updateMember(tbMember,"后台");
        if (i==0){
            String msg="<script>alert('修改失败')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/toMemberManageModify.jsp").forward(request,response);
        }else {
            String msg="<script>alert('修改成功')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/memberManageList.jsp").forward(request,response);
        }
    }


    /**
     * 前台会员修改
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    private void memberModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String passWord = request.getParameter("password");
        String city = request.getParameter("city");//城市
        String address = request.getParameter("member.address");
        String postCode = request.getParameter("member.postcode");//邮政编码
        String cardNo = request.getParameter("member.cardno");//证件号
        String cardType = request.getParameter("member.cardtype");//证件类型
        String tel = request.getParameter("member.tel");//电话号
        String email = request.getParameter("member.email");//邮箱
        //2.调用业务层做业务处理
        TbMember tbMember = new TbMember();
        tbMember.setId(userId);
        tbMember.setPassword(passWord);
        tbMember.setCity(city);
        tbMember.setAddress(address);
        tbMember.setPostcode(postCode);
        tbMember.setCardno(cardNo);
        tbMember.setCardtype(cardType);
        tbMember.setTel(tel);
        tbMember.setEmail(email);
        int i = service.updateMember(tbMember,"前台");
        if (i==0){
            String msg="<script>alert('修改失败')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/memberModify.jsp").forward(request,response);
        }else {
            String msg="<script>alert('修改成功')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }


    /**
     * 退出
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("member");
        response.sendRedirect("index.jsp");

    }


    /**
     *会员管理修改
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberName = request.getParameter("username");
        TbMember member = service.getMember(memberName);
        request.setAttribute("member",member);
        request.getRequestDispatcher("/toMemberManageModify.jsp").forward(request,response);
    }


    /**
     *会员信息详情
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    public void memberView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberName = request.getParameter("username");
        TbMember member = service.getMember(memberName);
        request.setAttribute("member",member);
        request.getRequestDispatcher("/memberManageView.jsp").forward(request,response);
    }


    /**
     * 更改冻结状态
     *
     * @param request 请求对象
     * @param response 响应对象
     */
    public void updateFreeze(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String freeze = request.getParameter("freeze");
        String username = request.getParameter("username");
        service.updateFreeze(freeze,username);
        request.getRequestDispatcher("memberManageList.jsp").forward(request,response);
    }


    /**
     * 会员管理一览
     *
     * @param request 请求对象
     * @param response 响应对象
     */
    public void memberList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageCount=3;
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int memberCount = service.getMemberCount();
        int totalPage=memberCount%pageCount==0?memberCount/pageCount:memberCount/pageCount+1;
        int start=(currentPage-1)*pageCount+1;
        int end=start+pageCount-1;
        List<TbMember> memberList=service.getAllMember(start,end);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("memberList",memberList);
        jsonObject.put("totalPage",totalPage);
        response.getWriter().println(jsonObject);
        response.getWriter().close();
    }


    /**
     * 重置密码
     *
     * @param request 请求对象
     * @param response 响应对象
     */
    public void reSetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String realName = request.getParameter("realName");
        String tel = request.getParameter("tel");
        int i=service.getMemberCount(userName,realName,tel);
        if (i==0){
            String msg="<script>alert('信息填写有误，重置失败')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/resetPassword.jsp").forward(request,response);
        }else {
            service.resetPassWord(userName);
            String msg="<script>alert('重置成功，初始密码为123')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }


    /**
     * 判断用户名是否存在
     *
     * @param request 请求对象
     * @param response 响应对象
     */
    public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        int userCount = service.getMemberCount(userName);
        response.getWriter().println(userCount);
        response.getWriter().close();

    }


    /**
     * 获取城市表的所有城市
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    public void getCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List cityList=service.getCity();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cityList",cityList);
        response.getWriter().println(jsonObject);
        response.getWriter().close();
    }


    /**
     * 注册业务
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取前台值
        String userName = request.getParameter("member.username");//用户名
        String realName = request.getParameter("member.realname");//真实姓名
        String passWord = request.getParameter("password");//密码
        String city = request.getParameter("city");//城市
        String address = request.getParameter("member.address");//地址
        String postCode = request.getParameter("member.postcode");//邮政编码
        String cardNo = request.getParameter("member.cardno");//证件号
        String cardType = request.getParameter("member.cardtype");//证件类型
        String tel = request.getParameter("member.tel");//电话号
        String email = request.getParameter("member.email");//邮箱
        //2.调用业务层做业务处理
        TbMember tbMember = new TbMember();
        tbMember.setUsername(userName);
        tbMember.setTruename(realName);
        tbMember.setPassword(passWord);
        tbMember.setCity(city);
        tbMember.setAddress(address);
        tbMember.setPostcode(postCode);
        tbMember.setCardno(cardNo);
        tbMember.setCardtype(cardType);
        tbMember.setTel(tel);
        tbMember.setEmail(email);
        int i = service.insertMember(tbMember);
        //3.跳转
        if (i==0){
            String msg="<script>alert('注册失败')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }else {
            String msg="<script>alert('注册成功')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }




    /**
     * 登录业务
     *
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取前台值
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        //2.调用业务层做业务处理
        int loginFlg = service.getMemberCount(userName, passWord);
        //3.跳转
        if (loginFlg>0){
            TbMember member=service.getMember(userName);
            try {
                if ("0".equals(member.getFreeze())){
                    String msg="<script>alert('您的账号有异常行为，已被冻结！')</script>";
                    request.setAttribute("msg",msg);
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }else {
                    String msg="<script>alert('欢迎登陆！')</script>";
                    request.setAttribute("msg",msg);
                    request.getSession().setAttribute("member",member);
                    request.getRequestDispatcher("/index.jsp").forward(request,response);
                }
            }catch (NullPointerException e){
                String msg="<script>alert('账号异常！')</script>";
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        }else {
            String msg="<script>alert('账号或密码错误，请重新输入！')</script>";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }


}
