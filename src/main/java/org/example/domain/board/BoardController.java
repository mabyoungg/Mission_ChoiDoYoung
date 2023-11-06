package org.example.domain.board;

import java.util.Scanner;

public class BoardController {
    private Scanner sc;
    private int count;

    public BoardController(Scanner sc) {
        this.sc = sc;
        count = 1;
    }

    public void create() {
        System.out.print("명언: ");
        String word = sc.nextLine();

        System.out.print("작가: ");
        String author = sc.nextLine();

        System.out.printf("%d번 명언이 등록되었습니다. \n", count);
        count++;
    }
}
