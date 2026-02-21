public class AttendanceCheck implements Rule{
    private final int minAttendancePct;

    public AttendanceCheck(int minAttendancePct){this.minAttendancePct = minAttendancePct;}
    public String check(StudentProfile student){
        if (student.attendancePct < this.minAttendancePct) {
            return "attendance below 75";
        }
        return null;
    }
}
