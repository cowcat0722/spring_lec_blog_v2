package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager em;

    public Board findById(int id) {
        String q = """
                select * from board_tb where id = ?;
                """;
        Board board = (Board) em.createNativeQuery(q,Board.class).setParameter(1,id).getSingleResult();
        return board;
    }

    public List<Board> findALL() {
        String q = """
                select * from board_tb order by id desc
                """;
        List<Board> boardList = em.createNativeQuery(q,Board.class).getResultList();
        return boardList;
    }

    @Transactional
    public void save(String username, String title, String content) {
        String q = """
                insert into board_tb(username, title, content, created_at) values (?,?,?,now())
                """;
        em.createNativeQuery(q, Board.class)
                .setParameter(1,username)
                .setParameter(2,title)
                .setParameter(3,content)
                .executeUpdate();
    }
}
