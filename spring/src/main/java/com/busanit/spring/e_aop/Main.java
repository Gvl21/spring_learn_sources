package com.busanit.spring.e_aop;

public class Main {
    public static void main(String[] args) {
        CalculatorImpl calIm = new CalculatorImpl();
        CalculatorRe calRe = new CalculatorRe();
        long start = System.nanoTime();
        calIm.factorial(30);
        long end = System.nanoTime();
        System.out.printf("CalculatorImpl 실행시간 : %d\n", end-start);
        start = System.nanoTime();
        calRe.factorial(30);
        end = System.nanoTime();
        System.out.printf("CalculatorRe 실행시간 : %d\n", end-start);
    }
}
