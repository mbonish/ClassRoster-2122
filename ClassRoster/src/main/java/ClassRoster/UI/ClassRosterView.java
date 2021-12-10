package ClassRoster.UI;

import ClassRoster.dto.Student;

import java.util.List;

public class ClassRosterView {
    public ClassRosterView(UserIO io) {
        this.io = io;
    }
    private UserIO io;

    public Student getNewStudentInfo(){
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter first name");
        String lastName = io.readString("Please enter last name");
        String cohort = io.readString("Please enter cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }

    public void displayCreateStudentBanner(){
        io.print("===Create Student ===");
    }

    public void displayCreateSuccessBanner(){
        io.readString(" Student successfully created. Please hit enter to continue.");
    }

    public void displayStudentList(List<Student> studentList){
        for (Student currentStudent : studentList){
            io.print(currentStudent.getStudentId() + ": "
                    + currentStudent.getFirstName() + ": "
                    + currentStudent.getLastName() + ": ");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner(){
        io.print("=== Display All Students ===");
    }

    public void displayDisplayStudentBanner(){
        io.print("===Display Student ===");
    }

    public String getStudentIdChoice(){
        return io.readString("Please enter the Student ID.");
    }

    public void displayStudent(Student student){
        if (student != null) {
            io.print(student.getStudentId());
            io.print(student.getFirstName() + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        }else{
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveStudentBanner(){
        io.print("===Remove Student===");
    }

    public void displayRemoveSuccessBanner(){
        io.readString("Student successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg){
        io.print("===ERROR===");
        io.print(errorMsg);
    }
}
