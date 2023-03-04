package socialmedia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

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
                Account.accounts.remove(account);
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new AccountIDNotRecognisedException("Account not found");
        }

        //remove all the posts
        Post.posts.removeIf(post -> post.getAccountId() == id);
        //todo remove all comments and remove all endorsements

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
                Account.accounts.remove(account);
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("Handle not found");
        }
        Post.posts.removeIf(post -> Objects.equals(post.getHandle(), handle));
        //todo remove all comments and remove all endorsements

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
                // TODO sb.append("Endorse count: [" + account.getEndorseCount() + "] \n"); //number of endorsements for all posts
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
                Post post = new Post(message, account, PostType.POST); //create a new post
                Post.posts.add(post); //add the post to the list of posts

                account.addPost(); //add one to the number of posts for the account
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

        //search through all the accounts and find the one with the handle then save it so i can associate it with the new post
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
        for (Post post: Post.posts) {
            if (post.getId() == id) {
                if (post.getPostType() == PostType.ENDORSEMENT) {
                    throw new NotActionablePostException("Cant endorse an endorsement");
                }

                //TODO test this

                Endorsement newEndorsement = new Endorsement(
                        "EP@" +
                        post.getHandle() +
                        ": " +
                        post.getMessage(),
                        endorsingAccount,
                        post); //create a new post

                Endorsement.endorsements.add(newEndorsement); //add the post to the list of posts
                endorsingAccount.addPost(); //add 1 to the count of posts for the account
                postID = newEndorsement.getId(); //get the id of the new post
                found = true;
                break; //we can stop looking now know the post exists
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

        if (message == null || message.isEmpty()) {
            throw new InvalidPostException("Message is empty");
        }
        if (message.length() > 100) {
            throw new InvalidPostException("Message is too long");
        }

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

        for (Post post: Post.posts) {
            if (post.getId() == id) {
                if (post.getPostType() == PostType.ENDORSEMENT) {
                    throw new NotActionablePostException("Cant comment an endorsement");
                }
                //TODO test this
                //make a new comment on this post
                Comment newComment = new Comment(message, commentingAccount, post);
                Comment.comments.add(newComment);
                commentingAccount.addPost(); //add 1 to the number of posts
                postID = newComment.getId(); //get the id of the new post
                found = true;
                break; //we can stop looking now know the post exists
            }
        }

        if (!found) {
            throw new PostIDNotRecognisedException("Cant find post");
        }

        return postID;
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        /*
         * The method removes the post from the platform. When a post is removed, all
         * its endorsements should be removed as well. All replies to this post should
         * be updated by replacing the reference to this post by a generic empty post.
         * <p>
         * The generic empty post message should be "The original content was removed
         * from the system and is no longer available.". This empty post is just a
         * replacement placeholder for the post which a reply refers to. Empty posts
         * should not be linked to any account and cannot be acted upon, i.e., it cannot
         * be available for endorsements or replies.
         * <p>
         * The state of this SocialMediaPlatform must be be unchanged if any exceptions
         * are thrown.
         *
         * @param id ID of post to be removed.
         * @throws PostIDNotRecognisedException if the ID does not match to any post in
         *                                      the system.
         */
        //search through all the posts and find the one with the id
        boolean found = false;

        for (Post post: Post.posts) {
            if (post.getId() == id) {

                Post.posts.remove(post); //remove the post

                post.getAccount().removePost(); //minus 1 from the number of posts on the account that posted it

                found = true;
                break; //we can stop looking now know the post exists
            }
        }

        if (!found) {
            throw new PostIDNotRecognisedException("Cant find post");
        }
        //TODO remove all endorsements of this post and replace all comments with a generic empty post
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        /*
         * The method generates a formated string containing the details of a single
         * post. The format is as follows:
         *
         * <pre>
         * ID: [post ID]
         * Account: [account handle]
         * No. endorsements: [number of endorsements received by the post] | No. comments: [number of comments received by the post]
         * [post message]
         * </pre>
         *
         * @param id of the post to be shown.
         * @return a formatted string containing post's details.
         * @throws PostIDNotRecognisedException if the ID does not match to any post in
         *                                      the system.
         */
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id)
            throws PostIDNotRecognisedException, NotActionablePostException {
        /*
         * The method builds a StringBuilder showing the details of the current post and
         * all its children posts. The format is as follows (you can use tabs or spaces to represent indentation):
         *
         * <pre>
         * {@link #showIndividualPost(int) showIndividualPost(id)}
         * |
         * [for reply: replies to the post sorted by ID]
         * |  > {@link #showIndividualPost(int) showIndividualPost(reply)}
         * </pre>
         *
         * See an example:
         *
         * <pre>
         * ID: 1
         * Account: user1
         * No. endorsements: 2 | No. comments: 3
         * I like examples.
         * |
         * | > ID: 3
         *     Account: user2
         *     No. endorsements: 0 | No. comments: 1
         *     No more than me...
         *     |
         *     | > ID: 5
         *         Account: user1
         *         No. endorsements: 0 | No. comments: 1
         *         I can prove!
         *         |
         *         | > ID: 6
         *             Account: user2
         *             No. endorsements: 0 | No. comments: 0
         *             prove it
         * | > ID: 4
         *     Account: user3
         *     No. endorsements: 4 | No. comments: 0
         *     Can't you do better than this?
         *
         * | > ID: 7
         *     Account: user5
         *     No. endorsements: 0 | No. comments: 1
         *     where is the example?
         *     |
         *     | > ID: 10
         *         Account: user1
         *         No. endorsements: 0 | No. comments: 0
         *         This is the example!
         * </pre>
         *
         * Continuing with the example, if the method is called for post ID=5
         * ({@code showIndividualPost(5)}), the return would be:
         *
         * <pre>
         * ID: 5
         * Account: user1
         * No. endorsements: 0 | No. comments: 1
         * I can prove!
         * |
         * | > ID: 6
         *     Account: user2
         *     No. endorsements: 0 | No. comments: 0
         *     prove it
         * </pre>
         *
         * @param id of the post to be shown.
         * @return a formatted StringBuilder containing the details of the post and its
         *         children.
         * @throws PostIDNotRecognisedException if the ID does not match to any post in
         *                                      the system.
         * @throws NotActionablePostException   if the ID refers to an endorsement post.
         *                                      Endorsement posts do not have children
         *                                      since they are not endorsable nor
         *                                      commented.
         */
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfAccounts() {
        /*
         * This method returns the current total number of accounts present in the
         * platform. Note, this is NOT the total number of accounts ever created since
         * the current total should discount deletions.
         *
         * @return the total number of accounts in the platform.
         */
        return Account.accounts.size() - 1;
    }

    @Override
    public int getTotalOriginalPosts() {
        /*
         * This method returns the current total number of original posts (i.e.,
         * disregarding endorsements and comments) present in the platform. Note, this
         * is NOT the total number of posts ever created since the current total should
         * discount deletions.
         *
         * @return the total number of original posts in the platform.
         */

        int total = 0;

        for (Post post: Post.posts) {
            if (post.getPostType() == PostType.POST) {
                total++;
            }
        }
        return total;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        /*
         * This method returns the current total number of endorsement posts present in
         * the platform. Note, this is NOT the total number of endorsements ever created
         * since the current total should discount deletions.
         *
         * @return the total number of endorsement posts in the platform.
         */

        int total = 0;

        for (Post post: Post.posts) {
            if (post.getPostType() == PostType.ENDORSEMENT) {
                total++;
            }
        }
        return total;
    }

    @Override
    public int getTotalCommentPosts() {
        /*
         * This method returns the current total number of comments posts present in the
         * platform. Note, this is NOT the total number of comments ever created since
         * the current total should discount deletions.
         *
         * @return the total number of comments posts in the platform.
         */

        int total = 0;

        for (Post post: Post.posts) {
            if (post.getPostType() == PostType.COMMENT) {
                total++;
            }
        }
        return total;
    }

    @Override
    public int getMostEndorsedPost() {
        /*
         * This method identifies and returns the post with the most number of
         * endorsements, a.k.a. the most popular post.
         *
         * @return the ID of the most popular post.
         */
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMostEndorsedAccount() {
        /*
         * This method identifies and returns the account with the most number of
         * endorsements, a.k.a. the most popular account.
         *
         * @return the ID of the most popular account.
         */
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void erasePlatform() {
        /*
         * Method empties this SocialMediaPlatform of its contents and resets all
         * internal counters.
         */

        //erase all accounts and posts
        Account.accounts.clear();
        Post.posts.clear();
        Comment.comments.clear();
        Endorsement.endorsements.clear();
    }

    @Override
    public void savePlatform(String filename) throws IOException {
        /*
         * Method saves this SocialMediaPlatform's contents into a serialised file, with
         * the filename given in the argument.
         *
         * @param filename location of the file to be saved
         * @throws IOException if there is a problem experienced when trying to save the
         *                     store contents to the file
         */

        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos); {
            oos.writeObject(Account.accounts);
            //todo oos.writeObject(Post.posts);
        }
    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        /*
         * Method should load and replace this SocialMediaPlatform's contents with the
         * serialised contents stored in the file given in the argument.
         * <p>
         * The state of this SocialMediaPlatform's must be be unchanged if any
         * exceptions are thrown.
         *
         * @param filename location of the file to be loaded
         * @throws IOException            if there is a problem experienced when trying
         *                                to load the store contents from the file
         * @throws ClassNotFoundException if required class files cannot be found when
         *                                loading
         */
        // TODO Auto-generated method stub
        //load the platform from the file created in savePlatform


    }

}
