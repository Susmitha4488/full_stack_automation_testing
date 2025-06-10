package javaprograms;

public class JumpState {

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) 
		{
		    int multipleOfFive = 5 * (i / 5); // can be if(i % 5 == 0)
		    if (i == multipleOfFive) 
		    {
		    	continue; // Skip numbers matching multiples of 5
		    }
		        System.out.print(i + " ");
		 }
	}

}
