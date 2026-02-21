import java.util.List;

public class Logger {

    public void logInput(String raw){
        System.out.println("INPUT: " + raw);
    }

    public void logErrors(List<String> errors){
        System.out.println("ERROR: cannot register");
        for (String e : errors) {
            System.out.println("- " + e);
        }
    }
    public void logSuccess(StudentRecord record, int cnt){
        System.out.println("OK: created student " + record.id);
        System.out.println("Saved. Total students: " + cnt);
        System.out.println("CONFIRMATION:");
        System.out.println(record);
    }
}
