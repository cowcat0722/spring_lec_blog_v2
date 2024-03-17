package shop.mtcoding.blog.board;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {




}
