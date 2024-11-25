package com.ll.wiseSaying;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Scanner;

public class AppTest {
    public static String run(String input) {
        // String 입력을 Scanner로 변환
        Scanner scanner = TestUtil.genScanner(input);

        // 출력 스트림 리다이렉션
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        try {
            // Scanner를 인자로 받는 App 생성자 사용
            new com.llwiseSaying.Test.App(scanner).run();

            return output.toString();
        } finally {
            TestUtil.clearSetOutToByteArray(output);
        }
    }

    public static void clear() {
        // DB 초기화
        File directory = new File("db/wiseSaying");
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
}