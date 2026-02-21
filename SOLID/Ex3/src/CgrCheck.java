public class CgrCheck implements Rule{
    private final double minCgr;

    public CgrCheck(double minCgr){this.minCgr = minCgr;}

    public String check(StudentProfile student){
        if (student.cgr < this.minCgr){
            return "CGR below "+minCgr;
        }

        return null;
    }
}
