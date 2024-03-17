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
    private final BoardRepository boardRepository;
    private final HttpSession session;

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        List<Board> boardList = boardRepository.findALL();
        req.setAttribute("boardList",boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest req) {
        Board board = boardRepository.findByIdJoinUser(id);
        req.setAttribute("board",board);
        return "board/detail";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardRepository.save(reqDTO.toEntity(sessionUser));
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        boardRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        Board board = boardRepository.findById(id);
        req.setAttribute("board",board);
        return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        boardRepository.updateById(id, reqDTO.getTitle(), reqDTO.getContent());
        return "redirect:/board/" + id;
    }


}
