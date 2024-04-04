import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        //Time In	Time Out same day or next day
        //2:48 PM	4:24 PM
        //4:24 AM	2:48 AM
        //12:48 AM	8:24 PM
        //1:12 AM	2:48 PM
        //6:24 AM	2:24 AM
        //2:48 AM	4:24 AM	Naional Holiday
        //10:48 AM	9:12 PM
        //10:00 PM	7:36 PM
        //11:12 PM	3:36 PM
        //4:00 AM	8:00 AM
        //8:48 AM	8:24 PM
        //9:36 PM	4:24 PM
        //9:12 AM	4:48 PM
        //3:12 AM	7:36 AM
        List<ScheduleEntry> scheduleEntryList = new ArrayList<>();
        scheduleEntryList.add(new ScheduleEntry("02:48 PM", "04:24 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("04:24 AM","02:48 AM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("12:48 AM","08:24 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("01:12 AM","02:48 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("06:24 AM","02:24 AM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("02:48 AM","04:24 AM", 0, true));
        scheduleEntryList.add(new ScheduleEntry("10:48 AM","09:12 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("10:00 PM","07:36 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("11:12 PM","03:36 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("04:00 AM","08:00 AM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("08:48 AM","08:24 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("09:36 PM","04:48 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("09:12 AM","04:48 PM", 0, false));
        scheduleEntryList.add(new ScheduleEntry("03:12 AM","07:36 AM", 0, false));

        NursePayCalculator payCalculator = new NursePayCalculator();
        double totalEarning = payCalculator.calculateBiweeklyPay(scheduleEntryList);

        System.out.println("Total Earnings: $" + totalEarning);
    }

    static void timeConversion(String s) {
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
        System.out.println(ans+":00");
    }
}