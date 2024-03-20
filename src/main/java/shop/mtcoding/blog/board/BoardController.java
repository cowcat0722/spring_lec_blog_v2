package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    // TODO : 글목록조회 API 필요 -> @GetMapping("/") -> 완료
    @GetMapping("/")
    public ResponseEntity<?> main() {
        List<Board> boardList = boardService.글목록조회();
        return ResponseEntity.ok(new ApiUtil(boardList));
    }

    // TODO : 글상세보기 API 필요 -> @GetMapping("/api/boards/{id}/detail") -> 완료
    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글상세보기(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    // TODO : 글조회 API 필요 -> @GetMapping("/api/boards/{id}") -> 완료
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> findOne(@PathVariable Integer id) {
        Board board = boardService.글조회(id);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    @PostMapping("api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글쓰기(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(board)); // 위험한 코드 : 무한 순환 참조로 오류가 날 수 있음(처음 쓰면 댓글은 빈배열이니 지금은 괜찮음)
    }

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글수정(id, sessionUser.getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil(board));
    }
}
