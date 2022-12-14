package demo.dd.learningofes.config;

import demo.dd.learningofes.constant.Constant;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DiDi
 * @description
 * @date 2022/12/14 13:35
 */

@Configuration
public class ElasticSearchClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Constant.ELASTIC_SEARCH_SERVER,
                                Constant.ELASTIC_SEARCH_PORT,
                                "http")));
    }
}
