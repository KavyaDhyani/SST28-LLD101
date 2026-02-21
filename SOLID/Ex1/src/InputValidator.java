import java.util.ArrayList;
import java.util.List;

public class InputValidator {
    public List<String> validate(Student student){
        String name = student.name;
        String email = student.email;
        String phone = student.phone;
        String program = student.program;

        // validation inline, printing inline
        List<String> errors = new ArrayList<>();
        if (name.isBlank()) errors.add("name is required");
        if (email.isBlank() || !email.contains("@")) errors.add("email is invalid");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!(program.equals("CSE") || program.equals("AI") || program.equals("SWE"))) errors.add("program is invalid");

        return errors;
    }
}
