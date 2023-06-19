package service;

import bean.TbType;
import dao.TypeDao;

import java.util.List;

public class TypeService {

    TypeDao typeDao = new TypeDao();


    /**
     * 查询支付方式
     *
     * @return  List<TbType>类别列表
     */
    public List<TbType> getPayBy() {
        return typeDao.selectPayBy();
    }


    /**
     * 查询运送方式
     *
     * @return  List<TbType>类别列表
     */
    public List<TbType> getCarryBy() {
        return typeDao.selectCarryBy();
    }
}
