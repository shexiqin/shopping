package dao;

import util.DbUtil;

import java.util.ArrayList;

public class AdminDao {
    DbUtil dbUtil = DbUtil.getInstance();


    /**
     * 查询管理员数量
     *
     * @param manager  名
     * @param password  密码
     * @return 查询成功与否   1 查询成功，   0  查询失败
     */
    public int selectAdminCount(String manager, String password) {
        String sql="select count(1) from tb_manager where manager=? and pwd=?";
        ArrayList<Object> params=new ArrayList<>();
        params.add(manager);
        params.add(password);
        return dbUtil.count(sql,params);
    }
}
