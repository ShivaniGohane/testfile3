public class ScheduleEntry {
    private String timeIn;
    private String timeOut;
    private double breaktime;
    private boolean isHoliday;

    public ScheduleEntry() {
    }

    public ScheduleEntry(String timeIn, String timeOut, double breaktime, boolean isHoliday) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.breaktime = breaktime;
        this.isHoliday = isHoliday;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public double getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(double breaktime) {
        this.breaktime = breaktime;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }
}
