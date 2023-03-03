package socialmedia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * BadSocialMedia is a minimally compiling, but non-functioning implementor of
 * the SocialMediaPlatform interface.
 *
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMedia implements SocialMediaPlatform {

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

        /*
         * The method creates an account in the platform with the given handle.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param handle account's handle.
         * @throws IllegalHandleException if the handle already exists in the platform.
         * @throws InvalidHandleException if the new handle is empty, has more than 30
         *                                characters, or has white spaces.
         * @return the ID of the created account.
         *
         */

        if (handle == null) {
            throw new InvalidHandleException("Invalid handle");
        }
        if (handle.isEmpty()){
            throw new InvalidHandleException("No handle given");
        }
        if(handle.length() > 30){
            throw new InvalidHandleException("Handle too long");
        }
        if(handle.contains(" ")){
            throw new InvalidHandleException("Handle contains spaces");
        }

        for (Account account : Account.accounts) {
             if (account.getHandle().equals(handle)) {
                throw new IllegalHandleException("Handle already exists");
            }
        }

        Account newAccount = new Account(handle);
        Account.accounts.add(newAccount);
        return newAccount.getId();
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {

        /*
         * The method creates an account in the platform with the given handle and
         * description.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param handle      account's handle.
         * @param description account's description.
         * @throws IllegalHandleException if the handle already exists in the platform.
         * @throws InvalidHandleException if the new handle is empty, has more than 30
         *                                characters, or has white spaces.
         * @return the ID of the created account.
         */

        if (handle == null) {
            throw new InvalidHandleException("Invalid handle");
        }
        if (handle.isEmpty()){
            throw new InvalidHandleException("No handle given");
        }
        if(handle.length() > 30){
            throw new InvalidHandleException("Handle too long");
        }
        if(handle.contains(" ")){
            throw new InvalidHandleException("Handle contains spaces");
        }
        for (Account account : Account.accounts) {
            if (account.getHandle().equals(handle)) {
                throw new IllegalHandleException("Handle already exists");
            }
        }

        Account newAccount = new Account(handle, description);
        Account.accounts.add(newAccount);
        return newAccount.getId();

    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        /*
         * The method removes the account with the corresponding ID from the platform.
         * When an account is removed, all of their posts and likes should also be
         * removed.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param id ID of the account.
         * @throws AccountIDNotRecognisedException if the ID does not match to any
         *                                         account in the system.
         */
        //search through all the accounts and find the one with the id
        //then remove it
        boolean found = false;
        for (Account account: Account.accounts) {
            if (account.getId() == id) {
                //remove all the posts
                for (Post post: account.posts) {
                    account.posts.remove(post);
                }
                Account.accounts.remove(account);
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new AccountIDNotRecognisedException("Account not found");
        }
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        /*
         * The method removes the account with the corresponding handle from the
         * platform. When an account is removed, all of their posts and likes should
         * also be removed.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param handle account's handle.
         * @throws HandleNotRecognisedException if the handle does not match to any
         *                                      account in the system.
         */
        // same as above but search with handle rather than id
        boolean found = false;
        for (Account account: Account.accounts) {
            if (account.getHandle().equals(handle)) {
                for (Post post: account.posts) { //for all the post posts
                    account.posts.remove(post); //sequentially remove all the posts
                }
                Account.accounts.remove(account);
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("Handle not found");
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        /*
         * The method replaces the oldHandle of an account by the newHandle.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param oldHandle account's old handle.
         * @param newHandle account's new handle.
         * @throws HandleNotRecognisedException if the old handle does not match to any
         *                                      account in the system.
         * @throws IllegalHandleException       if the new handle already exists in the
         *                                      platform.
         * @throws InvalidHandleException       if the new handle is empty, has more
         *                                      than 30 characters, or has white spaces.
         */
        //search through all the accounts and find the one with the handle
        //then update the handle
        boolean found = false;

        //check if new handle already exists
        for (Account search: Account.accounts) {
            if (search.getHandle().equals(newHandle)) {
                throw new IllegalHandleException("Handle already exists");
            }
        }

        //check if new handle is valid
        if (newHandle == null || newHandle.isEmpty() || newHandle.length() > 30 || newHandle.contains(" ")) {
            throw new InvalidHandleException("Invalid handle");
        }


        for (Account account: Account.accounts) {
            if (account.getHandle().equals(oldHandle)) {
                account.setHandle(newHandle);
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("cant find handle");
        }
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        /*
         * The method updates the description of the account with the respective handle.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param handle      handle to identify the account.
         * @param description new text for description.
         * @throws HandleNotRecognisedException if the handle does not match to any
         *                                      account in the system.
         */

        //update the description of the account
        //search through all the accounts and find the one with the handle
        //then update the description
        boolean found = false;

        for (Account account: Account.accounts) {
            if (account.getHandle().equals(handle)) {
                account.setDescription(description);
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("Cant find handle");
        }

    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        /*
          The method creates a formatted string summarising the stats of the account
          identified by the given handle. The template should be:

          <pre>
          ID: [account ID]
          Handle: [account handle]
          Description: [account description]
          Post count: [total number of posts, including endorsements and replies]
          Endorse count: [sum of endorsements received by each post of this account]
          </pre>

          @param handle handle to identify the account.
         * @return the account formatted summary.
         * @throws HandleNotRecognisedException if the handle does not match to any
         *                                      account in the system.
         */

        //search through all the accounts and find the one with the handle
        //then create the string with all details of the account

        boolean found = false;
        String accountDetails = "";
        StringBuilder sb = new StringBuilder();

        for (Account account: Account.accounts) {
            if (account.getHandle().equals(handle)) {
                sb.append("ID: [").append(account.getId()).append("] \n");
                sb.append("Handle: [").append(account.getHandle()).append("] \n");
                if (!Objects.equals(account.getDescription(), "")) {
                    sb.append("Description: [").append(account.getDescription()).append("] \n");
                } else {
                    sb.append("Description: No Description \n");
                }
                    sb.append("Post count: [").append(account.getPostCount()).append("] \n");
                // TODO sb.append("Endorse count: [" + account.getEndorseCount() + "] \n");
                accountDetails = sb.toString();
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("Cant find handle");
        }
        return accountDetails;
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        /*
         * The method creates a post for the account identified by the given handle with
         * the following message.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param handle  handle to identify the account.
         * @param message post message.
         * @throws HandleNotRecognisedException if the handle does not match to any
         *                                      account in the system.
         * @throws InvalidPostException         if the message is empty or has more than
         *                                      100 characters.
         * @return the sequential ID of the created post.
         */
        int postID = 0;
        boolean found = false;

        if (message == null || message.isEmpty()) {
            throw new InvalidPostException("Message is empty");
        }
        if (message.length() > 100) {
            throw new InvalidPostException("Message is too long");
        }

        for (Account account: Account.accounts) {
            if (account.getHandle().equals(handle)) {
                Post post = new Post(message, account, false); //create a new post
                account.addPost(post); //add the post to the account
                postID = post.getId(); //get the id of the new post
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("Cant find account");
        }
        //return the id of the new post
        return postID;
        //TODO test
    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException{
        /*
         * The method creates an endorsement post of an existing post, similar to a
         * retweet on Twitter. An endorsement post is a special post. It contains a
         * reference to the endorsed post and its message is formatted as:
         * <p>
         * <code>"EP@" + [endorsed account handle] + ": " + [endorsed message]</code>
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param handle of the account endorsing a post.
         * @param id     of the post being endorsed.
         * @return the sequential ID of the created post.
         * @throws HandleNotRecognisedException if the handle does not match to any
         *                                      account in the system.
         * @throws PostIDNotRecognisedException if the ID does not match to any post in
         *                                      the system.
         * @throws NotActionablePostException   if the ID refers to a endorsement post.
         *                                      Endorsement posts are not endorsable.
         *                                      Endorsements are not transitive. For
         *                                      instance, if post A is endorsed by post
         *                                      B, and an account wants to endorse B, in
         *                                      fact, the endorsement must refers to A.

         */
        //search through all the accounts and find the one with the handle
        //then create the endorsement post

        boolean found = false;
        int postID = 0;
        Account endorsingAccount = null;

        for (Account account: Account.accounts) {
            if (account.getHandle().equals(handle)) {
                endorsingAccount = account;
                found = true;
                break; //we can stop looking now know the account trying to endorse exists
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("Cant find handle");
        }

        //search through all the posts and find the one with the id

        found = false;
        for (Account account: Account.accounts) {
            for (Post post: account.posts) {
                if (post.getId() == id) {
                    if (post.isEndorsement()) {
                        throw new NotActionablePostException("Cant endorse an endorsement");
                    }
                    //TODO test this
                    Post newPost = new Post("EP@" + post.getHandle() + ": " + post.getMessage(), endorsingAccount, true); //create a new post
                    endorsingAccount.addPost(newPost); //add the post to the account
                    postID = newPost.getId(); //get the id of the new post
                    found = true;
                    break; //we can stop looking now know the post exists
                }
            }
        }
        if (!found) {
            throw new PostIDNotRecognisedException("Cant find post");
        }

        return postID;
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        /*
         * The method creates a comment post referring to an existing post, similarly to
         * a reply on Twitter. A comment post is a special post. It contains a reference
         * to the post being commented upon.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param handle  of the account commenting a post.
         * @param id      of the post being commented.
         * @param message the comment post message.
         * @return the sequential ID of the created post.
         * @throws HandleNotRecognisedException if the handle does not match to any
         *                                      account in the system.
         * @throws PostIDNotRecognisedException if the ID does not match to any post in
         *                                      the system.
         * @throws NotActionablePostException   if the ID refers to a endorsement post.
         *                                      Endorsement posts are not endorsable.
         *                                      Endorsements cannot be commented. For
         *                                      instance, if post A is endorsed by post
         *                                      B, and an account wants to comment B, in
         *                                      fact, the comment must refers to A.
         * @throws InvalidPostException         if the comment message is empty or has
         *                                      more than 100 characters.
         */
        //search through all the accounts and find the one with the handle
        //then create the endorsement post

        boolean found = false;
        int postID = 0;
        Account commentingAccount = null;

        for (Account account: Account.accounts) {
            if (account.getHandle().equals(handle)) {
                commentingAccount = account;
                found = true;
                break; //we can stop looking now know the account trying to endorse exists
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("Cant find handle");
        }

        //search through all the posts and find the one with the id

        found = false;

        for (Account account: Account.accounts) {
            for (Post post: account.posts) {
                if (post.getId() == id) {
                    if (post.isEndorsement()) {
                        throw new NotActionablePostException("Cant comment an endorsement");
                    }
                    //TODO test this
                    //make a new comment on this post

                    found = true;
                    break; //we can stop looking now know the post exists
                }
            }
        }
        return 0;
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub


    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id)
            throws PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfAccounts() {
        return Account.accounts.size() - 1;
    }

    @Override
    public int getTotalOriginalPosts() {
        int total = 0;
        for (Account account: Account.accounts) {
            for (Post post: account.posts) {
                if (!post.isEndorsement()) {
                    total++;
                }
            }
        }
        return total;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        int total = 0;
        for (Account account: Account.accounts) {
            for (Post post: account.posts) {
                if (post.isEndorsement()) {
                    total++;
                }
            }
        }
        return 0;
    }

    @Override
    public int getTotalCommentPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMostEndorsedPost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMostEndorsedAccount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void erasePlatform() {
        //erase all accounts and posts
        Account.accounts.clear();
        //todo Post.posts.clear();
    }

    @Override
    public void savePlatform(String filename) throws IOException {
        //saves the platform to a file
        //save the account and posts
        //save the account

        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos); {
            oos.writeObject(Account.accounts);
            //todo oos.writeObject(Post.posts);
        }
    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        //load the platform from the file created in savePlatform


    }

}
