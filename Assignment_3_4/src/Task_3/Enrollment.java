package Task_3;

import java.io.Serializable;

public class Enrollment implements Serializable {
    private Student student;
    private Course course;
    private String enrollmentDate;

    public Enrollment(Student student, Course course, String enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    // Setters
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return "Enrollment{student=" + student + ", course=" + course +
                ", enrollmentDate='" + enrollmentDate + "'}";
    }
}