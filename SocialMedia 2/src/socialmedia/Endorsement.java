package socialmedia;

public class Endorsement extends Post {

    private final Post post; // the post that this endorsement is associated with

    public Endorsement(String message, Account account, Post post) {
        //the message of the endorsement
        //the account that made the endorsement
        //the post that the endorsement is associated with

        //call the constructor of the parent class
        super(message, account, PostType.ENDORSEMENT);
        this.post = post;
        post.addEndorsement(this);
    }

    public Post getPost() {
        return post;
    }

}

