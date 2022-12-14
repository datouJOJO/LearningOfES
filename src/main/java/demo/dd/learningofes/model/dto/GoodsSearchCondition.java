package demo.dd.learningofes.model.dto;

import lombok.Data;

/**
 * @author DiDi
 * @description 商品搜索条件
 * @date 2022/12/14 16:07
 */

@Data
public class GoodsSearchCondition {

    /**
     * 关键字
     */
    private String keyWord;

    /**
     * 分页起始偏移量
     */
    private Integer startIndex;

    /**
     * 分页大小
     */
    private Integer pageSize;
}
