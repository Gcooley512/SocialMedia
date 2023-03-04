package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Comment extends Post {

    private final Post post; // the post that this comment is associated with

    public static List<Comment> comments = new ArrayList<Comment>(); // list of all comments

    public Comment(String message, Account account, Post post) {
        //the message of the comment
        //the account that made the comment
        //the post that the comment is associated with

        //call the constructor of the parent class
        super(message, account, PostType.COMMENT);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

}

