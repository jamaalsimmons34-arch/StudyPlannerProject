import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Course {

    private String courseName;
    private double grade;
    private int creditHours;
    private int recommendedStudyHours;

    // Constructor
    public Course(String courseName, double grade, int creditHours) {
        this.courseName = courseName;
        this.grade = grade;
        this.creditHours = creditHours;


        calculateStudyHours();
    }

    // Calculate study recommendation
    private void calculateStudyHours() {

        if (grade >= 90) {
            recommendedStudyHours = 3;
        }
        else if (grade >= 80) {
            recommendedStudyHours = 5;
        }
        else if (grade >= 70) {
            recommendedStudyHours = 8;
        }
        else {
            recommendedStudyHours = 12;
        }
    }

    // Convert percentage to GPA points
    public double getGpaPoints() {

        if (grade >= 90) {
            return 4.0;
        }
        else if (grade >= 80) {
            return 3.0;
        }
        else if (grade >= 70) {
            return 2.0;
        }
        else if (grade >= 60) {
            return 1.0;
        }
        else {
            return 0.0;
        }
    }

    // Determine letter grade
    public String getLetterGrade() {

        if (grade >= 90) {
            return "A";
        }
        else if (grade >= 80) {
            return "B";
        }
        else if (grade >= 70) {
            return "C";
        }
        else if (grade >= 60) {
            return "D";
        }
        else {
            return "F";
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() {
        return grade;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public int getRecommendedStudyHours() {
        return recommendedStudyHours;
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("      SMART STUDY PLANNER SYSTEM");
        System.out.println("======================================");

        // Ask for number of courses
        System.out.print("Enter number of courses: ");
        int courseCount = input.nextInt();
        input.nextLine();

        Course[] courses = new Course[courseCount];

        // Input course information
        for (int i = 0; i < courseCount; i++) {

            System.out.println("\nCourse #" + (i + 1));

            System.out.print("Enter course name: ");
            String name = input.nextLine();

            System.out.print("Enter current grade percentage: ");
            double grade = input.nextDouble();

            System.out.print("Enter credit hours: ");
            int credits = input.nextInt();
            input.nextLine();

            courses[i] = new Course(name, grade, credits);
        }

        // Display report
        System.out.println("\n======================================");
        System.out.println("          STUDY PLAN REPORT");
        System.out.println("======================================");

        double totalQualityPoints = 0;
        int totalCredits = 0;

        Course highestPriority = courses[0];

        for (Course course : courses) {

            System.out.println("\nCourse: " + course.getCourseName());
            System.out.println("Grade: " + course.getGrade() + "%");
            System.out.println("Letter Grade: " + course.getLetterGrade());
            System.out.println("Credit Hours: " + course.getCreditHours());
            System.out.println("Recommended Study Hours/Week: "
                    + course.getRecommendedStudyHours());

            totalQualityPoints +=
                    course.getGpaPoints() * course.getCreditHours();

            totalCredits += course.getCreditHours();

            // Determine highest priority course
            if (course.getGrade() < highestPriority.getGrade()) {
                highestPriority = course;
            }
        }

        // GPA calculation
        double semesterGPA = totalQualityPoints / totalCredits;

        // Final summary
        System.out.println("\n======================================");
        System.out.println("              SUMMARY");
        System.out.println("======================================");

        System.out.printf("Semester GPA: %.2f%n", semesterGPA);

        System.out.println("Highest Priority Course: "
                + highestPriority.getCourseName());

        System.out.println("Suggested Focus Hours: "
                + highestPriority.getRecommendedStudyHours()
                + " hours/week");

        // Save report to file
        try {

            FileWriter writer = new FileWriter("StudyPlanReport.txt");

            writer.write("SMART STUDY PLANNER REPORT\n");
            writer.write("===========================\n\n");

            for (Course course : courses) {

                writer.write("Course: " + course.getCourseName() + "\n");
                writer.write("Grade: " + course.getGrade() + "%\n");
                writer.write("Letter Grade: "
                        + course.getLetterGrade() + "\n");

                writer.write("Study Hours/Week: "
                        + course.getRecommendedStudyHours() + "\n\n");
            }

            writer.write(String.format("Semester GPA: %.2f%n",
                    semesterGPA));

            writer.write("Highest Priority Course: "
                    + highestPriority.getCourseName());

            writer.close();

            System.out.println("\nStudy plan saved to StudyPlanReport.txt");

        }
        catch (IOException e) {

            System.out.println("Error saving file.");
        }

        System.out.println("\nProgram Complete.");
    }
}