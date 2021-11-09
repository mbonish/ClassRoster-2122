package ClassRoster;


import ClassRoster.Controller.ClassRosterController;
import ClassRoster.UI.ClassRosterView;
import ClassRoster.UI.UserIO;
import ClassRoster.UI.UserIOConsoleImpl;
import ClassRoster.dao.ClassRosterDao;
import ClassRoster.dao.ClassRosterDaoFileImpl;

class App {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIO);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterController controller =
                new ClassRosterController(myDao,myView);
        controller.run();
    }
}