package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository;

    @Transactional
    public void save(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        boardJPARepository.save(reqDTO.toEntity(sessionUser));
    }

    @Transactional
    public void updateById(Integer id, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        Board board = boardJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정 할 권한이 없습니다.");
        }
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
    }

    public Board findById(Integer id) {
        Board board = boardJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        return board;
    }

    @Transactional
    public void deleteById(Integer id, int sessionUserId) {
        Board board = findById(id);

        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제 할 권한이 없습니다.");
        }

        boardJPARepository.deleteById(id);
    }

    public List<Board> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return boardJPARepository.findAll(sort);
    }

    public Board boardDetail(Integer boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        boolean isBoardOwner = false;
        if(sessionUser != null){
            if(sessionUser.getId() == board.getUser().getId()){
                isBoardOwner = true;
            }
        }

        board.setBoardOwner(isBoardOwner);

        board.getReplies().forEach(reply -> {
            boolean isReplyOwner = false;

            if(sessionUser != null){
                if(reply.getUser().getId() == sessionUser.getId()){
                    isReplyOwner = true;
                }
            }
            reply.setReplyOwner(isReplyOwner);
        });
        return board;
    }
}
