package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserJPARepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardJPARepositoryTest {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired BoardService boardService;



}