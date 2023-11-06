package org.example.domain.board;

import org.example.global.util.InputRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    private Scanner sc;
    private int count;
    private List<Board> list;

    public BoardController(Scanner sc) {
        this.sc = sc;
        count = 1;
        list = new ArrayList<>();
    }

    public void create() {
        System.out.print("명언: ");
        String word = sc.nextLine();

        System.out.print("작가: ");
        String author = sc.nextLine();

        Board board = new Board(count, word, author);

        list.add(board);
        count++;

        System.out.printf("%d번 명언이 등록되었습니다. \n", board.getCount());
    }

    public void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = list.size() - 1; i >= 0; i--){
            System.out.printf("%d / %s / %s \n", list.get(i).getCount(),list.get(i).getAuthor(),list.get(i).getWord());
        }
    }

    public void delete(InputRequest inputRequest) {
        int id = inputRequest.getIndexByParam("id",0);
        int index = getIndexByList(id);

        try {
            list.remove(index);
            System.out.printf("%d번 명언이 삭제되었습니다. \n", id);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("%d번 명언이 존재하지 않습니다. \n", id);
        }
    }

    public void update(InputRequest inputRequest) {
        int id = inputRequest.getIndexByParam("id",0);
        int index = getIndexByList(id);

        try {
            System.out.printf("명언(기존) : %s \n", list.get(index).getWord());
            System.out.print("명언: ");
            String word = sc.nextLine();
            System.out.printf("작가(기존) : %s \n", list.get(index).getAuthor());
            System.out.print("작가: ");
            String author = sc.nextLine();

            Board board = new Board(id, word, author);

            list.set(index, board);

        } catch (IndexOutOfBoundsException e) {
            System.out.printf("%d번 명언이 존재하지 않습니다. \n", id);
        }

    }

    public int getIndexByList(int id) {
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getCount() == id){
                return i;
            }
        }
        return -1;
    }
}
