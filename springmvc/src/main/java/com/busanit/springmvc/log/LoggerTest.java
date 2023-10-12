package com.busanit.springmvc.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerTest {
    public static void main(String[] args) {
        log.trace("trace");    // 가장 낮은 레벨 실행 흐름을 자세히 추적
        log.debug("debug");    // 2레벨 디버깅을 목적으로 사용(프로덕션에서는 필요한 경우만 활성화)
        log.info("info");     // 기본 레벨(3레벨) 중요한 실행 정보
        log.warn("warn");     // 4레벨 주의해야 할 상황
        log.error("error");    // 5레벨 중요한, 오류 또는 예외 상황(프로덕션에서도 활성화) 중요한 문제 식별

    }
}
