package org.daypilot.demo.html5eventcalendarspring.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsUtility {
    private static final String ACCOUNT_SID = "AC899fa2ea88ed71b93e716ffb0135a969";
    private static final String AUTH_TOKEN = "2902d9435fe906fe21f3624e4f845572";
    private static final String FROM_NUMBER = "+17242515324";


    public void Notification(String inComingEventsList) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+923125153352"),
                new PhoneNumber(FROM_NUMBER),
                "Your Today's events-")
                .create();
        System.out.println("here is my id:" + message.getSid());
    }
}
