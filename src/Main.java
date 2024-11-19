import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class WiseSaying {
    int id;
    String content;
    String author;

    WiseSaying(String content, String author, int id) {
        this.content = content;
        this.author = author;
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // JSON 형식으로 변환
    public String toJson() {
        return String.format("""
            {
              "id": %d,
              "content": "%s",
              "author": "%s"
            }""", id, content, author);
    }
}

class FileHandler {
    private static final String DIR_PATH = "db/wiseSaying";
    private static final String LAST_ID_FILE = DIR_PATH + "/lastId.txt";

    public FileHandler() {
        // 디렉토리 생성
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void saveWiseSaying(WiseSaying wiseSaying) {
        String filePath = DIR_PATH + "/" + wiseSaying.id + ".json";
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(wiseSaying.toJson());
        } catch (IOException e) { }
    }

    public void saveLastId(int id) {
        try (PrintWriter writer = new PrintWriter(LAST_ID_FILE)) {
            writer.println(id);
        } catch (IOException e) { }
    }

    public void deleteWiseSaying(int id) {
        File file = new File(DIR_PATH + "/" + id + ".json");
        if (file.exists()) {
            file.delete();
        }
    }

    public void buildJson(ArrayList<WiseSaying> wiseSayings){
        try ( PrintWriter writer = new PrintWriter("data.json")){
            writer.println("[");
            for(int i=0; i<wiseSayings.size(); i++){
                writer.print(wiseSayings.get(i).toJson());

                if(i != wiseSayings.size()-1){
                    writer.println(",");
                } else{
                    writer.println("]");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<WiseSaying> loadWiseSayings() {
        ArrayList<WiseSaying> wiseSayings = new ArrayList<>();
        File dir = new File(DIR_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String content = reader.lines().reduce("", String::concat);
                    int id = Integer.parseInt(file.getName().replace(".json", ""));
                    String wiseSayingContent = content.split("\"content\": \"")[1].split("\"")[0];
                    String author = content.split("\"author\": \"")[1].split("\"")[0];
                    wiseSayings.add(new WiseSaying(wiseSayingContent, author, id));
                } catch (IOException e) { }
            }
        }
        return wiseSayings;
    }

    public int loadLastId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LAST_ID_FILE))) {
            return Integer.parseInt(reader.readLine().trim());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        ArrayList<WiseSaying> wiseSayings = fileHandler.loadWiseSayings();
        int wiseSayingId = Math.max(fileHandler.loadLastId() + 1, 1);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("종료")) {
                break;
            }
            else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine().trim();

                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();

                WiseSaying wiseSaying = new WiseSaying(content, author, wiseSayingId);
                wiseSayings.add(wiseSaying);
                fileHandler.saveWiseSaying(wiseSaying);
                fileHandler.saveLastId(wiseSayingId);

                System.out.println(wiseSayingId + "번 명언이 등록되었습니다.");
                wiseSayingId++;
            }
            else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                    WiseSaying wiseSaying = wiseSayings.get(i);
                    System.out.println(wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content);
                }
            }
            else if (command.startsWith("삭제?id=")) {
                int id = Integer.parseInt(command.replace("삭제?id=", ""));
                boolean found = false;

                for (int i = 0; i < wiseSayings.size(); i++) {
                    if (wiseSayings.get(i).id == id) {
                        wiseSayings.remove(i);
                        fileHandler.deleteWiseSaying(id);
                        System.out.println(id + "번 명언이 삭제되었습니다.");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                }
            }
            else if (command.startsWith("수정?id=")) {
                int id = Integer.parseInt(command.replace("수정?id=", ""));
                boolean found = false;

                for (WiseSaying wiseSaying : wiseSayings) {
                    if (wiseSaying.id == id) {
                        System.out.println("명언(기존) : " + wiseSaying.content);
                        System.out.print("명언 : ");
                        String newContent = scanner.nextLine().trim();

                        System.out.println("작가(기존) : " + wiseSaying.author);
                        System.out.print("작가 : ");
                        String newAuthor = scanner.nextLine().trim();

                        wiseSaying.setContent(newContent);
                        wiseSaying.setAuthor(newAuthor);
                        fileHandler.saveWiseSaying(wiseSaying);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                }
            }
            else if(command.equals("빌드")){
                fileHandler.buildJson(wiseSayings);
                System.out.println("data.json 파일의 내용이 갱신되었습니다.");
            }
        }

        scanner.close();
    }
}