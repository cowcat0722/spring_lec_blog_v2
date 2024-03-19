package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private User user; // default = user_id

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Reply> replies = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @Transient // 테이블 생성이 안됨
    private boolean isBoardOwner;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}
