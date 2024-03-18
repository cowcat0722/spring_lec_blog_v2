package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        List<Board> boardList = boardService.글목록조회();
        req.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글상세보기(id, sessionUser);

        req.setAttribute("board", board);
        return "board/detail";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글쓰기(reqDTO, sessionUser);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        Board board = boardService.글조회(id);
        req.setAttribute("board", board);
        return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글수정(id, sessionUser.getId(), reqDTO);
        return "redirect:/board/" + id;
    }
}
