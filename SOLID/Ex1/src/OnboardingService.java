import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    InputParser parser;
    InputValidator validator;
    Logger logger;
    public OnboardingService(StudentRepository db) {
        this.db = db;
        this.parser = new InputParser();
        this.validator = new InputValidator();
        this.logger = new Logger();
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {

        logger.logInput(raw);

        Student student = this.parser.parse(raw);

        // validation inline, printing inline
        List<String> error = this.validator.validate(student);
        if(!error.isEmpty()){
            logger.logErrors(error);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, student.name, student.email, student.phone, student.program);
        db.save(rec);

        this.logger.logSuccess(rec, db.count());

    }
}
