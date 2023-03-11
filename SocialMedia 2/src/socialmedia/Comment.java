package socialmedia;

public class Comment extends Post {

    private Post post; // the post that this comment is associated with

    public Comment(String message, Account account, Post post) {
        //the message of the comment
        //the account that made the comment
        //the post that the comment is associated with

        super(message, account, PostType.COMMENT); // call the constructor of the parent class
        this.post = post; // set the post that this comment is associated with
        post.addComment(this); // add this comment to the list of comments of the post
        }

    public Post getPost() {
        return post;
        //returns the post that this comment is associated with
    }
    public void setPost(Post post) {
        this.post = post;
        //sets the post that this comment is associated with
        //this is only used when the post is deleted
    }

}

