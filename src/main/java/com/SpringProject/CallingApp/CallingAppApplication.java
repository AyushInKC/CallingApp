package com.SpringProject.CallingApp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;

@SpringBootApplication
public class CallingAppApplication implements ApplicationRunner {

	@Value("${twilio.sid}")
	private String sidAccount;

	@Value("${twilio.auth-id}")
	private String authId;

	@Value("${twilio.to-number}")
	private String toNumber;

	@Value("${twilio.from-number}")
	private String fromNumber;

	public static void main(String[] args) {
		SpringApplication.run(CallingAppApplication.class, args);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {

		Twilio.init(sidAccount, authId);

		try {
			Call call = Call.creator(
					new PhoneNumber(toNumber),
					new PhoneNumber(fromNumber),
					new URI("https://demo.twilio.com/docs/voice.xml")
			).create();
			System.out.println("Call initiated successfully. Call SID: " + call.getSid());
		} catch (Exception e) {

			System.err.println("Error occurred while making the call: " + e.getMessage());
		}
	}
}
