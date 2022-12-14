package demo.dd.learningofes.service;

import demo.dd.learningofes.model.Goods;
import demo.dd.learningofes.model.dto.GoodsSearchCondition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author DiDi
 * @description 数据服务
 * @date 2022/12/14 14:40
 */
public interface DataService {

    /**
     * 爬取数据
     * @return  数据列表
     */
    List<Goods> spiderData(String keyWord) throws IOException;

    /**
     * 分页查询商品信息
     *
     * @param condition 搜索条件
     * @return 商品信息
     */
    List<Map<String, Object>> getDataByPage(GoodsSearchCondition condition) throws IOException;
}
