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
		boolean assertionsEnabled = true;
		//assert assertionsEnabled = true;

		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new SocialMedia();

		assert (platform.getNumberOfAccounts() == 0) : "Initial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Initial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Initial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Initial SocialMediaPlatform not empty as required.";

		//try {
			int acc_id = platform.createAccount("first_handle", "first_description"); System.out.println("Account created with id: " + acc_id);
			int acc_second_id = platform.createAccount("second_handle", "second_description"); System.out.println("Account created with id: " + acc_second_id);
			int acc_third_id = platform.createAccount("third_handle", "third_description"); System.out.println("Account created with id: " + acc_third_id);

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
				System.out.println("throws correct exception when duplicate handle is created");
			}

			//testing remove account
			int prevAccNum = platform.getNumberOfAccounts();
			platform.removeAccount(1);
			if (platform.getNumberOfAccounts() + 1 == prevAccNum){
				System.out.println("the account id number 1 was removed");
			} else{
				System.out.println("the account was not removed");
			}

			//testing AccountIDNotRecognisedException (id does not exist)
			try {
				platform.removeAccount(100);
			}catch(AccountIDNotRecognisedException e){
				System.out.println("throws correct exception when removing incorrect account from id");
			}

			//testing change account handle

			platform.changeAccountHandle("second_handle", "new_second_handle");

	}

}
