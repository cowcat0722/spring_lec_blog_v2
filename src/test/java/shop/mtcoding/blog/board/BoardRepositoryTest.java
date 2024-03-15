package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "수정 제목1";
        String content = "수정 내용1";
        // when
        boardRepository.updateById(id, title, content);
        em.flush(); // 실제 코드는 작성할 필요가 없다. 이유는? 트랜잭션이 종료될꺼니까!
        // then
    }

    @Test
    public void deleteById_test() {
        int id = 1;
        boardRepository.deleteById(id);
        System.out.println("deleteById_test : "+ boardRepository.findAll().size());
    }

    @Test
    public void findAllV2_test() {

        List<Board> boardList = boardRepository.findAllV2();
        System.out.println(boardList);

    }

    @Test
    public void randomquery_test(){
        int[] ids = {5,1,2,3,4};

        // select u from User u where u.id in (?,?);
        String q = "select u from User u where u.id in (";
        for (int i=0; i<ids.length; i++){
            if(i==ids.length-1){
                q = q + ":id"+ ids.length+")";
            }else{
                q = q + ":id"+(i+1)+",";
            }
        }
        System.out.println("randomquery_test : "+q);
    }

    @Test
    public void findAll_lazyLoading_test() {
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i : userIds) {
            System.out.println(i);
        }
        // select * from user_tb where id in (3,2,1,1);
        // select * from user_tb where id in (3,2,1);
    }

    @Test
    public void findAll_test() {
        // given

        // when
        boardRepository.findAll();
        // then

    }

    @Test
    public void findById_test() {
        int id = 1;
        boardRepository.findById(id);
    }
}
