import java.util.ArrayList;
import java.util.Scanner;

class WiseSaying {
    String content;
    String author;
    int id;

    WiseSaying(String content, String author, int id) {
        this.content = content;
        this.author = author;
        this.id = id;
    }

    public void setContent(String cont){
        this.content = cont;
    }
    public void setAuthor(String aut){
        this.author = aut;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int WiseId = 1;
        ArrayList<WiseSaying> WiseSayings = new ArrayList<>();

        System.out.println("== 명언 앱==");


        while (true){
            System.out.print("명령) ");
            String Order = scanner.nextLine().trim();
            if(Order.equals("종료")){
                break;
            } else if(Order.equals("등록")){
                System.out.print("명언 : ");
                String Saying = scanner.nextLine().trim();

                System.out.print("작가 : ");
                String Writer = scanner.nextLine().trim();

                System.out.println(WiseId + "번 명언이 등록되었습니다.");
                WiseSayings.add(new WiseSaying(Saying, Writer, WiseId));
                WiseId ++;
            } else if(Order.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i=WiseSayings.size()-1; i>=0; i--){
                    WiseSaying wiseSaying = WiseSayings.get(i);
                    System.out.println(wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content);
                }
            } else if(Order.equals("삭제?id=")){
                int DelId = scanner.nextInt();
                boolean isExist = false;
                for(int i=0; i<WiseSayings.size(); i++){
                    if(WiseSayings.get(i).id == DelId){
                        WiseSayings.remove(i);
                        System.out.println(DelId + "번 명언이 삭제되었습니다.");
                        isExist = true;
                        break;
                    }
                }
                if(!isExist){
                    System.out.println(DelId + "번 명언은 존재하지 않습니다.");
                }
            }  else if(Order.equals("수정?id=")){
                int ReviseId = scanner.nextInt();
                System.out.println("명언(기존): " + WiseSayings.get(ReviseId).content);
                System.out.print("명언 : ");
                String newSaying = scanner.nextLine().trim();
                System.out.println("작가(기존): "+ WiseSayings.get(ReviseId).author);
                System.out.print("작가 : ");
                String newWriter = scanner.nextLine().trim();
                WiseSayings.get(ReviseId).setContent(newSaying);
                WiseSayings.get(ReviseId).setAuthor(newWriter);

            }


        }

        scanner.close();
    }
}