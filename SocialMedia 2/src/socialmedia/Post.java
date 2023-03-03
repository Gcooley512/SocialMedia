package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private static int idCounter = 0;
    private String message;
    private final int id;
    private final Account account;
    private final boolean endorsement;
    public List<Comment> comments = new ArrayList<Comment>();

    public Post(String message, Account account, boolean endorsement) {
        this.message = message;
        this.id = idCounter++;
        this.account = account;
        this.endorsement = endorsement;
    }
    public int getId() {
        return id;
    }
    public boolean isEndorsement() {
        return endorsement;
    }
    public String getMessage() {
        return message;
    }
    public String getHandle() {
        return account.getHandle();
    }



}
