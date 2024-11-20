package com.llwiseSaying;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        this.wiseSayingService = new WiseSayingService();
    }

    public void write() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = wiseSayingService.create(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSaying.getId());
    }

    public void list() {
        List<WiseSaying> wiseSayings = wiseSayingService.getList();

        System.out.println("번호 / 작가 / 명언");
        System.out.println("-".repeat(30));

        for (WiseSaying wiseSaying : wiseSayings) {
            System.out.printf("%d / %s / %s\n",
                    wiseSaying.getId(),
                    wiseSaying.getAuthor(),
                    wiseSaying.getContent());
        }
    }

    public void remove() {
        System.out.print("삭제할 명언 번호 : ");
        int id = Integer.parseInt(scanner.nextLine());

        if (wiseSayingService.remove(id)) {
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        } else {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        }
    }

    public void modify() {
        System.out.print("수정할 명언 번호 : ");
        int id = Integer.parseInt(scanner.nextLine());

        WiseSaying wiseSaying = wiseSayingService.get(id);
        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        if (wiseSayingService.modify(id, content, author)) {
            System.out.printf("%d번 명언이 수정되었습니다.\n", id);
        }
    }
}