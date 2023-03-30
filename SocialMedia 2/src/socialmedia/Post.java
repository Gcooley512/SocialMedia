package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {

    //class variables
    private static int idCounter = 0;

    private int endorsementCount;
    private int commentCount;

    private final String message;
    private final int id;
    private final Account account;
    private final PostType postType;

    //static list of all posts across all accounts
    public static List<Post> posts = new ArrayList<>();
    //list of all endorsements associated with this post
    public List<Endorsement> endorsements = new ArrayList<>();
    //list of all comments associated with this post
    public List<Comment> comments = new ArrayList<>();

    //constructor
    public Post(String message, Account account, PostType postType) {
        this.message = message;
        this.id = idCounter++;
        this.account = account;
        this.postType = postType;
        this.endorsementCount = 0;
        this.commentCount = 0;

    }

    //endorsement related getters and setters
    public void addEndorsement(Endorsement endorsement) {
        endorsements.add(endorsement);
        posts.add(endorsement);
        endorsementCount++;
    }
    public void removeEndorsement(Endorsement endorsement) {
        endorsements.remove(endorsement);
        endorsementCount--;
    }
    public List<Endorsement> getEndorsements() {
        return endorsements;
    }
    public int getEndorsementCount() {
        return endorsementCount;
    }

    //comment related getters and setters
    public void addComment(Comment comment) {
        comments.add(comment);
        posts.add(comment);
        commentCount++;
    }
    public void removeComment(Comment comment) {
        comments.remove(comment);
        commentCount--;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public int getCommentCount() {
        return commentCount;
    }

    //generic getters
    public int getID() {
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
    public static void resetCounter() {
        idCounter = 0;
    }
}
