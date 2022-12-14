package demo.dd.learningofes.util;

import demo.dd.learningofes.constant.Constant;
import demo.dd.learningofes.model.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DiDi
 * @description 网页解析工具类
 * @date 2022/12/14 13:59
 */
public class HtmlUtil {

    /**
     * 输入关键字调用url进行接口查询
     * 解析查询结果
     * @param keyWord
     */
    public static List<Goods> parseHTML(String keyWord) {
        List<Goods> goods = new ArrayList<>();
        String searchURL = Constant.JD_SEARCH_URL + keyWord;
        try {
            Document document = Jsoup.parse(new URL(searchURL), 3000);
            Element element = document.getElementById("J_goodsList");
            Elements elements = element.getElementsByTag("li");
            for (Element el : elements) {
                String pic =  el.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String name = el.getElementsByClass("p-name").eq(0).text();

                Goods good = new Goods();
                good.setPicURL(pic);
                good.setPrice(price);
                good.setName(name);
                goods.add(good);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return goods;
    }

    public static void main(String[] args) {
        List<Goods> goods = parseHTML("java");
    }
}
