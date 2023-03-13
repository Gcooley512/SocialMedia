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

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		//try {
			int id = platform.createAccount("my_handle", "descriptiondfghd"); System.out.println("Account created with id: " + id);

			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			String AccountDescription = platform.showAccount("my_handle"); System.out.println("Account description: \n" + AccountDescription);

			int id2 =platform.createAccount("my_handle2"); System.out.println("Account created with id: " + id2);

			//platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";
			int post1 = platform.createPost("my_handle2", "post1"); System.out.println("Post created with id: " + post1);
			int post2 = platform.createPost("my_handle", "post2"); System.out.println("Post created with id: " + post2);

			//user 1 comments on user 2's post
			int comment1 = platform.commentPost("my_handle", post1, "comment1"); System.out.println("Comment created with id: " + comment1);
			//user 2 comments on user 1's comment
			int comment2 = platform.commentPost("my_handle2", comment1, "comment2"); System.out.println("Comment created with id: " + comment2);
			//show individual post 1
			String post1String = platform.showIndividualPost(post1); System.out.println("Post 1: \n" + post1String);
			//endorse post 1
			int endorse1 = platform.endorsePost("my_handle", post1); System.out.println("Endorse created with id: " + endorse1);

			//show individual post 1
		    post1String = platform.showIndividualPost(post1); System.out.println("Post 1: \n" + post1String);

			//get number of posts
			int totalPosts = platform.getTotalOriginalPosts(); System.out.println("Total posts: " + totalPosts);
			//get number of comments
			int totalComments = platform.getTotalCommentPosts(); System.out.println("Total comments: " + totalComments);
			//get number of endorsements
			int totalEndorsements = platform.getTotalEndorsmentPosts(); System.out.println("Total endorsements: " + totalEndorsements);
			//get most popular post
			int mostPopular = platform.getMostEndorsedPost(); System.out.println("Most popular post: " + mostPopular);
			//get most popular account
			int mostPopularAccount = platform.getMostEndorsedAccount(); System.out.println("Most popular account: " + mostPopularAccount);



		/*} catch (IllegalHandleException e) {
			System.out.println("IllegalHandleException thrown correctly");
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			System.out.println("InvalidHandleException thrown correctly");
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			System.out.println("AccountIDNotRecognisedException thrown correctly");
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		}*/

	}

}
