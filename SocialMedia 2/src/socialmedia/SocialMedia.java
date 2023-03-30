package socialmedia;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SocialMedia implements SocialMediaPlatform {
    /**
     The users, i.e., accounts registered in the system, will be able to post original messages
     and comments, which can also be endorsed by endorsement posts.
     Accounts have a unique numerical identifier, but also a unique string handle to be more easily identified throughout
     the system. They can have a description field to add personal information they want to share. Posts (original,
     comments, and endorsements) have a unique numerical identifier and contain a message with up to 100-characters.
     The post ID is a sequential number such that its ordering is a proxy for a post’s chronology. They are always associated
     with an author, i.e., the account who posted it. To allow the creation of meaningful conversation trees, posts must keep
     track of the list of endorsements and comments they received. Endorsements and comments are also categorized as
     posts, but with special features. For instance, comments always have to point to another post (original or comment).
     Endorsements automatically replicate the endorsed message and also refer to original or comment posts. Endorsements
     are not endorsed or commented. The system should provide basic analytics such as the most popular post and the
     most popular account.
     */

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

        //throwing exceptions if the handle is invalid in some way
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

        //checking if the handle already exists
        for (Account account : Account.accounts) {
             if (account.getHandle().equals(handle)) {
                throw new IllegalHandleException("Handle already exists");
            }
        }

        //creating the new account
        Account newAccount = new Account(handle);
        Account.accounts.add(newAccount);

        //returning the new account's ID
        return newAccount.getId();
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {

        //throwing exceptions if the handle is invalid in some way
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

        //creating the new account
        Account newAccount = new Account(handle, description);
        Account.accounts.add(newAccount);

        //returning the new account's ID
        return newAccount.getId();

    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {

        //search through all the accounts and find the one with the id
        //remove all the comments the account has made, replace the comments with a generic response
        //remove all the endorsements the post has made
        //replace the comments on the posts with a generic response
        //remove the post from the account
        //then remove the account

        //flag to check if the account exists
        boolean found = false;

        for (Account account: Account.accounts) {
            if (account.getId() == id) {
                //make sure the account exists so we can remove it and all its posts, comments and endorsements
                //delete the posts in the same way which the DeletePost method does

                for (Post post: Post.posts) {
                    if (post.getAccountId() == id) {

                        //set the post that the comments refer to to a generic empty post
                        for (Comment comment: post.comments) {
                            //replace any comments on this post with a generic response
                            comment.setPost(new Post("The original content was removed from the system and is no longer available.", null, PostType.NULL));
                            }

                        //remove the endorsements
                        for (Endorsement endorsement: post.endorsements) {
                            endorsement.getAccount().removePost(); //remove a count of 1 from the account which posted
                            post.removeEndorsement(endorsement); //remove the endorsement
                            }

                        //remove the post
                        post.getAccount().removePost(); //minus 1 from the number of posts on the account that posted it
                        Post.posts.remove(post);
                    }

                    //remove all the accounts comments
                    for (Comment comment: post.comments) {
                        if (comment.getAccountId() == id) {
                            //replace any comments on this post with a generic response
                            for (Comment comment2: comment.comments) {
                                comment2.setPost(new Post("The original content was removed from the system and is no longer available.", null, PostType.NULL));
                            }

                            for (Endorsement endorsement: comment.endorsements) {
                                endorsement.getAccount().removePost(); //remove a count of 1 from the account which posted
                                comment.removeEndorsement(endorsement); //remove the endorsement
                                }

                            //remove the comment
                            //we dont need to remove a count from the account which posted the comment as it is being deleted anyway
                            post.removeComment(comment);
                        }
                    }

                    for (Endorsement endorsement: post.endorsements) {
                        if (endorsement.getAccountId() == id) {
                            post.removeEndorsement(endorsement); //remove the endorsement
                        }
                    }
                }
                // removes the account
                Account.accounts.remove(account);
                found = true;
                break; // break now we have found the account and removed it
            }
        }

        //if the account was not found throw an exception
        if (!found) {
            throw new AccountIDNotRecognisedException("Account not found");
        }
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {

        // same as above but search with handle rather than id
        boolean found = false;
        for (Account account: Account.accounts) {
            if (Objects.equals(account.getHandle(), handle)) {
                //make sure the account exists so we can remove it and all its posts, comments and endorsements
                //delete the posts in the same way which the DeletePost method does

                for (Post post: Post.posts) {
                    if (Objects.equals(post.getHandle(), handle)) {

                        //set the post that the comments refer to to a generic empty post
                        for (Comment comment: post.comments) {
                            //replace any comments on this post with a generic response
                            comment.setPost(new Post("The original content was removed from the system and is no longer available.", null, PostType.NULL));
                        }

                        //remove the endorsements
                        for (Endorsement endorsement: post.endorsements) {
                            endorsement.getAccount().removePost(); //remove a count of 1 from the account which posted
                            post.removeEndorsement(endorsement); //remove the endorsement
                        }

                        //remove the post
                        post.getAccount().removePost(); //minus 1 from the number of posts on the account that posted it
                        Post.posts.remove(post);
                    }

                    //remove all the accounts comments
                    for (Comment comment: post.comments) {
                        if (Objects.equals(comment.getAccount().getHandle(), handle)) {
                            //replace any comments on this post with a generic response
                            for (Comment comment2: comment.comments) {
                                comment2.setPost(new Post("The original content was removed from the system and is no longer available.", null, PostType.NULL));
                            }

                            for (Endorsement endorsement: comment.endorsements) {
                                endorsement.getAccount().removePost(); //remove a count of 1 from the account which posted
                                comment.removeEndorsement(endorsement); //remove the endorsement
                            }

                            //remove the comment
                            //we dont need to remove a count from the account which posted the comment as it is being deleted anyway
                            post.removeComment(comment);
                        }
                    }

                    for (Endorsement endorsement: post.endorsements) {
                        if (Objects.equals(endorsement.getAccount().getHandle(), handle)) {
                            post.removeEndorsement(endorsement); //remove the endorsement
                        }
                    }
                }
                // removes the account
                Account.accounts.remove(account);
                found = true;
                break; // break now we have found the account and removed it
            }
        }

        //if the account was not found throw an exception
        if (!found) {
            throw new HandleNotRecognisedException("Handle not found");
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

        //search through all the accounts and find the one with the handle
        //then update the handle
        boolean found = false;

        //check if new handle is valid
        if (newHandle == null) {
            throw new InvalidHandleException("Invalid handle");
        }
        if (newHandle.isEmpty()){
            throw new InvalidHandleException("No handle given");
        }
        if(newHandle.length() > 30){
            throw new InvalidHandleException("Handle too long");
        }
        if(newHandle.contains(" ")){
            throw new InvalidHandleException("Handle contains spaces");
        }

        //check if the new handle already exists
        for (Account account : Account.accounts) {
            if (account.getHandle().equals(newHandle)) {
                throw new IllegalHandleException("Handle already exists");
            }
        }


        //update the handle
        //search through all the accounts and find the one with the current handle
        for (Account account: Account.accounts) {
            if (account.getHandle().equals(oldHandle)) {
                //we set the handle and then break from the loop
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

        //search through all the accounts and find the one with the handle
        //then create the string with all details of the account

        boolean found = false;
        //creating the string to return and a string builder to make it
        String accountDetails;
        StringBuilder sb = new StringBuilder();

        //search through all the posts and if it is by our account add the endorsement count to the total
        int endorseCount = 0;
        for (Post post: Post.posts) {
            if (post.getHandle().equals(handle)) {
                endorseCount += post.getEndorsementCount();
            }
        }

        //search through all the accounts and find the one with the handle
        for (Account account: Account.accounts) {
            if (account.getHandle().equals(handle)) {

                //once we have found the right account we can create the string
                sb.append("ID: [").append(account.getId()).append("] \n");
                sb.append("Handle: [").append(account.getHandle()).append("] \n");

                if (Objects.equals(account.getDescription(), "")) {
                    //if the description is empty we add a message saying there is no description
                    sb.append("Description: No Description \n");
                } else {
                    //otherwise, we add it to the string
                    sb.append("Description: [").append(account.getDescription()).append("] \n");
                }

                sb.append("Post count: [").append(account.getPostCount()).append("] \n"); //number of posts
                sb.append("Endorse count: [").append(endorseCount).append("] \n"); //number of endorsements the account has received
                found = true;
                break; //we can stop looking now we have found it
            }
        }
        if (!found) {
            throw new HandleNotRecognisedException("Cant find handle");
        }
        //assemble the string builder into a string and return it
        accountDetails = sb.toString();
        return accountDetails;
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

        int postID = 0;
        boolean found = false;

        //check if the message is valid
        if (message == null || message.isEmpty()) {
            throw new InvalidPostException("Message is empty");
        }
        if (message.length() > 100) {
            throw new InvalidPostException("Message is too long");
        }

        //search through all the accounts and find the one with the handle
        //if it does not exist throw an exception
        for (Account account: Account.accounts) {
            if (account.getHandle().equals(handle)) {
                Post post = new Post(message, account, PostType.POST); //create a new post
                Post.posts.add(post); //add the post to the list of posts

                account.addPost(); //add one to the number of posts for the account
                postID = post.getID(); //get the id of the new post

                found = true;
                break; //we can stop looking now we have found it
            }
        }

        if (!found) {
            throw new HandleNotRecognisedException("Cant find account");
        }

        //return the id of the new post
        return postID;
    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException{

        boolean found = false;
        int postID = 0;
        Account endorsingAccount = null;

        //search through all the accounts and find the one with the handle then save it so that we can associate it with the new post
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
        //if the post we are trying to endorse is an endorsement post then throw an exception
        found = false;
        for (Post post: Post.posts) {
            if (post.getID() == id) {
                if (post.getPostType() == PostType.ENDORSEMENT) {
                    throw new NotActionablePostException("Cant endorse an endorsement");
                }
                Endorsement newEndorsement = new Endorsement(
                        "EP@" +
                        post.getHandle() +
                        ": " +
                        post.getMessage(),
                        endorsingAccount,
                        post); //create a new post

                endorsingAccount.addPost(); // add 1 to the count of posts for the account
                postID = newEndorsement.getID(); //get the id of the new endorsement post
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

        boolean found = false;
        int postID = 0;
        Account commentingAccount = null;

        //check the comment message is valid
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
            if (post.getID() == id) {
                if (post.getPostType() == PostType.ENDORSEMENT) {
                    throw new NotActionablePostException("Cant comment an endorsement");
                }
                //make a new comment on this post
                Comment newComment = new Comment(message, commentingAccount, post);

                commentingAccount.addPost(); //add 1 to the number of posts
                postID = newComment.getID(); //get the id of the new post
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

        //search through all the posts and find the one with the id
        boolean found = false;
        for (Post post: Post.posts) {
            if (post.getID() == id) {
                //remove all the endorsements
                for (Endorsement endorsement: post.endorsements) {
                    endorsement.getAccount().removePost(); //remove a count of 1 from the account which posted
                }
                post.endorsements.clear(); //remove all the endorsements from the list of endorsements
                //change the post the comments refer to to a generic empty post
                for (Comment comment: post.comments) {
                    comment.setPost(new Post("The original content was removed from the system and is no longer available.", null, PostType.NULL));
                }
                for (Endorsement endorsement: post.endorsements) {
                    //remove a count of 1 from the account which posted and then remove the endorsement
                    endorsement.getAccount().removePost();
                    post.endorsements.remove(endorsement);
                }
                Post.posts.remove(post); //remove the post

                post.getAccount().removePost(); //minus 1 from the number of posts on the account that posted it

                found = true;
                break; //we can stop looking now know the post exists
                }
        }

        if (!found) {
            throw new PostIDNotRecognisedException("Cant find post");
        }
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {

        //create a string builder to build the string and initialise an empty string
        StringBuilder sb = new StringBuilder();
        String postDetails;


        boolean found = false;

        for (Post post: Post.posts) {
            if (post.getID() == id) {
                sb.append("ID: [").append(post.getID()).append("] \n");
                sb.append("Account: [").append(post.getAccount().getHandle()).append("] \n");
                sb.append("No. endorsements: [").append(post.getEndorsementCount()).append("] | No. comments: [").append(post.getCommentCount()).append("] \n");
                sb.append(post.getMessage());
                found = true;
                break; //we can stop looking now we have finished building the string
            }
        }
        if (!found) {
            throw new PostIDNotRecognisedException("Cant find post");
        }
        postDetails = sb.toString();
        return postDetails;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id)
            throws PostIDNotRecognisedException, NotActionablePostException {

        StringBuilder sb = new StringBuilder(); //create the stringbuilder
        boolean found = false;

        for (Post currentPost: Post.posts) { //loop through all the posts to search for the one with our id
            if (currentPost.getID() == id) {
                found = true; //we found the post so we no longer need to throw the exception

                if (currentPost.getPostType() == PostType.ENDORSEMENT) {
                    //if the post is an endorsement then we cant show the children
                    throw new NotActionablePostException("Endorsement posts do not have children");
                }

                //start calling the childrenDetails method
                sb = childrenDetails(currentPost, 0, sb);
            }
        }
        if (!found) {
            throw new PostIDNotRecognisedException("Cant find post");
        }

        //for all the comments on the post call showPostChildrenDetails recursively
        //return the string builder
        return sb;

    }

    public StringBuilder childrenDetails(Post currentPost, int indentLevel, StringBuilder sb) {

        int postID = currentPost.getID();
        if (indentLevel == 0) {
            //if the post is a top level post then we dont need to add the indent
            sb.append("ID: ").append(postID).append("\n");
        } else {
            //otherwise we indent it
            sb.append("    ".repeat(indentLevel)).append("| \n");
            sb.append("    ".repeat(indentLevel)).append("| > ID: ").append(postID).append("\n");
        }

        //add the account handle, number of endorsements, number of comments and the message
        sb.append("    ".repeat(indentLevel)).append("Account: ").append(currentPost.getAccount().getHandle()).append("\n");
        sb.append("    ".repeat(indentLevel)).append("No. endorsements: ").append(currentPost.getEndorsementCount()).append(" | No. comments: ").append(currentPost.getCommentCount()).append("\n");
        sb.append("    ".repeat(indentLevel)).append(currentPost.getMessage()).append("\n");

        //if the post has comments then we call the method recursively on each of them
        if (currentPost.getCommentCount() > 0) {
            for (Post comment : currentPost.comments) {
                //for each comment we call the method again with the indent level increased by 1
                childrenDetails(comment, indentLevel + 1, sb);
            }
        }

        //return the string builder
        return sb;
    }

    @Override
    public int getNumberOfAccounts() {

        int count = 0;
        for (Account ignored : Account.accounts) {
            count ++;
        }
        return count;
    }

    @Override
    public int getTotalOriginalPosts() {

        int total = 0;

        for (Post post: Post.posts) {
            //for each post we check if it has a PostType of POST and if it does we add 1 to the total
            if (post.getPostType() == PostType.POST) {
                total++;
            }
        }
        return total;
    }

    @Override
    public int getTotalEndorsmentPosts() {

        int total = 0;

        for (Post post: Post.posts) {
            //for each post we check if it has a PostType of ENDORSEMENT and if it does we add 1 to the total
            if (post.getPostType() == PostType.ENDORSEMENT) {
                total++;
            }
        }
        return total;
    }

    @Override
    public int getTotalCommentPosts() {

        int total = 0;

        for (Post post: Post.posts) {
            //for each post we check if it has a PostType of COMMENT and if it does we add 1 to the total
            if (post.getPostType() == PostType.COMMENT) {
                total++;
            }
        }
        return total;
    }

    @Override
    public int getMostEndorsedPost() {

        int currentMax = 0;
        int currentMaxID = 0;

        for (Post post: Post.posts) {
            //loop through each post and check if the endorsement count is greater than the current max
            if (post.getEndorsementCount() > currentMax) {
                //if it is then set the current max to the endorsement count and set the current max id to the post id
                currentMax = post.getEndorsementCount();
                currentMaxID = post.getID();

            }
        }
        return currentMaxID;
    }

    @Override
    public int getMostEndorsedAccount() {

        //search for the account which has recived the most endorsements by looping through each post and checking the endorsements

        //loop through each account
        //loop through each post by the account
        //check the endorsements of the post and add them to the total
        //if the total is greater than the current max, set the current max to the total and set the current max id to the account id
        //return the current max id

        int currentMax = 0;
        int currentMaxID = 0;
        int total = 0;

        for (Account account: Account.accounts) {
            for (Post post: Post.posts) {
                if (post.getPostType() != PostType.ENDORSEMENT && post.getAccountId() == account.getId()) {
                    total += post.getEndorsementCount();
                }
            }
            if (total > currentMax) {
                currentMax = total;
                currentMaxID = account.getId();
            }
        }
        return currentMaxID;
    }

    @Override
    public void erasePlatform() {

        Account.resetPlatform();

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

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));

            //writing the account list object to the byte stream
            try {
                //only need to serialize post and account objects since endorsement and comments extend post
                //using Post.posts and Account.account ArrayList of objects to serialize all the included objects
                oos.writeObject(Post.posts);
                oos.writeObject(Account.accounts);
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Could not serialize objects correctly");
            }

            //closes the object output stream
            oos.close();

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

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));

        try {
            List<Post> posts = (List<Post>) ois.readObject();
            List<Account> accounts = (List<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Could not deserialize objects correctly");
        }

        //closes the object output stream
        ois.close();
    }
}
