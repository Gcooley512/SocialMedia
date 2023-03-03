package socialmedia;

public class Comment extends Post {

    private Post post;

    public Comment(String message, Account account, Post post) {
        super(message, account, false);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}

