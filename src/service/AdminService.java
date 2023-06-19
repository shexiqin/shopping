package service;

import dao.AdminDao;

public class AdminService {
    AdminDao adminDao = new AdminDao();


    /**
     * 查询管理员数量
     *
     * @param manager 管理员名
     * @param password 密码
     * @return 查询成功与否  1 查询成功， 0  查询失败
     */
    public int login(String manager, String password) {
        return adminDao.selectAdminCount(manager,password);
    }
}
