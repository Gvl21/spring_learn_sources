package com.busanit.spring.e_aop;

public class CalculatorImpl implements Calculator{

    @Override
    public long factorial(long num) {
        long result = 1;
        for(int i = 0; i < num; i++){
            result *= i;
        }

        return result;
    }
}
