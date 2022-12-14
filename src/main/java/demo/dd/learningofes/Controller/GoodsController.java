package demo.dd.learningofes.Controller;

import demo.dd.learningofes.model.Goods;
import demo.dd.learningofes.model.Reply;
import demo.dd.learningofes.model.dto.GoodsSearchCondition;
import demo.dd.learningofes.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author DiDi
 * @description
 * @date 2022/12/14 14:52
 */

@RestController
public class GoodsController {

    @Autowired
    private DataService dataService;

    @GetMapping("/spiderData/{keyWord}")
    public Reply spiderData(@PathVariable("keyWord") String keyWord) {
        try {
            List<Goods> goodsList = dataService.spiderData(keyWord);
            return Reply.success(goodsList);
        } catch (IOException e) {
            e.printStackTrace();
            return Reply.fail(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Reply.fail();
        }
    }

    @PostMapping("/getGoods")
    public Reply getDataByPage(@RequestBody GoodsSearchCondition condition) {
        try {
            List<Map<String, Object>> goods = dataService.getDataByPage(condition);
            return Reply.success(goods);
        } catch (Exception e) {
            e.printStackTrace();
            return Reply.fail();
        }
    }

}
