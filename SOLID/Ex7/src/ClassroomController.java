public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        Peripheral pj = reg.getFirstOfType(Peripheral.class);
        pj.powerOn();
        pj.connectInput("HDMI-1");

        Light lights = reg.getFirstOfType(Light.class);
        lights.setBrightness(60);

        Air ac = reg.getFirstOfType(Air.class);
        ac.setTemperatureC(24);

        Attendance scan = reg.getFirstOfType(Attendance.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstOfType(Peripheral.class).powerOff();
        reg.getFirstOfType(Light.class).powerOff();
        reg.getFirstOfType(Air.class).powerOff();
    }
}
