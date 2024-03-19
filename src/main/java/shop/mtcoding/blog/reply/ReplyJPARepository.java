package shop.mtcoding.blog.reply;

import org.springframework.data.jpa.repository.JpaRepository;

// 자등으로 컴퍼넌트 스캔된다
public interface ReplyJPARepository extends JpaRepository<Reply, Integer> {
}
