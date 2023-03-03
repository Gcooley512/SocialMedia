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
	public static void main(String[] args) throws IllegalHandleException, InvalidHandleException, AccountIDNotRecognisedException, HandleNotRecognisedException, InvalidPostException {
		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new SocialMedia();

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		int id;
		int id2;
		String AccountDescription;
		String my_handle = "my_handle";
		//try {
			id = platform.createAccount("my_handle", "descriptiondfghd");
			System.out.println("Account created with id: " + id);

			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			AccountDescription = platform.showAccount(my_handle);
			System.out.println("Account description: \n" + AccountDescription);

			id2 =platform.createAccount("my_handle2");
			System.out.println("Account created with id: " + id2);

			//platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";
			int post1 = platform.createPost("my_handle2", "post1");
			System.out.println("Post created with id: " + post1);
			int post2 = platform.createPost("my_handle", "post2");
			System.out.println("Post created with id: " + post2);


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
