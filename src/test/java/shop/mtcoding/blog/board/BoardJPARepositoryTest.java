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

    @Test
    public void save_test() {
        // given
        User user = User.builder()
                .username("chan")
                .password("12345")
                .email("chan@nate.com")
                .build();
        // when
        userJPARepository.save(user);
        List<User> userList = userJPARepository.findAll();
        // then
        System.out.println("save_test : " + userList.size());
    }

}