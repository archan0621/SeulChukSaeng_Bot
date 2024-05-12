package kr.co.seulchuksaeng.seulchukbot.utils;

import org.springframework.stereotype.Component;

@Component
public class HelpUtil {

    public String showCommandList() {
        StringBuilder sb = new StringBuilder();
        sb.append("다음 명령어들을 사용하실 수 있습니다").append("\n");
        sb.append("! otp - 부원 인증용 otp 값을 출력합니다").append("\n");
        sb.append("! time - 현재 시간을 출력합니다").append("\n");
        return sb.toString();
    }

}
