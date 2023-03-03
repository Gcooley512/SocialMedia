package socialmedia;

public class Post {

    private static int idCounter = 0;
    private String text;
    private int id;
    private int likes;
    private Account account;
    private boolean endorsement = false;

    public Post(String text, Account account) {
        this.text = text;
        this.id = idCounter++;
        this.likes = 0;
        this.account = account;
    }
    public Post(String text, Account account, boolean endorsement) {
        this.text = text;
        this.id = idCounter++;
        this.likes = 0;
        this.account = account;
        this.endorsement = endorsement;
    }
    public int getId() {
        return id;
    }
    public boolean isEndorsement() {
        return endorsement;
    }
    public String getMessage() {
        return text;
    }
    public String getHandle() {
        return account.getHandle();
    }



}
