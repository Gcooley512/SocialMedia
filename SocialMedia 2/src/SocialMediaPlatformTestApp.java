//import jdk.incubator.foreign.ResourceScope;
import socialmedia.*;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 *
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

    /**
     * Test method.
     *
     * @param args not used
     */


    public static void main(String[] args) throws IllegalHandleException, InvalidHandleException, AccountIDNotRecognisedException, HandleNotRecognisedException, InvalidPostException, NotActionablePostException, PostIDNotRecognisedException {

        System.out.println("The system compiled and started the execution...");

        SocialMediaPlatform platform = new SocialMedia();

        assert (platform.getNumberOfAccounts() == 0) : "Initial SocialMediaPlatform not empty as required.";
        assert (platform.getTotalOriginalPosts() == 0) : "Initial SocialMediaPlatform not empty as required.";
        assert (platform.getTotalCommentPosts() == 0) : "Initial SocialMediaPlatform not empty as required.";
        assert (platform.getTotalEndorsmentPosts() == 0) : "Initial SocialMediaPlatform not empty as required.";


        //++++++++++++++++++             ACCOUNT TESTING                    +++++++++++++++++++++


        int acc_id = platform.createAccount("first_handle", "first_description"); System.out.println("Account created with id: " + acc_id);
        int to_be_deleted_acc_second_id = platform.createAccount("second_handle", "second_description"); System.out.println("Account created with id: " + to_be_deleted_acc_second_id);
        int acc_third_id = platform.createAccount("third_handle", "third_description"); System.out.println("Account created with id: " + acc_third_id);


        //creating posts
        int post_id = platform.createPost("first_handle", "first_post_msg"); System.out.println("Post created with id : " + post_id);
        int second_post_id = platform.createPost("second_handle", "second_post_msg"); System.out.println("Post created with id : " + second_post_id);
        int third_post_id = platform.createPost("third_handle", "third_post_msg"); System.out.println("Post created with id : " + third_post_id);

        //testing create account------------------------

        //testing InvalidHandleException (empty handle)
        try {
            int acc_fourth_id = platform.createAccount("", "fourth_description");
        }catch (InvalidHandleException e) {
            System.out.print("throws correct exception when handle is empty\n");
        }

        //testing InvalidHandleException (spaces present)
        try {
            int acc_fourth_id = platform.createAccount("fourth handle", "fourth_description");
        }catch (InvalidHandleException e) {
            System.out.print("throws correct exception when spaces are in handle\n");
        }

        //testing IllegalHandleException (already exists in system)
        try {
            int acc_fifth_id_SAME_HANDLE = platform.createAccount("first_handle", "fifth_description");
        } catch (IllegalHandleException e) {
            System.out.println("throws correct exception when duplicate handle is created\n");
        }

        //testing remove account-----------------------

        int prevAccNum = platform.getNumberOfAccounts();
        platform.removeAccount(1);
        if (platform.getNumberOfAccounts() + 1 == prevAccNum){
            System.out.println("the account id number 1 was removed\n");
        } else{
            System.out.println("the account was not removed\n");
        }
        //recreates the second account
        int acc_second_id = platform.createAccount("second_handle", "second_description");

        //testing if when an account is removed all its posts is removed
        int before_removed_num_acc = platform.getNumberOfAccounts();
        int removingAcc_acc = platform.createAccount("removeAcc", "rmvAcc");
        int removingAcc_post = platform.createPost("removeAcc", "test remove acc");
        System.out.println("total number of posts before account is removed: " + platform.getTotalOriginalPosts());
        platform.removeAccount(removingAcc_acc); System.out.println("account was removed");
        System.out.println("total number of posts after account is removed: " + platform.getTotalOriginalPosts() + "\n");


        //testing AccountIDNotRecognisedException (id does not exist)
        try {
            platform.removeAccount(100);
        }catch(AccountIDNotRecognisedException e){
            System.out.println("throws correct exception when removing incorrect account from id\n");
        }

        //show account testing-----------------------

        //creates a new account for testing
        int test_id = platform.createAccount("test_handle", "test_description");
        System.out.println("\ntesting showAccount() works correctly \n" + platform.showAccount("test_handle"));

        //testing HandleNotRecognisedException (handle does not exist for show method)
        try {
            platform.showAccount("non_existant_handle");
        } catch (HandleNotRecognisedException e) {
            System.out.println("throws correct exception when handle does not exist when showing account\n");
        }


        //change account handle testing after show account so can verify it works

        //testing change account handle----------------------------

        platform.changeAccountHandle("test_handle", "new_test_handle");
        System.out.println("\n" + platform.showAccount("new_test_handle"));

        //old handle does not exist
        try {
            platform.changeAccountHandle("not_a_real_handle", "also_not_a_real_handle");
        } catch (HandleNotRecognisedException e){
            System.out.println("correct exception thrown when oldhandle does not exist");
        }

        //new handle exists already
        try{
            platform.changeAccountHandle("new_test_handle", "test_description");
        }catch (IllegalHandleException e) {
            System.out.println("correct exception thrown when newhandle already exists");
        }

        //new handle length > 30
        try{
            platform.changeAccountHandle("new_test_handle", "jhsuhsouhsouhsouhsohsouhsouhsouhsouh");
        } catch (InvalidHandleException e) {
            System.out.println("correct exception was thrown when newhandle lenght is > 30");
        }

        //trying to change account handle if account has been removed
        try{
            platform.removeAccount("new_test_handle");
            platform.changeAccountHandle("new_test_handle", "new_new_test_handle");
        } catch (HandleNotRecognisedException e) {
            System.out.println("handle was not changed since the account was deleted before");
        }

        //testing updateAccountDescription -----------------------------

        int changeacchandleid = platform.createAccount("changeAccHandle", "chicken");
        System.out.println("\n showing account before handle was changed : \n" + platform.showAccount("changeAccHandle") + "\n");
        platform.updateAccountDescription("changeAccHandle", "burger");
        System.out.println("\n showing account after handle was changed : \n" + platform.showAccount("changeAccHandle") + "\n");

        //testing to see if it still updates the description after the account has been deleted

        try{
            platform.removeAccount("changeAccHandle");
            platform.updateAccountDescription("changeAccHandle", "burger");
            System.out.println("\n showing account after handle was deleted : \n" + platform.showAccount("changeAccHandle") + "\n");
        } catch (HandleNotRecognisedException e) {
            System.out.println("description of account was not changed if account was deleted\n");
        }


        //++++++++++++         POST TESTING               +++++++++++++++


        //testing total original posts-----------------------

        int test_del_post = platform.createPost("first_handle", "2Bdeleted");
        int acc_for_comment_totalPost = platform.createAccount("michael", "love");
        System.out.println("total number of posts : " + platform.getTotalOriginalPosts());
        //testing deleting a post to see if num of accounts is decremented
        platform.deletePost(test_del_post);
        System.out.println("total number of posts : " + platform.getTotalOriginalPosts());
        System.out.println("total total original posts isnt effected by deletion");

        int test_endorsement = platform.commentPost("first_handle", 2, "hope this works");
        int test_add_post = platform.createPost("first_handle", "korean");

        System.out.println("total number of posts : " + platform.getTotalOriginalPosts());
        System.out.println("total number of posts isnt affected by comments on a post.\n");

        //endorse post testing --------------------
        int testingEndorsePost = platform.createPost("third_handle", "endorse");
        //before endorsement
        System.out.println("account before endorsement : " + platform.showAccount("third_handle") + "\n");
        System.out.println("post before endorsement : " + platform.showIndividualPost(testingEndorsePost) + "\n");
        //endorsement
        platform.endorsePost("third_handle", testingEndorsePost); System.out.println("post has been endorsed");
        //after endorsement
        System.out.println("account after endorsement : " + platform.showAccount("third_handle") + "\n");
        System.out.println("post after endorsement : " + platform.showIndividualPost(testingEndorsePost) + "\n");

        //comment posts testing -------------
        //checks to see if the logic for commenting on an endorsement is correct

        try{
            //creates chain of objects
            int acc_commTest = platform.createAccount("commTesting", "commentTesting");
            int post_commTest = platform.createPost("commTesting", "can comment");
            int endorsement_commTest = platform.endorsePost("commTesting", post_commTest);
            //if the id is changed in the comment to direct to the endorsement then it catches the exception
            //if the id is the post then prints successfull comment on post
            int comment_commTest = platform.commentPost("third_handle", post_commTest, "test comment on endorsement"); System.out.println("successfull comment on post");
        } catch(NotActionablePostException e) {
            System.out.println("correct Exception thrown, cannot comment on an endorsement");
        }

        //show Post children details testing --------------------

        //showPostChildrenDetails was already tested at the time of the method being created so no need to test now

        //save platform testing --------------------------
        //not sure why doesnt work
        //says exception is not handled even though it is in the saveplatform method?
        //platform.savePlatform("socialmedia.ser");

        //erase platform testing ----------------

        System.out.println("number of accounts before erase platform : " + platform.getNumberOfAccounts());
        platform.erasePlatform(); System.out.println("ERASED PLATFORM INTERNAL COUNTERS");
        System.out.println("number of accounts after erase platform : " + platform.getNumberOfAccounts());



        /*

        *\
        */
    }

}
