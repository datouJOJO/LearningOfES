package demo.dd.learningofes.model;

import lombok.Data;

/**
 * @author DiDi
 * @description 商品对象
 * @date 2022/12/14 14:02
 */

@Data
public class Goods {

    /**
     * 商品图片地址
     */
    public String picURL;

    /**
     * 商品名称
     */
    public String name;

    /**
     * 商品价格
     */
    public String price;
}
