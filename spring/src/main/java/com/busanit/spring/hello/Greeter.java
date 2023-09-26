package com.busanit.spring.hello;

public class Greeter {

    // 인사를 하는 문자열 포맷
    private String format;

    // 포맷을 설정해주는 설정자 setter
    public void setFormat(String format) {
        this.format = format;
    }

    // 포맷대로 인사해서 문자열을 반환하는 메소드
    public String greet(String guest) {
        return String.format(format, guest);
    }

}
