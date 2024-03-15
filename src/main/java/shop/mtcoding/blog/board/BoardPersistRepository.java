package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    @Transactional
    public void updateById(int id, String username, String title, String content) {
        String q = """
                update board_tb set username = ?, title = ?, content = ? where id = ?
                """;
        em.createNativeQuery(q)
                .setParameter(1,username)
                .setParameter(2,title)
                .setParameter(3,content)
                .setParameter(4,id)
                .executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
            String q = """
                delete from board_tb where id = ?
                """;
            em.createNativeQuery(q)
                    .setParameter(1, id)
                    .executeUpdate();
    }

    public Board findById(int id) {
        String q = """
                select * from board_tb where id = ?;
                """;
        Board board = (Board) em.createNativeQuery(q, Board.class).setParameter(1, id).getSingleResult();
        return board;
    }

    public List<Board> findALL() {
        String q = """
                select * from board_tb order by id desc
                """;
        List<Board> boardList = em.createNativeQuery(q, Board.class).getResultList();
        return boardList;
    }

    @Transactional
    public Board save(Board board) {
        em.persist(board);
        return board;
    }
}
