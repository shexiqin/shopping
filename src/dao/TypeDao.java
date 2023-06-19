package dao;

import bean.TbType;
import util.DbUtil;

import java.util.List;

public class TypeDao {
    DbUtil dbUtil = DbUtil.getInstance();


    /**
     * 查询支付方式
     *
     * @return  List<TbType>类别列表
     */
    public List<TbType> selectPayBy() {
        String sql = "select ID, NAME from TB_TYPE where FLAG = 'B'";
        return dbUtil.queryListObject(sql,null,TbType.class);
    }


    /**
     * 查询运送方式
     *
     * @return  List<TbType>类别列表
     */
    public List<TbType> selectCarryBy() {
        String sql = "select ID, NAME from TB_TYPE where FLAG = 'C'";
        return dbUtil.queryListObject(sql,null,TbType.class);
    }


    /**
     * 查询证件类型
     *
     * @return  List<TbType>类别列表
     */
    public List<TbType> selectCertificateBy() {
        String sql = "select ID, NAME from TB_TYPE where FLAG = 'A'";
        return dbUtil.queryListObject(sql,null,TbType.class);
    }

}
