package me.dong.slacknotifysample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ethan.kim on 2018. 10. 7..
 */
@Configuration
public class HttpClientConfiguration {

    private static final int EXTERNAL_TIMEOUT_IN_MILLISECONDS = 30_000;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(EXTERNAL_TIMEOUT_IN_MILLISECONDS);
        httpRequestFactory.setReadTimeout(EXTERNAL_TIMEOUT_IN_MILLISECONDS);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(httpRequestFactory);
        return restTemplate;
    }
}
