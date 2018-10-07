package me.dong.slacknotifysample.notify.slack;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by ethan.kim on 2018. 10. 4..
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class SlackMessage {

    // optional. channel name, @opklnm102(DM)
    // 특정 user 에게 개인적으로 보낼꺼 아니면... 굳이 필요없을듯?? 기본셋팅 체널로 전송될텐데
    private String channel;

    // optional. webhookbot
    private String username;  // hook name

    // message
    private String text;

    private List<Attachment> attachments;

    /**
     * 첨부 메시지
     */
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Attachment {

        // Required text summary of the attachment that is shown by clients that understand attachments but choose not to show them
        private String fallback;  // pretext와 같으면 좋다

        // Optional text that should appear above the formatted data
        private String pretext;  // attachment 위에 표시

        // Optional text that should appear within the attachment
        private String text;  // attachment와 함께 표시

        // Can either be one of 'good', 'warning', 'danger', or any hex color code
        private String color;

        // Fields are displayed in a table on the message
        private List<Field> fields;

        public static final String COLOR_GOOD = "good";
        public static final String COLOR_WARNING = "warning";
        public static final String COLOR_DANGER = "danger";

        @NoArgsConstructor
        @Setter
        @Getter
        public static class Field {

            // "Required Field Title", // The title may not contain markup and will be escaped for you
            private String title;

            // "value": "Text value of the field. May contain standard message markup and must be escaped as normal. May be multi-line.",
            @JsonProperty(value = "value")
            private String content;
        }
    }
}
/*
{
	"fallback": "Required text summary of the attachment that is shown by clients that understand attachments but choose not to show them.",
	"text": "Optional text that should appear within the attachment",
	"pretext": "Optional text that should appear above the formatted data",
	"color": "#36a64f", // Can either be one of 'good', 'warning', 'danger', or any hex color code

	// Fields are displayed in a table on the message
	"fields": [
		{
			"title": "Required Field Title", // The title may not contain markup and will be escaped for you
			"value": "Text value of the field. May contain standard message markup and must be escaped as normal. May be multi-line.",
			"short": false // Optional flag indicating whether the `value` is short enough to be displayed side-by-side with other values
		}
	]
}
example
{
   "attachments":[
      {
         "fallback":"New open task [Urgent]: <http://url_to_task|Test out Slack message attachments>",
         "pretext":"New open task [Urgent]: <http://url_to_task|Test out Slack message attachments>",
         "color":"#D00000",
         "fields":[
            {
               "title":"Notes",
               "value":"This is much easier than I thought it would be.",
               "short":false
            }
         ]
      }
   ]
}
 */
