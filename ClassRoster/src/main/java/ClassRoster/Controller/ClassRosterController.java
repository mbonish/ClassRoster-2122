package ClassRoster.Controller;
import ClassRoster.UI.UserIO;
import ClassRoster.UI.UserIOConsoleImpl;

public class ClassRosterController {
        private UserIO io = new UserIOConsoleImpl();

        public void run() {
            boolean keepGoing = true;
            boolean var2 = false;

            while(keepGoing) {
                this.io.print("ClassRoster2.Main Menu");
                this.io.print("1. List Student IDs");
                this.io.print("2. Create New student ");
                this.io.print("3. View a Student");
                this.io.print("4. Remove a Student ");
                this.io.print("5. Exit");
                int menuSelection = this.io.readInt("Please select from the above choices.", 1, 5);
                switch(menuSelection) {
                    case 1:
                        this.io.print("LIST STUDENTS");
                        break;
                    case 2:
                        this.io.print("CREATE STUDENT");
                        break;
                    case 3:
                        this.io.print("VIEW STUDENT");
                        break;
                    case 4:
                        this.io.print("REMOVE STUDENT");
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        this.io.print("UNKNOWN COMMAND");
                }
            }

            this.io.print("GOOD BYE");
        }
    }


