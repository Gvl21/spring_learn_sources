package com.busanit.spring.di;

import java.util.Scanner;

public class MainforAssembler {
    public static void main(String[] args) {
        // 사용자의 입력 받기
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("명령을 입력해주세요.");

            String command = scn.nextLine();
        // exit : 종료
        // new : member를 새로 생성
        // change : 비밀번호를 변경
            if (command.equals("exit")){
                break;
            }
            if(command.startsWith("new ")){
                // 사용자로부터 인자를 공백 간격으로 입력받음
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")){
                processChangeCommand(command.split(" "));
                continue;
            } else printHelp();
        }

    }
    private static Assembler assembler =  new Assembler();
    private static void printHelp() {
        System.out.println("명령어 사용법");
        System.out.println("exit : 프로그램이 종료됩니다.");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비밀번호 변경비밀번호");
    }

    private static void processChangeCommand(String[] args) {
        if (args.length != 4) return;
        ChangePasswordService changePasswordService = assembler.getChangePasswordService();
        try{
            changePasswordService.changePassword(args[1],args[2],args[3]);
            System.out.println("암호가 변경되었습니다.");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일 입니다.");
        } catch (WrongPasswordException e ){
            System.out.println("암호가 틀립니다.");
        }


    }

    private static void processNewCommand(String[] args) {
        // 인자 : [0]new [1]이메일 [2]이름 [3]암호 [4]암호확인
        // 만약 인자 길이가 5개가 아니며 종료
        if(args.length != 5){
            return;
        }
        // 인자 길이가 유효하면, 회원 가입 서비스를 실행 => 조립기로부터 비지니스 로직이 담긴 Service 객체 반환
        var memberRegisterService = assembler.getMemberRegisterService();
        // 회원 가입 요청 객체에서 회원 가입 데이터를 받아
        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setEmail(args[1]);
        registerRequest.setName(args[2]);
        registerRequest.setPassword(args[3]);
        registerRequest.setConfirmPassword(args[4]);

        //암호와 확인암호가 일치하지 않는 경우
        if(!registerRequest.isPasswordEqualToConfirmPassword()){
            System.out.println("암호와 확인암호가 일치하지 않습니다.");
            return;
        }
        // 이미 존재하는 경우
        try {
        // 회원 가입 서비스에서 등록
            memberRegisterService.register(registerRequest);
            System.out.println("성공적으로 등록 완료했습니다.");
            return;
            // 기존 회원 가운데 중복 이메일이 있는 경우
        }catch(DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일이 있습니다.");
        }

    }
}
