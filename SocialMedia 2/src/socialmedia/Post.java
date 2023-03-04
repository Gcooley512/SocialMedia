package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private static int idCounter = 0;

    private final String message;
    private final int id;
    private final Account account;
    private final PostType postType;

    public static List<Post> posts = new ArrayList<Post>(); // list of all posts

    public Post(String message, Account account, PostType postType) {
        this.message = message;
        this.id = idCounter++;
        this.account = account;
        this.postType = postType;
    }
    public int getId() {
        return id;
    }

    public PostType getPostType() {
        return postType;
    }
    public String getMessage() {
        return message;
    }
    public String getHandle() {
        return account.getHandle();
    }
    public int getAccountId() {
        return account.getId();
    }
    public Account getAccount() {
        return account;
    }
}
