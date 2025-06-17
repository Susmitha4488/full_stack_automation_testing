package javaprograms;

public class StringVowels {

	public static void main(String[] args) {
		// TODO Counting the number of vowels in a sentence
		String senten = "Hello how are you doing today?";
        int count = 0;

        senten = senten.toLowerCase();

        for (int i = 0; i < senten.length(); i++) {
            char ch = senten.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }

        System.out.println("Total number of vowels: " + count);
	}

}
