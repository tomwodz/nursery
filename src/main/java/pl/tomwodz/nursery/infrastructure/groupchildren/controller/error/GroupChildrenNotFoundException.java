package pl.tomwodz.nursery.infrastructure.groupchildren.controller.error;

public class GroupChildrenNotFoundException extends RuntimeException {
    public GroupChildrenNotFoundException(String message) {
        super(message);
    }
}
