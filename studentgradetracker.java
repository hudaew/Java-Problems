import java.util.ArrayList;

interface Action {
    void perform(ArrayList<String> names, ArrayList<Integer> grades);
}

class AddStudent implements Action {
    public void perform(ArrayList<String> names, ArrayList<Integer> grades) {
        System.out.print("  Student Name : ");
        String name = System.console().readLine();
        System.out.print("  Grade        : ");
        int grade = Integer.parseInt(System.console().readLine());
        names.add(name);
        grades.add(grade);
        System.out.println("  >> " + name + " added.");
    }
}

class RemoveStudent implements Action {
    public void perform(ArrayList<String> names, ArrayList<Integer> grades) {
        if (names.isEmpty()) { System.out.println("  >> No students."); return; }
        System.out.print("  Name to remove: ");
        String name = System.console().readLine();
        int index = -1;
        for (int i = 0; i < names.size(); i++)
            if (names.get(i).equalsIgnoreCase(name)) { index = i; break; }
        if (index == -1) System.out.println("  >> Not found.");
        else {
            names.remove(index);
            grades.remove(index);
            System.out.println("  >> " + name + " removed.");
        }
    }
}

class ShowHighest implements Action {
    public void perform(ArrayList<String> names, ArrayList<Integer> grades) {
        if (grades.isEmpty()) { System.out.println("  >> No students."); return; }
        int high = grades.get(0); String top = names.get(0);
        for (int i = 1; i < grades.size(); i++)
            if (grades.get(i) > high) { high = grades.get(i); top = names.get(i); }
        System.out.println("  >> Highest : " + top + " [" + high + "]");
    }
}

class ShowLowest implements Action {
    public void perform(ArrayList<String> names, ArrayList<Integer> grades) {
        if (grades.isEmpty()) { System.out.println("  >> No students."); return; }
        int low = grades.get(0); String bot = names.get(0);
        for (int i = 1; i < grades.size(); i++)
            if (grades.get(i) < low) { low = grades.get(i); bot = names.get(i); }
        System.out.println("  >> Lowest  : " + bot + " [" + low + "]");
    }
}

class ShowAverage implements Action {
    public void perform(ArrayList<String> names, ArrayList<Integer> grades) {
        if (grades.isEmpty()) { System.out.println("  >> No students."); return; }
        int sum = 0;
        for (int g : grades) sum += g;
        System.out.printf("  >> Average : %.2f%n", (double) sum / grades.size());
    }
}

class ShowReport implements Action {
    public void perform(ArrayList<String> names, ArrayList<Integer> grades) {
        if (names.isEmpty()) { System.out.println("  >> No students."); return; }

        int high = grades.get(0), low = grades.get(0), sum = 0;
        String top = names.get(0), bot = names.get(0);

        System.out.println();
        System.out.println("  +----------------------+-------+");
        System.out.println("  |   SUMMARY REPORT     |       |");
        System.out.println("  +----------------------+-------+");
        System.out.printf ("  | %-20s | %-5s |%n", "Name", "Grade");
        System.out.println("  +----------------------+-------+");

        for (int i = 0; i < names.size(); i++) {
            System.out.printf("  | %-20s | %-5d |%n", names.get(i), grades.get(i));
            sum += grades.get(i);
            if (grades.get(i) > high) { high = grades.get(i); top = names.get(i); }
            if (grades.get(i) < low)  { low  = grades.get(i); bot = names.get(i); }
        }

        System.out.println("  +----------------------+-------+");
        System.out.printf ("  | %-20s | %-5d |%n", "Highest: " + top,  high);
        System.out.printf ("  | %-20s | %-5d |%n", "Lowest:  " + bot,   low);
        System.out.printf ("  | %-20s | %-5.2f |%n", "Average:",  (double) sum / grades.size());
        System.out.println("  +----------------------+-------+");
        System.out.println();
    }
}

class GradeSystem {
    Action action;
    ArrayList<String>  names  = new ArrayList<>();
    ArrayList<Integer> grades = new ArrayList<>();

    void setAction(Action a) { action = a; }
    void run() { action.perform(names, grades); }
}

public class Main {
    public static void main(String[] args) {
        GradeSystem system = new GradeSystem();

        while (true) {
            System.out.println();
            System.out.println("  ================================");
            System.out.println("       STUDENT GRADE SYSTEM      ");
            System.out.println("  ================================");
            System.out.println("   [1] Add Student               ");
            System.out.println("   [2] Remove Student            ");
            System.out.println("   [3] Show Highest              ");
            System.out.println("   [4] Show Lowest               ");
            System.out.println("   [5] Show Average              ");
            System.out.println("   [6] Summary Report            ");
            System.out.println("   [7] Exit                      ");
            System.out.println("  ================================");
            System.out.print("   Choose: ");

            String choice = System.console().readLine();
            System.out.println();

            switch (choice) {
                case "1": system.setAction(new AddStudent());    break;
                case "2": system.setAction(new RemoveStudent()); break;
                case "3": system.setAction(new ShowHighest());   break;
                case "4": system.setAction(new ShowLowest());    break;
                case "5": system.setAction(new ShowAverage());   break;
                case "6": system.setAction(new ShowReport());    break;
                case "7": System.out.println("  >> Goodbye!"); return;
                default:  System.out.println("  >> Invalid choice."); continue;
            }

            system.run();
        }
    }
}
