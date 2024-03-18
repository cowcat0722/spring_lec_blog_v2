package shop.mtcoding.blog.board;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // paging 오류때문에 Json변환 할때 user는 무시함.
    private User user; // default = user_id

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}
