package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void save(Board board) {
        em.persist(board);
    }

    public List<Board> findAllV2() {
        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList1 = em.createQuery(q1, Board.class).getResultList();

        // Board에 글을 쓴 유저의 아이디 배열
        int[] ids = boardList1.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();

        // 동적 쿼리 작성
        String q2 = "select u from User u where u.id in (";
        for (int i=0; i<ids.length; i++){
            if(i==ids.length-1){
                q2 = q2 + ids[i]+")";
            }else{
                q2 = q2 + ids[i]+",";
            }
        }
//        System.out.println("q2_test : "+q2);
        // 글을 작성한 유저의 정보 가져오기
        List<User> userList = em.createQuery(q2, User.class).getResultList();

        // 스트림 GPT사용
        List<Board> boardList2 = boardList1.stream()
                .map(board -> {
                    board.setUser(userList.stream()
                            .filter(user -> user.getId() == board.getUser().getId())
                            .findFirst().orElse(null));
                    return board;
                })
                .collect(Collectors.toList());

        return boardList2;
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        Board board = (Board) query.setParameter("id", id).getSingleResult();
        return board;
    }

    public Board findById(int id) {
        // id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);
        return board;
    }
}
