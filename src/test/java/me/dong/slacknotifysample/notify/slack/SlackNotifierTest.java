package me.dong.slacknotifysample.notify.slack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Created by ethan.kim on 2018. 10. 7..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SlackNotifierTest {

    @Autowired
    private SlackNotifier slackNotifier;

    @Test
    public void doNotify() {
        List<SlackMessage.Attachment> attachments = IntStream.range(0, 2)
                .mapToObj(n -> {
                    List<SlackMessage.Attachment.Field> fields = new ArrayList<>();
                    SlackMessage.Attachment.Field field;
                    for (int i = 0; i < 3; i++) {
                        field = new SlackMessage.Attachment.Field();
                        field.setTitle("field title " + i);
                        field.setContent("field content " + i);
                        fields.add(field);
                    }

                    SlackMessage.Attachment attachment = new SlackMessage.Attachment();
                    attachment.setColor(SlackMessage.Attachment.COLOR_GOOD);
                    attachment.setFallback("New open task");
                    attachment.setPretext("pre text");
                    attachment.setText("text");
                    attachment.setFields(fields);

                    return attachment;
                }).collect(Collectors.toList());

        SlackMessage message = new SlackMessage();
        message.setText("This is test hook");
        message.setAttachments(attachments);

        slackNotifier.doNotify(message);
    }
}