package com.busanit.spring.e_aop;

public class CalculatorRe implements Calculator {
    @Override
    public long factorial(long num) {
        // 성능을 계산하는 로직

        if (num == 0)
            return 1;
        else {
            return num * factorial(num - 1);
        }
    }

}
