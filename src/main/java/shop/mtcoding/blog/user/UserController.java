package shop.mtcoding.blog.user;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.board.BoardRequest;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {

            User sessionUser = userService.signUp(reqDTO);
            session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
            User sessionUser = userService.signIn(reqDTO);
            session.setAttribute("sessionUser", sessionUser);
            return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userRepository.findById(sessionUser.getId());
        req.setAttribute("user", user);
        return "/user/update-form";
    }

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        userRepository.update(sessionUser.getId(), reqDTO.getPassword(), reqDTO.getEmail());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
