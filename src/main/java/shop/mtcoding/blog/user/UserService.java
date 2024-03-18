package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IoC 등록
public class UserService {

    private final UserJPARepository userJPARepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO reqDTO) {
        // 유저네임 중복검사(예외처리)
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저네임이 존재합니다.");
        }

        userJPARepository.save(reqDTO.toEntity());
    }

    public User 로그인(UserRequest.LoginDTO reqDTO) {
        // 조회를 했는데 값이 null 이면 throw를 날림
        // null 이 아니면 sessionUser에 저장
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return sessionUser;
    }

    public User 회원수정폼(int id) {
        return userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
    }

    @Transactional
    public User 회원수정(int id, UserRequest.UpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));

        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    }
}
