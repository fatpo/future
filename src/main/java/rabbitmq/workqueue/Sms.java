package rabbitmq.workqueue;

import lombok.Data;

@Data
public class Sms {
    private String name;
    private String phone;
    private String content;

    public Sms(String name, String phone, String content) {
        this.name = name;
        this.phone = phone;
        this.content = content;
    }
}
