package TheShoeBox.TheShoeBox.exeptions;

public class ObjectNotFoundException extends RuntimeException{

    private final Long objectId;

    public ObjectNotFoundException(Long objectId) {
        super("Object with id " + objectId + " not found!");
        this.objectId = objectId;
    }

    public Long getObjectId() {
        return objectId;
    }
}
