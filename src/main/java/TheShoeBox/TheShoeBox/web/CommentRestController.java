package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.NewCommentBindingModel;
import TheShoeBox.TheShoeBox.model.service.CommentServiceModel;
import TheShoeBox.TheShoeBox.model.validator.ApiError;
import TheShoeBox.TheShoeBox.model.view.CommentViewModel;
import TheShoeBox.TheShoeBox.service.CommentService;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService,
                                 ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{shoeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long shoeId,
            Principal principal
    ) {
        return ResponseEntity.ok(
                commentService.getComments(shoeId));
    }

    @PostMapping("/api/{shoeId}/comments")
    public ResponseEntity<CommentViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long shoeId,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel
    ) {
        CommentServiceModel serviceModel =
                modelMapper.map(newCommentBindingModel, CommentServiceModel.class);
        serviceModel.setCreator(principal.getUsername());
        serviceModel.setShoeId(shoeId);

        CommentViewModel newComment =
                commentService.createComment(serviceModel);

        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", shoeId, newComment.getCommentId()));

        return ResponseEntity.
                created(locationOfNewComment).
                body(newComment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }

}
