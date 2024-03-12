package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String index() {
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
        boardRepository.save(username, title, content);
        return "redirect:/";
    }
}
