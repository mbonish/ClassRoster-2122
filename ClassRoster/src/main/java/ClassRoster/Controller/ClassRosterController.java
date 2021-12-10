package ClassRoster.Controller;

import ClassRoster.UI.ClassRosterView;
import ClassRoster.UI.UserIO;
import ClassRoster.UI.UserIOConsoleImpl;
import ClassRoster.dao.ClassRosterDao;
import ClassRoster.dao.ClassRosterDaoException;
import ClassRoster.dao.ClassRosterDaoFileImpl;
import ClassRoster.dto.Student;

import java.util.List;

public class ClassRosterController {
    private UserIO io = new UserIOConsoleImpl();
    ClassRosterDao dao;
    ClassRosterView view;

    //Constructor
    public ClassRosterController(ClassRosterDao dao, ClassRosterView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() throws ClassRosterDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                io.print("---Main Menu (Class Roster) ----");
                io.print("1. List Student IDs");
                io.print("2. Create New student ");
                io.print("3. View a Student");
                io.print("4. Remove a Student ");
                io.print("5. Exit");

                menuSelection = this.io.readInt("Please select from the above choices.", 1, 5);


                switch (menuSelection) {
                    case 1:
//                        this.io.print("LIST STUDENTS");
                        listStudents();
                        break;
                    case 2:
//                        this.io.print("CREATE STUDENT");
                        createStudent();
                        break;
                    case 3:
//                        this.io.print("VIEW STUDENT");
                        viewStudent();
                        break;
                    case 4:
//                        this.io.print("REMOVE STUDENT");
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
//                        this.io.print("UNKNOWN COMMAND");
                        unknownCommand();
                }
            }

//            this.io.print("GOOD BYE");
            exitMessage();
        } catch (ClassRosterDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
        private void createStudent () throws ClassRosterDaoException {
            view.displayCreateStudentBanner();
            Student newStudent = view.getNewStudentInfo();
            dao.addStudent(newStudent.getStudentId(), newStudent);
            view.displayCreateSuccessBanner();
        }

        private void listStudents () throws ClassRosterDaoException {
            view.displayDisplayAllBanner();
            List<Student> studentList = dao.getAllStudents();
            view.displayStudentList(studentList);
        }

        private void viewStudent () throws ClassRosterDaoException {
            view.displayDisplayStudentBanner();
            String studentId = view.getStudentIdChoice();
            Student student = dao.getStudent(studentId);
            view.displayStudent(student);
        }

        private void removeStudent () throws ClassRosterDaoException {
            view.displayRemoveStudentBanner();
            String studentId = view.getStudentIdChoice();
            dao.removeStudent(studentId);
            view.displayRemoveSuccessBanner();
        }

        private void unknownCommand () {
            view.displayUnknownCommandBanner();
        }

        private void exitMessage () {
            view.displayExitBanner();
        }

    }



