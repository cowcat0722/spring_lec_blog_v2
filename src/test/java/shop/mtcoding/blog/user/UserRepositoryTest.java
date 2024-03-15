package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToStdout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void update_test() {
        // given
        int id = 1;
        String password = "12345";
        String email = "sssssa@nate.com";
        // when
        userRepository.update(id,password,email);
        em.flush();
        // then

    }

    @Test
    public void findById_test() {
        // given
        int id = 2;
        // when
        userRepository.findById(id);
        // then

    }

    @Test
    public void login_test() {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("ssar");
        reqDTO.setPassword("1234");
        // when
        User user = userRepository.findByUsernameAndPassword(reqDTO);
        // then
//        Assertions.assert
    }
}
