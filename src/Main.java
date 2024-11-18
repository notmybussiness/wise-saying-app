import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 명언 앱==");

        while (true){
            System.out.print("명령) ");
            if(scanner.nextLine().equals("종료")){
                break;
            } else if(scanner.nextLine().equals("등록")){
                System.out.print("명언 : ");
                String WiseSaying = scanner.nextLine();

                System.out.print("작가 : ");
                String WiseWriter = scanner.nextLine();
            }
        }
    }
}