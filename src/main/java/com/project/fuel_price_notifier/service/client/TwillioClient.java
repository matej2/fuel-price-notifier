package com.project.fuel_price_notifier.service.client;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class TwillioClient {
    private static final String RECEIVER_PHONE_NUM = System.getenv("RECEIVER_PHONE_NUM");

    private static final String TWILIO_ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String TWILIO_AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public TwillioClient() {
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
    }

    public void sendMessage(String body) {
        Message message = Message
                .creator(
                        new PhoneNumber("+18777804236"),
                        new PhoneNumber("+19123616064"),
                        "This is the ship that made the Kessel Run in fourteen parsecs?"
                )
                .create();
    }
}
