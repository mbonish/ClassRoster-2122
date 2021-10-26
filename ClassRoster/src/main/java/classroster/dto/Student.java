package classroster.dto;

public class Student {
    private String firstName;
    private String lastName;
    private String studentId;
    private String cohort;
//no setter for the student id field
    // studentId is passed in as a parameter of the constructor
    public Student (String studentId){
        this.studentId = studentId;
    }

    public String getStudentId(){
        return studentId;
    }
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

     public String getCohort(){
        return cohort;
     }
     public void setCohort(String cohort){
        this.cohort = cohort;
     }

}
