package org.example;

import org.example.domain.board.BoardController;
import org.example.global.util.InputRequest;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public void run () {
        Scanner sc = new Scanner(System.in);

        BoardController boardController = new BoardController(sc);
        boardController.load();

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String input = sc.nextLine();
            InputRequest inputRequest = new InputRequest(input);

            String action = inputRequest.getAction();

            if (action.equals("종료")){
                boardController.save();
                return;
            } else if (action.equals("등록")) {
                boardController.create();
            } else if (action.equals("목록")) {
                boardController.list();
            } else if (action.equals("삭제")) {
                boardController.delete(inputRequest);
            } else if (action.equals("수정")) {
                boardController.update(inputRequest);
            } else if (action.equals("빌드")) {
                boardController.jsonBuild();
            } else if (action.equals("빌드2")) {
            boardController.jsonBuildToJackson();
        }
        }
    }
}
