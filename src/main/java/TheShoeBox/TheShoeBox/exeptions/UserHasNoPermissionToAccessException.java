package TheShoeBox.TheShoeBox.exeptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Client tries to access without being logged in.")
public class UserHasNoPermissionToAccessException extends RuntimeException {
    public UserHasNoPermissionToAccessException(String message) {
        super(message);
    }
}
