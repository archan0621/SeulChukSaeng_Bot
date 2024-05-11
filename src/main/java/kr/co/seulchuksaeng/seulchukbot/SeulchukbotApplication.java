package kr.co.seulchuksaeng.seulchukbot;

import kr.co.seulchuksaeng.seulchukbot.listener.SeulchukListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.security.auth.login.LoginException;

@SpringBootApplication
public class SeulchukbotApplication {

	public static void main(String[] args) throws LoginException {
		ApplicationContext context = SpringApplication.run(SeulchukbotApplication.class, args);
		String token = context.getEnvironment().getProperty("discord.token");
		SeulchukListener listener = context.getBean(SeulchukListener.class);
		JDABuilder.createDefault(token)
				.setActivity(Activity.customStatus("민혜랑 현피 뜨는 중"))
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.addEventListeners(listener)
				.build();
	}

}