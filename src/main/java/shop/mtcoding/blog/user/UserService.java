package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository;

    @Transactional
    public User signUp(UserRequest.JoinDTO reqDTO) {
        // 예외 처리
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()){
            throw new Exception400("중복된 유저네임이 존재합니다.");
        }

        // 핵심 로직
        User sessionUser = userJPARepository.save(reqDTO.toEntity());
        return sessionUser;
    }
}
