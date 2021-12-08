package TheShoeBox.TheShoeBox.model.service;

public class CommentServiceModel {

    private Long shoeId;
    private String message;
    private String creator;

    public Long getShoeId() {
        return shoeId;
    }

    public CommentServiceModel setShoeId(Long shoeId) {
        this.shoeId = shoeId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentServiceModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public CommentServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}
