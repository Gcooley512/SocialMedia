package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String handle;
    private String description;
    private final int id;
    private int postCount;

    public static List<Account> accounts = new ArrayList<Account>();

    public Account(String handle) {
        this.handle = handle;
        this.description = "";
        this.id = accounts.size();
        this.postCount = 0;
    }
    public Account(String handle, String description) {
        this.handle = handle;
        this.description = description;
        this.id = accounts.size();
        this.postCount = 0;
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
    public int getPostCount() {
        return postCount;
    }
    public void addPost() {
        this.postCount++;
    }
    public void removePost() {
        this.postCount--;
    }
}
