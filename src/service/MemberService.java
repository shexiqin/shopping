package service;

import bean.TbMember;
import dao.MemberDao;

import java.util.List;

public class MemberService {
    MemberDao dao =new MemberDao();

    /**
     * 注册业务同名用户校验
     *
     * @param userName 会员名
     * @return 找到用户与否   0、未找到该用户 1、找到该用户
     */
    public int getMemberCount(String userName) {
        return dao.selMemberCount(userName);
    }


    /**
     *登录业务用户名密码校验
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功与否（>0:成功   =0:失败）
     */
    public int getMemberCount(String userName,String password){
        return dao.selMemberCount(userName,password);
    }


    /**
     *密码重置业务校验
     *
     * @param userName 用户名
     * @param realName 真实姓名
     * @param tel 手机号
     * @return 找到与否  >0: 找到  =0:未找到
     */
    public int getMemberCount(String userName,String realName,String tel){
        return dao.selMemberCount(userName,realName,tel);
    }


    /**
     *获取所有会员条数
     *
     * @return 找到用户与否  0、未找到该用户 1、找到该用户
     */
    public int getMemberCount(){
        return dao.selMemberCount();
    }


    /**
     * 根据会员名获取Member对象
     *
     * @param userName 会员名
     * @return Member实体对象
     */
    public TbMember getMember(String userName) {
        return dao.selMember(userName);
    }


    /**
     * 获取所有城市
     *
     * @return 返回城市集合
     */
    public List getCity() {
        return dao.selCity();
    }


    /**
     * 插入会员
     *
     * @param tbMember 会员实体
     * @return 插入成功与否  0、插入失败 1、插入成功
     */
    public int insertMember(TbMember tbMember) {
        return dao.insertMember(tbMember);
    }


    /**
     * 重置密码为123
     *
     * @param userName 用户名
     */
    public void resetPassWord(String userName) {
        dao.updatePassword(userName);
    }


    /**
     * 获取所有的会员数据
     *
     * @return 返回会员实体集合
     */
    public List<TbMember> getAllMember(int start,int end) {
        return dao.selAllMember(start,end);
    }


    /**
     * 改变冻结状态
     *
     * @param freeze 冻结
     * @param username 用户名
     */
    public void updateFreeze(String freeze,String username) {
        dao.updateFreeze(freeze,username);
    }


    /**
     * 会员信息修改
     *
     * @param tbMember 会员
     * @return 修改成功与否  >0修改成功，=0修改失败
     */
    public int updateMember(TbMember tbMember,String flg) {
        return  dao.updateMember(tbMember,flg);
    }

}



