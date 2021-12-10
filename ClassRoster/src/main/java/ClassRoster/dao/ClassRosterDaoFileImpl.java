package ClassRoster.dao;

import ClassRoster.dto.Student;

import java.io.*;
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
    public Student addStudent(String studentId, Student student)
            throws ClassRosterDaoException {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
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
    public List<Student> getAllStudents()
            throws ClassRosterDaoException {
        loadRoster();
        return new ArrayList(students.values());
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
    public Student getStudent(String studentId)
            throws ClassRosterDaoException {
        loadRoster();
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
    public Student removeStudent(String studentId)
            throws ClassRosterDaoException {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
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

    private String marshallStudent(Student aStudent){
        // We need to turn a Student object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer.
        String studentAsText = aStudent.getStudentId() + DELIMITER;

        studentAsText += aStudent.getFirstName() + DELIMITER;

        studentAsText +=aStudent.getLastName() + DELIMITER;

        studentAsText += aStudent.getCohort();
        return studentAsText;
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE. See loadRoster for f
     * for file format.
     * @throws ClassRosterDaoException
     */
    private void writeRoster() throws ClassRosterDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new ClassRosterDaoException(" Could not save student data.", e
            );
        }
        /**
         * Write out the Student objects to the roster file.
         * NOTE TO THE APPRENTICES: We could just grab the student map,
         * get the Collection of Student and iterate over then. BUT we have already created
         * a method that gets a list of students--so we re-use it.
         */
            String studentAsText;
            List<Student> studentList = this.getAllStudents();
            for(Student currentStudent: studentList){
                //turn a student into a string
                studentAsText = marshallStudent(currentStudent);

                //write the Student object to the file
                out.println(studentAsText);

                //force PritWriter to write line to the file
                out.flush();

                //clean up
                out.close();
            }
        }

    }





