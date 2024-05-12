package kr.co.seulchuksaeng.seulchukbot.utils.impl;

import kr.co.seulchuksaeng.seulchukbot.utils.OtpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class OtpUtilImpl implements OtpUtil {


    @Value("${otp.key}")
    private String otpKey;

    @Override
    public String getOtp() {
        try {
            // 현재 시간을 "yyyy-MM-dd HH:mm:ss" 형식으로 가져옵니다.
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String data = currentTime + otpKey;  // 현재 시간과 비밀 키를 조합

            // SHA-256 해시 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());

            // 해시의 일부를 정수로 변환하고 1000000으로 나눈 나머지를 계산하여 6자리 OTP를 생성
            int otp = ((hash[0] & 0xff) << 24 | (hash[1] & 0xff) << 16 | (hash[2] & 0xff) << 8 | (hash[3] & 0xff)) % 1000000;

            // OTP가 6자리가 되도록 보장
            otp = Math.abs(otp);
            if (otp < 100000) {
                otp += 100000;  // 6자리 미만인 경우, 최소 100000을 더함
            }

            // 출력
            return String.valueOf(otp);
        } catch (NoSuchAlgorithmException e) {
           return "otp 생성중 에러 발생";
        }
    }

    @Override
    public String getCurrentTime() {
        return LocalDateTime.now().toString();
    }

}
