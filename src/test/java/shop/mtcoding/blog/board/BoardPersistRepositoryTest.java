package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository boardPersistRepository;

    @Test
    public void save_test() {
        // given
        Board board = new Board("제목5","내용","홍길동");

        // when
        boardPersistRepository.save(board);
        System.out.println("save_test : " + board);
        // then
    }

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "수정 제목1";
        String content = "수정 내용1";
        String username = "수정 이름";
        // when
        boardPersistRepository.updateById(id,username,title,content);
        // then
        Board board = boardPersistRepository.findById(id);
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getUsername()).isEqualTo(username);
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;
        // when
        boardPersistRepository.deleteById(id);
        //then
        List<Board> boardList = boardPersistRepository.findALL();
        assertThat(boardList.size()).isEqualTo(3);
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Board board = boardPersistRepository.findById(id);
        // then
//        System.out.println("findById_test : " + board);

        // org.assertj.core.api
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");

    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardPersistRepository.findALL();
        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }
}
