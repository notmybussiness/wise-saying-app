import com.llwiseSaying.WiseSayingController;

import java.util.Scanner;

class App {
    private final Scanner scanner;
    private final WiseSayingController wiseSayingController;

    public App() {
        scanner = new Scanner(System.in);
        wiseSayingController = new WiseSayingController(scanner);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("종료")) {
                break;
            }

            if(command.contains("?")){
              String[] query = command.split("\\?");
              String commandName = query[0];
              String commandSpecific = query[1];
              if(commandName.equals("목록")){
                  wiseSayingController.list(commandSpecific);
              }
            }

            switch (command) {
                case "등록":
                    wiseSayingController.write();
                    break;
                case "목록":
                    wiseSayingController.list(null);
                    break;
                case "삭제":
                    wiseSayingController.remove();
                    break;
                case "수정":
                    wiseSayingController.modify();
                    break;
                case "빌드":
                    wiseSayingController.build();
                    break;

            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new App().run();
    }
}