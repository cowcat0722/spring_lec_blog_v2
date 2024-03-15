package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User findById(int id) {
        User User = em.find(User.class, id);
        return User;
    }

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO) {
        String q = "select u from User u where u.username = :username and u.password = :password";
        User user = em.createQuery(q, User.class)
                .setParameter("username", reqDTO.getUsername())
                .setParameter("password", reqDTO.getPassword())
                .getSingleResult();
        return user;
    }

    @Transactional
    public void update(int id, String password, String email) {
        User user = findById(id);
        user.setPassword(password);
        user.setEmail(email);
    }
}
