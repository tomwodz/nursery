package pl.tomwodz.nursery.controllers.errors;

public class GroupChildrenNotFoundException extends RuntimeException {
    public GroupChildrenNotFoundException(String message) {
        super(message);
    }
}
