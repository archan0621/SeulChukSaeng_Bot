package kr.co.seulchuksaeng.seulchukbot.command;

import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class MessageParser {

    public static final String DEFAULT_MESSAGE = "알 수 없는 명령어입니다\n명령어 목록은 !명령어 를 입력해주세요!";

    public boolean checkValidCommand (MessageReceivedEvent event, String[] message) {
        return message[0].equalsIgnoreCase("!");
    }

    public String responseCommand(MessageReceivedEvent event, String[] message) {

        try {
            switch (message[1].toLowerCase()) {

                case "otp" -> {

                }

                default -> {
                    return DEFAULT_MESSAGE;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return DEFAULT_MESSAGE;
        }

        return DEFAULT_MESSAGE;
    }
}
