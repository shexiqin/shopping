package service;

import bean.TbNotice;
import dao.NoticeDao;

import java.util.List;

public class NoticeService {
    NoticeDao noticeDao = new NoticeDao();


    /**
     * 插入公告
     *
     * @param tbNotice  tbNotice对象
     * @return   1 成功    0  失败
     */
    public int addNotice(TbNotice tbNotice) {
        return noticeDao.insertNotice(tbNotice);
    }


    /**
     * 查询公告总数
     *
     * @return  数量
     */
    public int getNoticeCount() {
        return noticeDao.selectNoticeCount();
    }


    /**
     * 分页查询公告列表
     *
     * @param start  起始位置
     * @param end  结束位置
     * @return    List<TbNotice>公告列表
     */
    public List<TbNotice> getNoticeList(int start, int end) {
        return  noticeDao.selectNoticeList(start,end);
    }


    /**
     * 查询公告明细
     *
     * @param id  ID
     * @return  TbNotice公告
     */
    public TbNotice getNoticeById(String id) {
        return noticeDao.selectNoticeById(id);
    }


    /**
     * 更新公告
     *
     * @param tbNotice   tbNotice对象
     * @return 修改成功与否  1 修改成功  0 修改失败
     */
    public int modifyNotice(TbNotice tbNotice) {
        return noticeDao.updateNoticeById(tbNotice);
    }


    /**
     * 删除公告
     *
     * @param id  ID
     */
    public void delNoticeById(String id) {
        noticeDao.deleteNoticeById(id);
    }


    /**
     * 前台轮播列表查询
     *
     * @return  List<TbNotice>公告列表
     */
    public List<TbNotice> getNoticeListLimit() {
        return noticeDao.selectNoticeLimit();
    }

}
