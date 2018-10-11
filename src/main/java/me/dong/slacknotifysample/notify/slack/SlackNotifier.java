package me.dong.slacknotifysample.notify.slack;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by ethan.kim on 2018. 10. 4..
 */
@Component
@EnableConfigurationProperties(SlackProperties.class)
@Slf4j
public class SlackNotifier {

    // 1개의 알림자는 1개의 체널에만 알림을 주는게 맞지 않을까?? 너무 많은 체널을 알고 있으면 점점 더 복잡해지기 때문에....

    private final RestTemplate restTemplate;

    private final SlackProperties slackProperties;

    public SlackNotifier(RestTemplate restTemplate, SlackProperties slackProperties) {
        this.restTemplate = restTemplate;
        this.slackProperties = slackProperties;
    }

    /*
    plain text - {"text": "This is a line of text in a channel.\nAnd this is another line of text."}
    add links - {"text": "A very important thing has occurred! <https://alert-system.com/alerts/1234|Click here> for details!"}

    curl example
    curl -X POST -H "Content-Type: application/json" -d "{\"username\": \"webhookbot\", \"text\": \"This is posted to #notice and comes from a bot named webhookbot.\"}" https://hooks.slack.com/services/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

    라이브러리로 제공한다고 했을 때
    사용자가 원하는 http client를 사용하도록 만든다
    비동기, 동기 각각 사용할 http client를 전달하도록?
    */

    private void doNotify(String url, SlackMessage slackMessage) {
        restTemplate.postForObject(url, slackMessage, String.class);
    }

    public void notifyTest(SlackMessage message) {
        doNotify(slackProperties.getWebHookUrlTest(), message);
    }
}
