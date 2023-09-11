package pl.tomwodz.nursery.infrastructure.groupchildren.controller.error;

public class GroupChildrenNotDeleteException extends RuntimeException {
    public GroupChildrenNotDeleteException(String message) {
        super(message);
    }
}
