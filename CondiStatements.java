package javaprograms;

public class CondiStatements {

	public static void main(String[] args) {
		// TODO calculates the grade depends on the average
        int mathMarks = 95;
        int scienceMarks = 76;
        int englishMarks = 82;

        double average = (mathMarks + scienceMarks + englishMarks) / 3.0;

        String grade;
        String message;

        if (average >= 90) {
            grade = "A+";
            message = "Excellent performance!";
        } else if (average >= 75) {
            grade = "A";
            message = "Excellent performance!";
        } else if (average >= 60) {
            grade = "B";
            message = "Keep improving.";
        } else if (average >= 40) {
            grade = "C";
            message = "Keep improving.";
        } else {
            grade = "Fail";
            message = "Please work harder next time.";
        }

        // Checking if any subject mark is below 35
        if (mathMarks < 35 || scienceMarks < 35 || englishMarks < 35) {
            message += " Failed due to low score in at least one subject.";
        }

        // Printing results
        System.out.println("Math Marks: " + mathMarks);
        System.out.println("Science Marks: " + scienceMarks);
        System.out.println("English Marks: " + englishMarks);
        System.out.println("\nAverage Marks: " + average);
        System.out.println("Grade: " + grade);
        System.out.println("Message: " + message);
    }
}
