package me.dong.slacknotifysample.notify.slack;


import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ethan.kim on 2018. 10. 7..
 */
@ConfigurationProperties(prefix = "slack")
@Getter
@Setter
public class SlackProperties {

    private String webHookUrlTest;
}
