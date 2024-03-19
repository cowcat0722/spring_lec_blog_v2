package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;

// 자동 컴퍼넌트 스캔됨.
public interface BoardJPARepository extends JpaRepository<Board, Integer> {
}
