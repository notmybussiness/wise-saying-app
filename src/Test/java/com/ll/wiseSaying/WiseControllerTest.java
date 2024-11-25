package com.ll.wiseSaying;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class WiseControllerTest {
    @BeforeEach
    void beforeEach(){
        AppTest.clear();
    }

    @Test
    @DisplayName("등록")
    void t1(){
        final String out = Apptest.run("""
                    등록
                    1번
                    작자미상
                """);
        assertThat(out).contains("명언 :").contains("작가 :").contains("1번 명언이 등록되었습니다.");
    }
}

