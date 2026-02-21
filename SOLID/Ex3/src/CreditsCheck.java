public class CreditsCheck implements Rule{
    private final int minCredits;

    public CreditsCheck(int minCredits){this.minCredits = minCredits;}
    public String check(StudentProfile student){
        if (student.earnedCredits < this.minCredits) {
            return "credits below 20";
        }
        return null;
    }
}
