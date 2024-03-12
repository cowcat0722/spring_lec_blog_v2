package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Data;

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
    private String createdAt;
}
