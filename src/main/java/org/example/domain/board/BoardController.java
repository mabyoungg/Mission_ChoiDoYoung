package org.example.domain.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.global.util.InputRequest;

import java.io.*;
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

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"))) {
            oos.writeObject(list);

            System.out.println("데이터를 파일에 저장했습니다.");
        } catch (Exception e) {
            System.out.println("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt"))) {
            list = (List<Board>) ois.readObject();
            count = list.get(list.size() - 1).getCount() + 1;

            System.out.println("데이터를 파일에서 불러왔습니다.");
        } catch (Exception e) {
            System.out.println("파일 불러오기 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public void jsonBuild() {
        StringBuilder jsonString = new StringBuilder("[");

        // 클래스에 정의된 변수 목록 가져오기
//        Field[] fields = list.getClass().getDeclaredFields();
//        String a = fields[0].getName();

        for (Board board : list) {
            jsonString.append("{");
            jsonString.append("\"id\":").append(board.getCount()).append(",");
            jsonString.append("\"content\":\"").append(board.getWord()).append("\",");
            jsonString.append("\"author\":\"").append(board.getAuthor()).append("\"");
            jsonString.append("},");
        }

        if (list.size() > 0) {
            jsonString.deleteCharAt(jsonString.length() - 1);
        }

        jsonString.append("]");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.json"))) {
            writer.write(jsonString.toString());
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
        }

    }

    // jackson 라이브러리 사용
    public void jsonBuildToJackson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("data2.json"), list);
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
        }

    }

}
