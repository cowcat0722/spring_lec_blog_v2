package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardNativeRepository boardNativeRepository;

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        List<Board> boardList = boardNativeRepository.findALL();
        req.setAttribute("boardList",boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @GetMapping("/board/1")
    public String detail() {
        return "board/detail";
    }

    @PostMapping("/board/save")
    public String save(String username, String title, String content) {
        boardNativeRepository.save(username, title, content);
        return "redirect:/";
    }
}
