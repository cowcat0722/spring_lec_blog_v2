package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// 자동 컴퍼넌트 스캔이 된다.
public interface UserJPARepository extends JpaRepository<User, Integer> {

//    @Query("select u from User u where u.username = :username and u.password = :password")
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    Optional<User> findByUsername(@Param("username") String username); // Optional을 이용하면 null 체크 가능
}
