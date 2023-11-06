package org.example;

import org.example.domain.board.Board;
import org.example.domain.board.BoardController;

import java.util.Scanner;

public class App {
    public void run () {
        Scanner sc = new Scanner(System.in);

        BoardController boardController = new BoardController(sc);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();

            if (input.equals("종료")){
                break;
            } else if (input.equals("등록")) {
                boardController.create();
            }
        }
    }
}
