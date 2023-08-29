package pl.tomwodz.nursery.exception;

public class GroupChildrenNotFoundException extends RuntimeException {
    public GroupChildrenNotFoundException(String message) {
        super(message);
    }
}
