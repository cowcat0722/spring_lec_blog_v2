package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
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
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id",id).executeUpdate();
    }

    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        Board board = (Board) query.setParameter("id", id).getSingleResult();
        return board;
    }

    public List<Board> findALL() {
       Query query = em.createQuery("select b from Board  b order by b.id desc",Board.class);
        return query.getResultList();
    }

    @Transactional
    public Board save(Board board) {
        em.persist(board);
        return board;
    }
}
