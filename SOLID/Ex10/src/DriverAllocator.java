public class DriverAllocator implements AllocatorService{
    public String allocate(String studentId) {
        // fake deterministic driver
        return "DRV-17";
    }
}
