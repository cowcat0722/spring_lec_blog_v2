package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "board_tb")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 프라이머리 키

    private String title;
    private String content;
    private String username;
    @CreationTimestamp
    private Timestamp createdAt;

    public String getTime() {
        return MyDateUtil.timestampFormat(createdAt);
    }

    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public void update(BoardRequest.UpdateDTO reqDTO) {
        this.title = reqDTO.getTitle();
        this.username = reqDTO.getUsername();
        this.content = reqDTO.getContent();
    }

}
