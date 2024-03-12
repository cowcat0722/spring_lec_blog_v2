package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardPersistRepository boardPersistRepository;

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        List<Board> boardList = boardPersistRepository.findALL();
        req.setAttribute("boardList",boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest req) {
        Board board = boardPersistRepository.findById(id);
        req.setAttribute("board",board);
        return "board/detail";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        System.out.println("reqDTO : " + reqDTO);
        boardPersistRepository.save(reqDTO.toEntity());
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String save(@PathVariable Integer id) {
        boardPersistRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        Board board = boardPersistRepository.findById(id);
        req.setAttribute("board",board);
        return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, String username, String title, String content) {
        boardPersistRepository.updateById(id, username, title, content);
        return "redirect:/board/"+id;
    }
}
