package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String handle;
    private String description;
    private int id;

    public static List<Account> accounts = new ArrayList<Account>();
    public List<Post> posts = new ArrayList<Post>();

    public Account(String handle) {
        this.handle = handle;
        this.description = "";
        this.id = accounts.size();
    }
    public Account(String handle, String description) {
        this.handle = handle;
        this.description = description;
        this.id = accounts.size();
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getHandle() {
        return handle;
    }
    public void setHandle(String handle) {
        this.handle = handle;
    }
    public int getId() {
        return id;
    }
    public void addPost(Post post) {
        posts.add(post);
    }
    public int getPostCount() {
        return posts.size();
    }

}
