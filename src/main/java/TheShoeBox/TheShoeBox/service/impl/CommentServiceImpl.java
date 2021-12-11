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

    @Override
    public void initComments() {

        if (commentRepository.count() == 0) {

            Comment comment1 = new Comment();
            comment1.setId(1L);
            comment1.setShoe(shoeRepository.getById(1L));
            comment1.setApproved(false);
            comment1.setCreated(LocalDateTime.now());
            comment1.setTextContent("много точен. и преди съм поръчвал от него много съм доволен.препоръчвам! ! !");
            comment1.setAuthor(userRepository.getById(1L));

            commentRepository.save(comment1);


            Comment comment2 = new Comment();
            comment2.setId(2L);
            comment2.setShoe(shoeRepository.getById(2L));
            comment2.setApproved(false);
            comment2.setCreated(LocalDateTime.now());
            comment2.setTextContent("Голям лъжец преди месец ги имаше пуснати същите за половин цена аз не бих се доверил! ! !");
            comment2.setAuthor(userRepository.getById(1L));

            commentRepository.save(comment2);

            Comment comment3 = new Comment();
            comment3.setId(3L);
            comment3.setShoe(shoeRepository.getById(3L));
            comment3.setApproved(false);
            comment3.setCreated(LocalDateTime.now());
            comment3.setTextContent("много точен. и преди съм поръчвал от него много съм доволен.препоръчвам! ! !");
            comment3.setAuthor(userRepository.getById(2L));

            commentRepository.save(comment3);


            Comment comment4 = new Comment();
            comment4.setId(4L);
            comment4.setShoe(shoeRepository.getById(4L));
            comment4.setApproved(false);
            comment4.setCreated(LocalDateTime.now());
            comment4.setTextContent("Голям лъжец преди месец ги имаше пуснати същите за половин цена аз не бих се доверил! ! !");
            comment4.setAuthor(userRepository.getById(2L));

            commentRepository.save(comment4);
        }
    }

    private CommentViewModel mapAsComment(Comment commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanApprove(true).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(String.format("%s %s", commentEntity.getAuthor().getFirstName(), commentEntity.getAuthor().getLastName()));


        return commentViewModel;
    }
}
