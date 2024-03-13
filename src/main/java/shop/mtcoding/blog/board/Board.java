package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor // Entity는 Default 생성자가 무조건 있어야한다.
@Entity
@Data
@Table(name = "board_tb")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 프라이머리 키

    private String title;
    private String content;

//    @JoinColumn(name = "user___id")
    @ManyToOne
    private User user; // deault = user_id

    @CreationTimestamp
    private Timestamp createdAt;

}
