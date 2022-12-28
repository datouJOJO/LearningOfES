package demo.dd.learningofes.config;

import demo.dd.learningofes.constant.Constant;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DiDi
 * @description es客户端初始化
 * @date 2022/12/14 13:35
 */

@Configuration
public class ElasticSearchClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(Constant.USER_NAME, Constant.PASSWORD));  //es账号密码
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Constant.ELASTIC_SEARCH_SERVER, Constant.ELASTIC_SEARCH_PORT, "http")
                ).setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.disableAuthCaching();
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                })
        );
    }
}
