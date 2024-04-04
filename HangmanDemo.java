/*
This program is a simple console-based
hangman game based on the String class
*/
import java.util.Random;
import java.util.Scanner;

// Define a Hangman class to create instances of game
class Hangman {
	int misses = 0; // Create variable misses and set equal to zero
	int maxMisses = 4; // Declare variable maxMisses

	// Create a method for welcome message
	void printWelcome() {
		System.out.println();
		System.out.println("Welcome to Hangman! A man's life it at stake.");
		System.out.println("Can you save them?");
		System.out.println();
	}

	// Create a method display for game graphics

	// This game is designed based on maxMisses = 4
	void display(String blank) {
		// If misses are zero, print welcome message
		if (this.misses == 0) {
			System.out.println("H A N G M A N");
			System.out.println(" +---+");
			System.out.println("    |");
			System.out.println("    |");
			System.out.println("    |");
			System.out.println("======");
			this.showAttemptsLeft();
			System.out.println("Missed letters: ");
			// This later prints out the underscores with spaces between
			for (char letter : blank.toCharArray()) {
            	System.out.print(letter + " ");
        	}
        	System.out.println();
			System.out.print("Guess a letter: ");
		} 
		else if (this.misses == 1) {
			System.out.println("H A N G M A N");
			System.out.println(" +---+");
			System.out.println(" O  |");
			System.out.println("    |");
			System.out.println("    |");
			System.out.println("  ===");
			this.showAttemptsLeft();
			System.out.println("Missed letters: ");
			for (char letter : blank.toCharArray()) {
            	System.out.print(letter + " ");
        	}
        	System.out.println();
			System.out.print("Guess a letter: ");
		}
		else if (this.misses == 2) {
			System.out.println("H A N G M A N");
			System.out.println(" +---+");
			System.out.println(" O  |");
			System.out.println(" |  |");
			System.out.println("    |");
			System.out.println("  ===");
			this.showAttemptsLeft();
			System.out.println("Missed letters: ");
			for (char letter : blank.toCharArray()) {
            	System.out.print(letter + " ");
        	}
        	System.out.println();
			System.out.print("Guess a letter: ");
		}
		else if (this.misses == 3) {
			System.out.println("H A N G M A N");
			System.out.println(" +---+");
			System.out.println(" O  |");
			System.out.println("/|\\ |");
			System.out.println("    |");
			System.out.println("  ====");
			this.showAttemptsLeft();
			System.out.println("Missed letters: ");
			for (char letter : blank.toCharArray()) {
            	System.out.print(letter + " ");
        	}
        	System.out.println();
			System.out.println();
			System.out.print("Guess a letter: ");
		}

		// If misses equal 4, then print losing message
		else if (this.misses == 4) { 
			System.out.println("H A N G M A N");
			System.out.println(" +---+");
			System.out.println(" O  |");
			System.out.println("/|\\ |");
			System.out.println("/ \\ |");
			System.out.println("  =======");
			this.showAttemptsLeft();
			System.out.println("Missed letters: ");
			for (char letter : blank.toCharArray()) {
            	System.out.print(letter + " ");
        	}
        	System.out.println();
			System.out.println();
			System.out.print("Sorry, You lose! The answer was: ");
		}
		
		
	}
	// Create a method to show attempts left. It's used in the display() method
	void showAttemptsLeft() {
		if (this.misses <= this.maxMisses) { //
					switch (this.maxMisses - this.misses) {
						case 1:
							System.out.println("You have 1 attempt left.");
							break;
						case 0:
							System.out.println("You have no attempts left.");
							break;
						default:
							System.out.println("You have " + (this.maxMisses - this.misses) + " attempts left.");
					}
				}
		System.out.println();
	}

}

// Create the Demo class as the running class
class HangmanDemo {
	public static void main(String[] args) {

		// Instanitate a new object
		Hangman hangman = new Hangman();

		// Create a list of words to randomly choose from
		String[] wordList = {"absurd", "matrix", "sticky", "sandman" , "cinema",
							 "rainy", "master", "smart" , "noble", "primary",
							 "logical", "zebra", "turbine", "rabbit", "kettle",
							 "panther", "sweden", "miracle", "batman", "tricky"};
		Random random = new Random();
		// Choose a random number from 0 - 19
		int wordNum = random.nextInt(20);

		// Randomly select number
		String answer = wordList[wordNum];

		// Create an array to store every guess
		char[] guessList = new char[hangman.maxMisses + answer.length()]; 

		// Define a string to display the progress
		String blank = "";


		// Let's modfiy our blank string using for-loop
		for(int i = 0; i < answer.length(); i++) {
			blank = blank + "_";
		}
	

		// Create a new object to input data
		Scanner scan = new Scanner(System.in);

		// Create a new bool to identify repeated guesses
		boolean alreadyGuessed;

		// Create to ints to keep track of tries used and letter occurences
		int triesUsed = 0;
		int letterOccurence = 0;

		// Print welcome message
		hangman.printWelcome();
			while (hangman.misses < 4) {

				// Every time letterOccurence is set to zero
				letterOccurence = 0;

				alreadyGuessed = false;

				// Display graphics
				hangman.display(blank);

				char guess = scan.next().charAt(0); // Get user input

				// Warn user if they repeat the same guess
				for (int j = 0; j < guessList.length; j++) {
					if (guessList[j] == guess) {
						System.out.println("You've already guessed this letter. Be more careful!");
						alreadyGuessed = true;		
						break;
					} 
				}	
				// Append guess to guess list
				guessList[triesUsed] = guess;

				triesUsed ++; // Increment number of tries

				// Reconstruct blank accordingly
				for(int i = 0; i < answer.length(); i++) {

					if((answer.charAt(i) == guess) && (alreadyGuessed == false)) {
						blank = blank.substring(0, i) + guess + blank.substring(i + 1);
						letterOccurence += 1;

					}
				}

				// If no occurence was found, increment number of misses
				if (letterOccurence == 0) {hangman.misses++;} 
				
				System.out.println();

				System.out.println("====================");	

				// If maxMisses reached, display losing message and the answer
				if (hangman.misses == 4) {
					hangman.display(blank);
					System.out.print(answer);
				}

				// If answer is fully guessed, print winning message
				if (blank.equals(answer) == true) {
					System.out.println("Congratulations, You win!");
					break;
				}

			}

	}
}