package service;

import bean.TbClasses;
import bean.TbGoods;
import dao.GoodsDao;

import java.util.List;

public class GoodsService {
    GoodsDao goodsDao = new GoodsDao();

    /**
     * 分页查询商品
     *
     * @param start  起始位置
     * @param end  结束位置
     * @return  List<TbGoods>商品列表
     */
    public List<TbGoods> getGoodsListBySplit(int start, int end) {
        return  goodsDao.queryGoodsListBySplit(start,end);
    }


    /**
     * 查询商品总数
     *
     * @return 数量
     */
    public int getGoodsCount() {
        return goodsDao.selectTotalCount();
    }


    /**
     * 查询所有分类
     *
     * @return  List<TbClasses>类别列表
     */
    public List<TbClasses> getGoodsClass() {
        return goodsDao.queryGoodsClass();
    }


    /**
     * 通过类别名找类别ID
     *
     * @param options 类别名
     * @return ID
     */
    public String getClassID(String options) {
        return goodsDao.queryGoodsClass(options);
    }


    /**
     * 添加商品
     *
     * @param tbGoods  tbGoods对象
     * @return  int 插入成功与否 1 插入成功    0 插入失败
     */
    public int addGoods(TbGoods tbGoods) {
        return goodsDao.insertGoods(tbGoods);
    }


    /**
     * 得到商品
     *
     * @param goodsName 商品名
     * @return 商品
     */
    public TbGoods getGoods(String goodsName) {
        return goodsDao.getGoods(goodsName);
    }


    /**
     * 修改商品
     *
     * @param tbGoods 商品
     * @return 修改成功与否  >0修改成功， =0修改失败
     */
    public int modifyGoods(TbGoods tbGoods) {
        return goodsDao.updateGoods(tbGoods);
    }


    /**
     * 删除商品
     *
     * @param goodsName 商品名
     */
    public void delGoods(String goodsName) {
        goodsDao.deleteGoods(goodsName);
    }


    /**
     * 查询商品详细信息
     *
     * @param goodsId  商品ID
     * @return  TbGoods对象
     */
    public TbGoods getGoodsDetail(int goodsId) {
        return goodsDao.selectGoodsDetail(goodsId);
    }


    /**
     * 通过类别名得到所有的商品
     *
     * @param className 类别名
     * @return 商品实体对象
     */
    public List<TbGoods> getGoodsByClass(String className) {
        return goodsDao.selGoodsByClass(className);
    }


    /**
     * 通过id获得商品
     *
     * @param goodId 商品id
     * @return 商品
     */
    public TbGoods getGoodsById(String goodId) {
        return goodsDao.selGoodsById(goodId);
    }

    /**
     * 获取所有商品
     *
     * @return 商品列表
     */
    public List getAllGoods() {
        return goodsDao.selAllGoods();
    }


    /**
     * 根据条件查询个数
     *
     * @param name 名字
     * @param lowPrice 价格底线
     * @param upPrice 价格上限
     * @return 个数
     */
    public int getGoodsCountBy(String name, String lowPrice, String upPrice) {
        return goodsDao.selectGoodCountBy(name,lowPrice,upPrice);
    }


    /**
     * 条件查询
     *
     * @param start 起始
     * @param end 结束
     * @param name 商品名
     * @param lowPrice 底价
     * @param upPrice 顶价
     * @param sortBy 排序
     * @return  List<TbGoods>商品列表
     */
    public List<TbGoods> getGoodsListBy(int start, int end, String name, String lowPrice, String upPrice, String sortBy) {
        return goodsDao.selectGoodsBy(start,end,name,lowPrice,upPrice,sortBy);
    }
}
