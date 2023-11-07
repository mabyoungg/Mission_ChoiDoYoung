package org.example.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Board implements Serializable {
    private int count;
    private String word;
    private String author;
}
