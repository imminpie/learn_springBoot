package com.codingrecipe.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)  // board_file_table 을 기준으로 board_table 과 N : 1 관계
    @JoinColumn(name = "board_id")      // DB 에 만들어지는 조인 컬럼 이름
    private BoardEntity boardEntity;    // board_id 라고 해서 Long 타입이 아니라 부모 엔티티 타입으로 설정해 주어야 한다.
    // mappedBy 와 매칭이 된다.

    public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName) {

        System.out.println("boardEntity = " + boardEntity);
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(boardEntity);
        return boardFileEntity;
    }

}
