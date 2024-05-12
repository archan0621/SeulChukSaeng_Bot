package kr.co.seulchuksaeng.seulchukbot.command;

import kr.co.seulchuksaeng.seulchukbot.utils.HelpUtil;
import kr.co.seulchuksaeng.seulchukbot.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RequiredArgsConstructor
@Component
@Slf4j
public class MessageParser {

    private final OtpUtil otpUtil;
    private final HelpUtil helpUtil;

    public static final String DEFAULT_MESSAGE = "알 수 없는 명령어입니다\n명령어 목록은 ! help 를 입력해주세요!";

    public boolean checkValidCommand (MessageReceivedEvent event, String[] message) {
        return message[0].equalsIgnoreCase("!");
    }

    public String responseCommand(MessageReceivedEvent event, String[] message) {

        try {
            switch (message[1].toLowerCase()) {
                case "otp" -> {
                    log.info("otp 번호 호출 명령어가 실행되었습니다!");
                    StringBuilder sb = new StringBuilder();
                    String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss"));
                    int leftTime = 60 - Integer.parseInt(currentTime);
                    sb.append("인증번호 : ").append(otpUtil.getOtp()).append("\n");
                    sb.append(leftTime).append(" 초 남았습니다!");
                    return sb.toString();
                }
                case "time" -> {
                    log.info("현재 시간 조회 명령어가 실행되었습니다!");
                    return otpUtil.getCurrentTime();
                }
                case "help" -> {
                    log.info("도움 명령어가 호출되었습니다!");
                    return helpUtil.showCommandList();
                }
                default -> {
                    return DEFAULT_MESSAGE;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return DEFAULT_MESSAGE;
        }
    }
}
