package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// 자동 컴퍼넌트 스캔이 됨
public interface UserJPARepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(@Param("username") String username);
}
