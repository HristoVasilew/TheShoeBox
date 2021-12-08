package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.exeptions.ObjectNotFoundException;
import TheShoeBox.TheShoeBox.model.entity.Comment;
import TheShoeBox.TheShoeBox.model.service.CommentServiceModel;
import TheShoeBox.TheShoeBox.model.view.CommentViewModel;
import TheShoeBox.TheShoeBox.repository.CommentRepository;
import TheShoeBox.TheShoeBox.repository.ShoeRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import TheShoeBox.TheShoeBox.service.CommentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final ShoeRepository shoeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(
            ShoeRepository shoeRepository, UserRepository userRepository,
            CommentRepository commentRepository) {
        this.shoeRepository = shoeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {

        Objects.requireNonNull(commentServiceModel.getCreator());

        var shoe = shoeRepository.
                findById(commentServiceModel.getShoeId()).
                orElseThrow(() -> new ObjectNotFoundException("Shoe with id " + commentServiceModel.getShoeId() + " not found!"));

        var author = userRepository.
                findByEmail(commentServiceModel.getCreator()).
                orElseThrow(() -> new ObjectNotFoundException("User with eamil " + commentServiceModel.getCreator() + " not found!"));

        Comment newComment = new Comment();
        newComment.setApproved(false);
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(LocalDateTime.now());
        newComment.setShoe(shoe);
        newComment.setAuthor(author);

        Comment savedComment = commentRepository.save(newComment);

        return mapAsComment(savedComment);
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long shoeId) {
        var shoeOpt = shoeRepository.
                findById(shoeId);

        if (shoeOpt.isEmpty()) {
            throw new ObjectNotFoundException("Shoe with id " + shoeId + " was not found!");
        }

        return shoeOpt.
                get().
                getComments().
                stream().
                map(this::mapAsComment).
                collect(Collectors.toList());
    }

    private CommentViewModel mapAsComment(Comment commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanApprove(true).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(String.format("%s %s",commentEntity.getAuthor().getFirstName(),commentEntity.getAuthor().getLastName()));


        return commentViewModel;
    }
}
