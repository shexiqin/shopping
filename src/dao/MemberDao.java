package dao;

import bean.TbArealink;
import bean.TbMember;
import util.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    DbUtil dbUtil = DbUtil.getInstance();


    /**
     * 插入会员
     *
     * @param tbMember 会员实体
     * @return 插入成功与否  0、插入失败 1、插入成功
     */
    public int insertMember(TbMember tbMember){
        String sql = "insert into TB_MEMBER (id, username, truename, password, city, address, postcode, cardno, cardtype, tel, email) values (SEQ_MANAGER.nextval,?,?,?,?,?,?,?,?,?,?)";
        ArrayList<Object> params = new ArrayList<>();
        params.add(tbMember.getUsername());
        params.add(tbMember.getTruename());
        params.add(tbMember.getPassword());
        params.add(tbMember.getCity());
        params.add(tbMember.getAddress());
        params.add(tbMember.getPostcode());
        params.add(tbMember.getCardno());
        params.add(tbMember.getCardtype());
        params.add(tbMember.getTel());
        params.add(tbMember.getEmail());
        return dbUtil.execute(sql,params);
    }


    /**
     * 得到符合条件的会员条数
     *
     * @param userName 用户名
     * @param password 密码
     * @return  0、未找到该用户 1、找到用户
     */
    public int selMemberCount(String userName,String password){
        String sql = "select count(1) from TB_MEMBER where USERNAME=? and PASSWORD=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(userName);
        params.add(password);
        return dbUtil.count(sql,params);
    }


    /**
     * 得到符合条件的会员条数
     *
     * @param userName 用户名
     * @param realName 真实姓名
     * @param tel 手机号
     * @return  0、未找到该用户 1、找到用户
     */
    public int selMemberCount(String userName,String realName,String tel){
        String sql = "select count(1) from TB_MEMBER where USERNAME=? and TRUENAME=? and TEL=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(userName);
        params.add(realName);
        params.add(tel);
        return dbUtil.count(sql,params);
    }


    /**
     * 得到符合条件的会员条数
     *
     * @param userName 用户名
     * @return  0、未找到该用户 1、找到高用户
     */
    public int selMemberCount(String userName) {
        String sql="select count(1) from tb_member where username=?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(userName);
        return dbUtil.count(sql,list);
    }


    /**
     * 得到所有的的会员条数
     *
     * @return  找到用户与否 0、未找到该用户 1、找到该用户
     */
    public int selMemberCount() {
        String sql="select count(1) from tb_member";
        return dbUtil.count(sql,null);
    }


    /**
     * 获取所有的城市
     *
     * @return 返回城市列表
     */
    public List selCity() {
        String sql = "select name from TB_AREALINK";
        return dbUtil.queryListObject(sql,null, TbArealink.class);
    }


    /**
     * 根据会员名获取Member对象
     *
     * @param userName 会员名
     * @return Member实体对象
     */
    public TbMember selMember(String userName) {
        String sql = "select * from TB_MEMBER WHERE USERNAME=?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(userName);
        return dbUtil.queryObject(sql,list,TbMember.class);
    }


    /**
     * 分页获取获取的会员数据
     *
     * @return 返回会员实体集合
     */
    public List<TbMember> selAllMember(int start,int end) {
        String sql = "select a1.* from (select a.*,ROWNUM r from(select * from TB_MEMBER)a where ROWNUM<= ?)a1 where a1.r>=?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(end);
        list.add(start);
        return dbUtil.queryListObject(sql,list, TbMember.class);
    }


    /**
     * 重置密码为123
     *
     * @param userName 用户名
     */
    public void updatePassword(String userName) {
        String sql = "update TB_MEMBER set PASSWORD =123 where USERNAME =?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(userName);
        dbUtil.execute(sql,list);
    }


    /**
     * 更改冻结状态
     *
     * @param freeze 冻结
     * @param username 用户名
     */
    public void updateFreeze(String freeze,String username) {
        if ("0".equals(freeze)){
            String sql = "update TB_MEMBER set freeze =1 where USERNAME =?";
            ArrayList<Object> list = new ArrayList<>();
            list.add(username);
            dbUtil.execute(sql,list);
        }else if("1".equals(freeze)){
            String sql = "update TB_MEMBER set freeze =0 where USERNAME =?";
            ArrayList<Object> list = new ArrayList<>();
            list.add(username);
            dbUtil.execute(sql,list);
        }
    }


    /**
     * 会员资料修改
     *
     * @param tbMember 会员
     * @param flg 标志
     * @return 修改成功与否  >0修改成功，=0修改失败
     */
    public int updateMember(TbMember tbMember, String flg) {
        if ("前台".equals(flg)){
            String sql = "update TB_MEMBER set password =?,city=?,address=?,postcode=?,cardno=?,cardtype=?,tel=?,email=? where id =?";
            ArrayList<Object> list = new ArrayList<>();
            list.add(tbMember.getPassword());
            list.add(tbMember.getCity());
            list.add(tbMember.getAddress());
            list.add(tbMember.getPostcode());
            list.add(tbMember.getCardno());
            list.add(tbMember.getCardtype());
            list.add(tbMember.getTel());
            list.add(tbMember.getEmail());
            list.add(tbMember.getId());
            return dbUtil.execute(sql,list);
        }else if ("后台".equals(flg)){
            String sql = "update TB_MEMBER set userName=?,truename=?,password =?,city=?,address=?,postcode=?,cardno=?,cardtype=?,tel=?,email=?,grade=? where id =?";
            ArrayList<Object> list = new ArrayList<>();
            list.add(tbMember.getUsername());
            list.add(tbMember.getTruename());
            list.add(tbMember.getPassword());
            list.add(tbMember.getCity());
            list.add(tbMember.getAddress());
            list.add(tbMember.getPostcode());
            list.add(tbMember.getCardno());
            list.add(tbMember.getCardtype());
            list.add(tbMember.getTel());
            list.add(tbMember.getEmail());
            list.add(tbMember.getGrade());
            list.add(tbMember.getId());
            return dbUtil.execute(sql,list);
        }else {
            return 0;
        }
    }

}
