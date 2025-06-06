package javaprograms;

public class ArithOperator {

	public static void main(String[] args) {
		// TODO Calculate the total marks using arithmetic operators
		int Math = 95;
        int Science = 80;
        int English = 84;

        int sum = Math + Science + English;

        // 3. Compute average with precision
        double average = sum / 3.0d;

        // 4. Print results
        System.out.println("Total marks: " + sum);
        System.out.println("Average (double): " + average);

        // 5. Integer average (truncated)
        int avgInt =sum / 3;
        System.out.println("Average (int): " + avgInt);

	}

}

