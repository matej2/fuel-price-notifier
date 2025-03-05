package com.project.fuel_price_notifier.service.client;

import com.project.fuel_price_notifier.model.SmsPayload;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class TwillioClient {
    private static final String SENDER_PHONE_NUM = System.getenv("TWILIO_SENDER_PHONE_NUM");
    private static final String RECEIVER_PHONE_NUM = System.getenv("TWILIO_RECEIVER_PHONE_NUM");

    private static final String TWILIO_ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String TWILIO_AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public TwillioClient() {
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
    }

    public void sendMessage(SmsPayload body) {
        Message
                .creator(
                        new PhoneNumber(RECEIVER_PHONE_NUM),
                        new PhoneNumber(SENDER_PHONE_NUM),
                        body.toString()
                )
                .create();
    }
}
