package pro.sky.telegrambotteamwork.exception;

public class UserNotFoundException extends RuntimeException {

    private final long id;
    public UserNotFoundException(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

