//package com.llwiseSaying.Test;
//
//import com.llwiseSaying.App;
//import java.io.*;
//import java.util.Scanner;
//
//public class AppTest {
//    public static String run(String input) {
//        // 테스트용 스캐너 생성
//        Scanner scanner = TestUtil.genScanner(input);
//
//        // 출력 스트림 리다이렉션
//        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
//
//        try {
//            // 앱 실행
//            new App(scanner).run();
//
//            // 출력된 결과를 문자열로 변환
//            return output.toString();
//        } finally {
//            TestUtil.clearSetOutToByteArray(output);
//        }
//    }
//}