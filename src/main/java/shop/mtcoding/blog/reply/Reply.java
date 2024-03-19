package shop.mtcoding.blog.reply;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor // Entity는 Default 생성자가 무조건 있어야한다.
@Entity
@Data
@Table(name = "reply_tb")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 프라이머리 키

    private String comment;

    //    @JoinColumn(name = "user___id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // default = user_id

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @CreationTimestamp
    private Timestamp createdAt;

    @Transient
    private boolean isReplyOwner;

    @Builder
    public Reply(Integer id, String comment, User user, Board board, Timestamp createdAt) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.board = board;
        this.createdAt = createdAt;
    }
}
