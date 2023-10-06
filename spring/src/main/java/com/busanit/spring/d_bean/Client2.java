package com.busanit.spring.d_bean;

public class Client2{
    private String host;
    
    public void connect(){
        System.out.println("Client2.connect");
    }

    public void setHost(String host) {
        this.host = host;
    }

   public void send(){
       System.out.println("클라이언트2 send 호출");
   }

   // destroyMethod
    public void close() {
        System.out.println("클라이언트2 close");
    }

}
