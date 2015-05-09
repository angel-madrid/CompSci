package MagpieActivityStarterCode.activity4;
/**
 * A program to carry on conversations with a human user.
 * This version: 
 * <ul><li>
 *    Uses advanced search for keywords 
 * </li></ul> 
 *    
 * @author Laurie White
 * @version April 2012
 */
public class Magpie4
{
	/**
	 * Get a default greeting
	 * 
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk. What would you like to talk about?";
	}

	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}
		else if(findKeyword(statement,"dog")>=0
				|| findKeyword(statement, "cat")>=0){		//Pet
			response = "Tell me more about your pet.";
		}
		else if(findKeyword(statement, "eno") >= 0){
	         response = "He sounds like a good teacher.";   //Teacher
		}     
		else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0	//Family
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0)
		{
			response = "Tell me more about your family.";
		}
		else if(findKeyword(statement,"do you like")>=0
				||findKeyword(statement,"dont you like")>=0){
			response = "Yes of course, who doesn't?";
		}
		else if(findKeyword(statement,"engineering") >= 0
				||findKeyword(statement,"robotics") >= 0
				||findKeyword(statement,"robot") >= 0
				||findKeyword(statement,"math") >= 0          //1 Keyword
	            ||findKeyword(statement,"science") >= 0
	            ||findKeyword(statement,"tech") >= 0
	            ||findKeyword(statement,"stem") >= 0){
	         response = "So you are interested in STEM?";
	    }
		else if(findKeyword(statement,"angel")>= 0       //2nd Keyword
	            ||findKeyword(statement,"name")>= 0){
	         response = "My name is Angel";
	    }
		else if(findKeyword(statement,("soccer"))>=0
	    		||findKeyword(statement,"football")>=0
	    		||findKeyword(statement,"volleyball")>=0	//3rd keyword
	    		||findKeyword(statement,"baseball")>=0
	    		||findKeyword(statement,"sports")>=0
	    		||findKeyword(statement,"basketball")>=0){
	    	 response = "So you're into sports then?"; 	
	    }
		else if(findKeyword(statement,"i want you")>=0
				||findKeyword(statement,"i like you")>=0
				||findKeyword(statement,"i love you")>=0){
			response = "Why do you like me?";
		}
		else if(findKeyword(statement,"i like")>=0
				||findKeyword(statement, "i love")>=0){
			response = "Would you really be happy with "+ statement.substring(statement.indexOf("i ")+7) + "?" ;
		}
		else if(findKeyword(statement,"i want")>=0){
			response = "Why do you want "+ statement.substring(statement.indexOf("want")) + "?";
		}
		else if(findKeyword(statement,"awesome")>=0){
			response = "Awesome you say?";
		}
		else if(findKeyword(statement,"of course")>=0
				||findKeyword(statement,"yes")>=0
				||findKeyword(statement,"yea")>=0){
			response = "Of course, of course";
		}
		else if(findKeyword(statement,"weather")>=0){
			response = "That weather man really has his work cut out for him...";
		}
		else if (findKeyword(statement, "sunny") >= 0
				||findKeyword(statement, "will be hot") >= 0)
		{
			response = "The sun is so nice";
		}
		else if (findKeyword(statement, "rain") >= 0
				||findKeyword(statement, "rainy")>= 0)
		{
			response = "Ugh rain? Why the rain?";
		}
		else if (findKeyword(statement, "clouds") >= 0)
		{
			response = "Clouds? Well is it gonna rain? or maybe it will be sunny?";
		}
		else if (findKeyword(statement,"sleep")>=0){
			response = "Well, as a robot I don't need sleep.";
		}
		else if (findKeyword(statement, "you") >= 0
				||findKeyword(statement, "your") >= 0)
		{
			response = "Well I am a robot...";
		}
		else if(findKeyword(statement,"what?")>=0
				||statement.indexOf("?")>=0){
			response = "I'm not really sure...";
		}
		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
		}
		else
		{
			response = getRandomResponse();
		}
		return response;
	}

	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no"). The search
	 * begins at the beginning of the string.
	 * 
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword(statement, goal, 0);
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * 
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}

		return response;
	}

}
