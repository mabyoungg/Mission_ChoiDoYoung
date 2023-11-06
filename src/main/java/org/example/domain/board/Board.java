package org.example.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Board {
    private int count;
    private String word;
    private String author;
}
