public class DisciplinaryCheck implements Rule{
    public String check(StudentProfile student){
        if (student.disciplinaryFlag != LegacyFlags.NONE) {
            return "disciplinary flag present";
        }
        return null;
    }
}
