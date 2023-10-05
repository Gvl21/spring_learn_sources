package com.busanit.spring.autowire.service;

public class VersionPrinter {
    private int majorVersion;
    private int minorVersion;

    public void print(){
        System.out.println("버전 %d.%d입니다.".formatted(majorVersion,minorVersion));
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }
}
