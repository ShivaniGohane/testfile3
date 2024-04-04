import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NursePayCalculator {
    private static final double REGULAR_RATE = 100.0;

    //20% additional hourly rate for overtime hours
    private static final double OVERTIME_RATE_1 = 0.2;

    //15% additional hourly rate for overtime hours
    private static final double OVERTIME_RATE_2 = 0.15;

    //25% additional hourly rate for overtime hours
    private static final double OVERTIME_RATE_3 = 0.25;

    //30% additional hourly rate for overtime hours
    private static final double OVERTIME_RATE_4 = 0.3;

    public NursePayCalculator() {
    }


    public double calculateBiweeklyPay(List<ScheduleEntry> schedule) throws ParseException {
        double totalRegularHours = 0;
        double totalOvertimeHours2amTo5am = 0;
        double totalOvertimeHoursNationalHoliday = 0;

        for(ScheduleEntry entry : schedule){
            double workHours = calculateWorkHours(entry.getTimeIn(), entry.getTimeOut(), entry.getBreaktime());

            //Calculate regular and overtime hours
            if(entry.isHoliday()==true){
                totalOvertimeHoursNationalHoliday += workHours;
            }
            else if(entry.getTimeIn().charAt(0)>='2' && entry.getTimeIn().charAt(6)=='A' && entry.getTimeOut().charAt(6)=='A' && entry.getTimeOut().charAt(0)<='5'){
                totalOvertimeHours2amTo5am += workHours;
            }
            else if(totalRegularHours + workHours <= 50){
                totalRegularHours += workHours;
            }
            else {
                totalOvertimeHours2amTo5am += Math.max(0, workHours - (50 - totalRegularHours));
                totalOvertimeHoursNationalHoliday += Math.max(0, workHours - totalRegularHours);
            }
        }

        // Calculate Earning
        double regularEarnings = totalRegularHours*REGULAR_RATE;
        double overtimeEarning3amTo5am = totalOvertimeHours2amTo5am*REGULAR_RATE*OVERTIME_RATE_3;
        double overtimeEarningsNationalHoliday = totalOvertimeHoursNationalHoliday*REGULAR_RATE*OVERTIME_RATE_4;

        return  regularEarnings + overtimeEarning3amTo5am + overtimeEarningsNationalHoliday;
    }

    private static double calculateWorkHours(String timeIn, String timeOut, double breakTime) throws ParseException {
        String time1 = timeConversion(timeIn);
        String time2 = timeConversion(timeOut);

        // Creating a SimpleDateFormat object
        // to parse time in the format HH:MM:SS
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // Parsing the Time Period
        Date date1 = simpleDateFormat.parse(time1);
        Date date2 = simpleDateFormat.parse(time2);

        // Calculating the difference in milliseconds
        long differenceInMilliSeconds = Math.abs(date2.getTime() - date1.getTime());

        // Calculating the difference in Hours
        long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;

        // Calculating the difference in Minutes
        long differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;

        String str = differenceInMinutes+"";
        double val = Math.pow(10, str.length());

        double x = differenceInMinutes/val;

        return differenceInHours+x;
    }

    static String timeConversion(String s) {
        //Write your code here
        String hr = s.substring(0,2);
        String same = s.substring(2,5);
        String ans = "";
        if(s.charAt(6)=='A'){
            if(hr.equals("12")){
                hr = "00";
            }
            ans = hr + same;
        }
        else{
            int num = Integer.parseInt(hr);
            if(num != 12){
                num = num+12;
            }
            ans = num + same;
        }
        return (ans+":00");
    }
}
