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
                delete from Board b where id = :id
                """;
            em.createQuery(q)
                    .setParameter("id", id)
                    .executeUpdate();
    }

    public Board findById(int id) {
        Board board = em.find(Board.class,id);
        return board;
    }

    public List<Board> findALL() {
        String q = """
                select b from Board b order by b.id desc
                """;
        List<Board> boardList = em.createQuery(q, Board.class).getResultList();
        return boardList;
    }

    @Transactional
    public Board save(Board board) {
        em.persist(board);
        return board;
    }
}
