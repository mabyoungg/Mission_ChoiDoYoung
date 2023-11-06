package org.example.domain.board;

import java.util.Scanner;

public class BoardController {
    private Scanner sc;

    public BoardController(Scanner sc) {
        this.sc = sc;
    }

    public void create() {
        System.out.print("명언: ");
        String word = sc.nextLine();

        System.out.print("작가: ");
        String author = sc.nextLine();
    }
}
