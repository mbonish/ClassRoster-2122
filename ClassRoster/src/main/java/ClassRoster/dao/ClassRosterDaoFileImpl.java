package ClassRoster.dao;

import ClassRoster.dto.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private Map<String, Student> students = new HashMap<>();
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    /**
     * Adds the given Student to the roster and associates it with the given
     * student id. If there is already a student associated with the given
     * student id it will return that student object, otherwise it will
     * return null.
     *
     * @param studentId id with which student is to be associated
     * @param student   student to be added to the roster
     * @return the Student object previously associated with the given
     * student id if it exists, null otherwise
     */
    @Override
    public Student addStudent(String studentId, Student student) {
        Student newStudent = students.put(studentId, student);
        return newStudent;
    }


    /**
     * Returns a String array containing the student ids of all
     * students in the roster.
     *
     * @return String array containing the ids of all the students
     * in the roster
     */
    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }


    /**
     * Returns a String array containing the student ids of all
     * students in the roster.
     *
     * @param studentId ID of the student to retrieve
     * @return String array containing the ids of all the students
     * in the roster
     */
    @Override
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }


    /**
     * Removes from the roster the student associated with the given id.
     * Returns the student object that is being removed or null if
     * there is no student associated with the given id
     *
     * @param studentId id of student to be removed
     * @return Student object that was removed or null if no student
     * was associated with the given student id
     */
    @Override
    public Student removeStudent(String studentId) {
        Student removedStudent = students.remove(studentId);
        return removedStudent;
    }

    //This method it retrieving the student information from the text file then using
    //pieces to create a new student object
    private Student unmarshallStudent(String studentAsText) {
        // studentAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Ada::Lovelace::Java-September1842
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in studentTokens.
        // Which should look like this:
        // ______________________________________
        // |    |   |        |                  |
        // |1234|Ada|Lovelace|Java-September1842|
        // |    |   |        |                  |
        // --------------------------------------
        //  [0]  [1]    [2]         [3]


        String[] studentTokens = studentAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array.
        String studentId = studentTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        Student studentFromFile = new Student(studentId);


        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        //Index 1 - First Name
        studentFromFile.setFirstName(studentTokens[1]);

        //Index 2- Last Name
        studentFromFile.setLastName(studentTokens[2]);

        //Index 3- Student Cohort
        studentFromFile.setCohort(studentTokens[3]);

        //We have now created the final student and can return it
        return studentFromFile;
    }

    private void loadRoster() throws ClassRosterDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterDaoException(
                    "-_- Could not load roster data into memory.", e);
        }

        //currentLine holds the most recent line read from the file
        String currentLine;
        //currentStudent holds the most recent student unmarshalled
        Student currentStudent;
        //Go through ROSTER_FILE line by line , decoding each line into a
        //Student object by calling the unmarshalled student method.
        //process while we have more lines in the file
        while (scanner.hasNextLine()) {
            //unmarshall the line into a student
            currentLine = scanner.nextLine();
            currentStudent = unmarshallStudent(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            students.put(currentStudent.getStudentId(), currentStudent);
        }
        scanner.close();
    }
}




