package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.service.CommentServiceModel;
import TheShoeBox.TheShoeBox.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    CommentViewModel createComment(CommentServiceModel commentServiceModel);


    List<CommentViewModel> getComments(Long shoeId);

    void initComments();
}
