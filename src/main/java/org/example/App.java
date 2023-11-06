package org.example;

import java.util.Scanner;

public class App {
    void run () {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();

            if (input.equals("종료")){
                break;
            }
        }
    }
}
