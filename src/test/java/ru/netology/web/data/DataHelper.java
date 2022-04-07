package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String summa;
        private String number;
    }

    public static CardInfo getInfoForTransfer(AuthInfo authInfo) {
        return new CardInfo("500", "5559 0000 0000 0001");
    }

//    public static CardInfo getOtherInfoForTransfer(AuthInfo authInfo) {
//        return new CardInfo("10500", "5559 0000 0000 0001");
//    }
}
