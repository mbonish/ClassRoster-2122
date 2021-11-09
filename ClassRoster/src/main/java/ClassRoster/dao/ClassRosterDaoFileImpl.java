package ClassRoster.dao;

import ClassRoster.dto.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private Map<String, Student> students = new HashMap<>();


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
    public List<Student> getAllStudents(){
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
        Student removedStudent =students.remove(studentId);
        return removedStudent;
    }

}

