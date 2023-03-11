package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private static int idCounter = 0;

    private int endorsementCount;
    private int commentCount;

    private final String message;
    private final int id;
    private final Account account;
    private final PostType postType;

    public static List<Post> posts = new ArrayList<>(); // list of all posts
    public List<Endorsement> endorsements = new ArrayList<>(); // list of all endorsements
    public List<Comment> comments = new ArrayList<>(); // list of all comments

    public Post(String message, Account account, PostType postType) {
        this.message = message;
        this.id = idCounter++;
        this.account = account;
        this.postType = postType;
        this.endorsementCount = 0;
        this.commentCount = 0;

    }
    /*
    To allow the creation of meaningful conversation trees, posts must keep
    track of the list of endorsements and comments they received.
    */
    public void addEndorsement(Endorsement endorsement) {
        endorsements.add(endorsement);
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

    public void addComment(Comment comment) {
        comments.add(comment);
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
}
