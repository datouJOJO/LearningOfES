package demo.dd.learningofes.service.impl;

import com.alibaba.fastjson.JSON;
import demo.dd.learningofes.model.Goods;
import demo.dd.learningofes.model.dto.GoodsSearchCondition;
import demo.dd.learningofes.service.DataService;
import demo.dd.learningofes.util.HtmlUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author DiDi
 * @description 数据服务实现类
 * @date 2022/12/14 14:41
 */

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public List<Goods> spiderData(String keyWord) throws IOException {
        List<Goods> goods = HtmlUtil.parseHTML(keyWord);
        // 批量插入
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (int i = 0; i < goods.size(); i++) {
            bulkRequest.add(new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(goods.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return goods;
    }

    @Override
    public List<Map<String, Object>> getDataByPage(GoodsSearchCondition condition) throws IOException {
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.from(condition.getStartIndex());
        sourceBuilder.size(condition.getPageSize());

        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", condition.getKeyWord());
        sourceBuilder.query(queryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> goods = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            goods.add(hit.getSourceAsMap());
        }
        return goods;
    }

    public static void main(String[] args) throws IOException {
        DataServiceImpl dataService = new DataServiceImpl();
        // dataService.spiderData("java");
        GoodsSearchCondition searchCondition = new GoodsSearchCondition();
        searchCondition.setKeyWord("java");
        searchCondition.setStartIndex(0);
        searchCondition.setPageSize(10);
        dataService.getDataByPage(searchCondition);
    }
}
