package shop.mtcoding.blog.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// 임포트 필요 없음 (적어도 문제는 없다)
@DataJpaTest
class UserJPARepositoryTest {

    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void save_test() {
        // given
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();
        // when
        userJPARepository.save(user);

        // then

    }

    @Test
    public void findById_test() {
        // given
        int id = 5;
        // when
        Optional<User> userOP = userJPARepository.findById(id);

        if (userOP.isPresent()) {  // userOP가 존재한다면
            User user = userOP.get();
            System.out.println("findById_test : " + user.getId());
        }
        // then

    }

    @Test
    public void findAll_test() {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // when

        userJPARepository.findAll(sort);
        // then

    }

    @Test
    public void findAll_paging_test() throws JsonProcessingException {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 2, sort);
        // when

        Page<User> userPG = userJPARepository.findAll(pageable);
        // then
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userPG);
        System.out.println(json);

    }

    @Test
    public void findByUsernameAndPassword_test() {
        // given
        String username = "ssar";
        String password = "1234";
        // when
        userJPARepository.findByUsernameAndPassword(username, password);
        // then

    }
}