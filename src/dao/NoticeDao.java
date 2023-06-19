package dao;

import bean.TbNotice;
import util.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class NoticeDao {
    DbUtil dbUtil = DbUtil.getInstance();


    /**
     * 插入公告
     *
     * @param tbNotice  tbNotice对象
     * @return 插入成功与否   1 成功    0  失败
     */
    public int insertNotice(TbNotice tbNotice) {
        String sql = "insert into TB_NOTICE (ID, TITLE, CONTENT, CREATDATE, ENDDATE, ADDFILE) values (SEQ_NOTICE.nextval,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
        ArrayList<Object> params = new ArrayList<>();
        params.add(tbNotice.getTitle());
        params.add(tbNotice.getContent());
        params.add(tbNotice.getCreatdate());
        params.add(tbNotice.getEnddate());
        params.add(tbNotice.getAddfile());
        return dbUtil.execute(sql,params);
    }


    /**
     * 查询公告总数
     *
     * @return  数量
     */
    public int selectNoticeCount() {
        String sql = "SELECT count(1) FROM TB_NOTICE";
        return dbUtil.count(sql,null);
    }


    /**
     * 分页查询公告列表
     *
     * @param start 起始位置
     * @param end 结束位置
     * @return List<TbNotice>公告列表
     */
    public List<TbNotice> selectNoticeList(int start, int end) {
        // 获取所有公告  如果只获取未过期公告 添加条件 ENDDATE>sysdate and
        String sql = "select ID, TITLE, CONTENT, CREATDATE, ENDDATE, ADDFILE from(select t.*,ROWNUM r from TB_NOTICE t where ROWNUM<=?) where r>=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(end);
        params.add(start);
        return dbUtil.queryListObject(sql,params,TbNotice.class);
    }


    /**
     * 查询公告明细
     *
     * @param id  ID
     * @return  TbNotice公告
     */
    public TbNotice selectNoticeById(String id) {
        String sql = "select ID, TITLE, CONTENT, CREATDATE, ENDDATE, ADDFILE from TB_NOTICE where ID = ?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        return dbUtil.queryObject(sql,params,TbNotice.class);
    }


    /**
     * 更新公告
     *
     * @param tbNotice   tbNotice对象
     * @return 修改成功与否   1 修改成功  0 修改失败
     */
    public int updateNoticeById(TbNotice tbNotice) {
        String sql = "update TB_NOTICE set TITLE=?,CONTENT=?,CREATDATE=to_date(?,'yyyy-mm-dd hh24:mi:ss'),ENDDATE=to_date(?,'yyyy-mm-dd hh24:mi:ss'),ADDFILE=? where ID=?";
        String sql1 = "update TB_NOTICE set TITLE=?,CONTENT=?,CREATDATE=to_date(?,'yyyy-mm-dd hh24:mi:ss'),ENDDATE=to_date(?,'yyyy-mm-dd hh24:mi:ss') where ID=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(tbNotice.getTitle());
        params.add(tbNotice.getContent());
        params.add(tbNotice.getCreatdate());
        params.add(tbNotice.getEnddate());
        if (tbNotice.getAddfile() == null){
            params.add(tbNotice.getId());
            return dbUtil.execute(sql1,params);
        }else {
            params.add(tbNotice.getAddfile());
            params.add(tbNotice.getId());
            return dbUtil.execute(sql,params);
        }
    }


    /**
     * 删除公告
     *
     * @param id  ID
     */
    public void deleteNoticeById(String id) {
        String sql = "delete from TB_NOTICE where ID=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        dbUtil.execute(sql,params);
    }


    /**
     * 前台轮播列表查询
     *
     * @return  List<TbNotice>公告列表
     */
    public List<TbNotice> selectNoticeLimit() {
        String sql = "select t.*,ROWNUM r from TB_NOTICE t where t.ENDDATE>sysdate and ROWNUM<=5";
        return dbUtil.queryListObject(sql,null,TbNotice.class);
    }

}
