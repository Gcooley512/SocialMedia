package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private static int idCounter = 0;
    private int endorsementCount;

    private final String message;
    private final int id;
    private final Account account;
    private final PostType postType;

    public static List<Post> posts = new ArrayList<>(); // list of all posts
    public List<Endorsement> endorsements = new ArrayList<>(); // list of all endorsements

    public Post(String message, Account account, PostType postType) {
        this.message = message;
        this.id = idCounter++;
        this.account = account;
        this.postType = postType;
        this.endorsementCount = 0;

    }
    /*
    To allow the creation of meaningful conversation trees, posts must keep
    track of the list of endorsements and comments they received.
    */
    //todo test
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
