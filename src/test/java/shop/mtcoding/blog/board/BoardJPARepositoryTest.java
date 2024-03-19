package shop.mtcoding.blog.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    // save
    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);
        // then
        System.out.println("save_test : id = " +board.getId());
    }

    // findById
    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);
        if (boardOP.isPresent()){
            Board board = boardOP.get();
            System.out.println("findById_test : " + board);
        }
        // then

    }

    @Test
    public void findByIdJoinUserAndReplies_test() {
        // given
        int id = 4;
        // when
        Board board = boardJPARepository.findByIdJoinUserAndReplies(id).get();
        // then
    }

    // findByIdJoinUser
    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;
        // when
        Board board = boardJPARepository.findByIdJoinUser(id).get();
        // then

    }

    // findAll (sort)
    @Test
    public void findAll_test() {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // when

        boardJPARepository.findAll(sort);
        // then

    }

    // findAll (page)
    @Test
    public void findAll_paging_test() throws JsonProcessingException {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 2, sort);
        // when

        Page<Board> boardPG = boardJPARepository.findAll(pageable);
        // then
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(boardPG);
        System.out.println(json);

    }

    // deleteById_test
    @Test
    public void deleteById_test() {
        // given
        int id = 1;
        // when
        boardJPARepository.deleteById(id);
        // then
        em.flush();
    }
}