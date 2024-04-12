package ExercisesPB04;

import java.util.List;

public class Student {
    private String firstName;
    private String secondName;
    private double grade;

    public Student(String firstName, String secondName, double grade){
        this.firstName = firstName;
        this.secondName = secondName;
        this.grade = grade;

    }

    public double getGrade() {
        return grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString(){
        return String.format("%s %s: %.2f", this.firstName, this.secondName,this.grade);
    }
}
