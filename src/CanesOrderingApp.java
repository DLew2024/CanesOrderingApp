import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

enum JOB {
    KITCHEN, DRIVETHRU, TRAINER, MEET, CIT, CITC, DRINKS 
}

public class CanesOrderingApp {

    public static final int ORDERSTARTINGNUM = 1000;
    public static String date, formattedTime, formattedDate, clockInTime, clockOutTime, hourClockIn, MinuteClockIn, M;
    static DateTimeFormatter dateFormatter, timeFormatter, hourFormatter, minuteFormatter, MFormatter;
    
    static LocalDate currentDate;
    static LocalTime currentTime; 
    public static int Hours, HoursClockedIn, MinutesClockedIn, minutes, hours;

    public static void main(String[] args) throws Exception {

        boolean clockIn = false;
        boolean takeOrder = false; 
        JOB JOBCODE = JOB.KITCHEN;
        String name;
        int unit = 1;
        int orderNum = ORDERSTARTINGNUM;

        currentDate = LocalDate.now();
        dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        formattedDate = currentDate.format(dateFormatter);
        date = formattedDate;

        if (!clockIn) {
        //sout
        // First Clock In
        topClockIn(unit, formattedDate);

        currentTime = LocalTime.now(); 
        timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        hourFormatter = DateTimeFormatter.ofPattern("hh");
        minuteFormatter = DateTimeFormatter.ofPattern("mm");
        MFormatter = DateTimeFormatter.ofPattern("a"); 
        formattedTime = currentTime.format(timeFormatter);
        clockInTime = formattedTime;
        System.out.printf("Time in: %20s\n", clockInTime);

        bottom();

        // clock out // same day clock out 

        topClockIn(unit, formattedTime);

        // Clock in.
        currentTime = LocalTime.now(); 
        formattedTime = currentTime.format(timeFormatter);
        clockInTime = formattedTime;
        hourClockIn = currentTime.format(hourFormatter);
        HoursClockedIn = Integer.parseInt(hourClockIn);
        MinuteClockIn = currentTime.format(hourFormatter);
        MinutesClockedIn = Integer.parseInt(MinuteClockIn);

        System.out.printf("\nJOB:%10s\n", JOBCODE);    
        System.out.printf("Time in: %21s\n", clockInTime);
        waitInSeconds(60);

        //Clock Out.
        currentTime = LocalTime.now(); 
        formattedTime = currentTime.format(timeFormatter);
        clockOutTime = formattedTime;
        
        formattedTime = currentTime.format(hourFormatter);
        hours = Integer.parseInt(formattedTime);
        
        formattedTime = currentTime.format(minuteFormatter);
        minutes = Integer.parseInt(formattedTime);
        
        formattedTime = currentTime.format(MFormatter);
        M = formattedTime;
        
        
        if (M.equals("PM")) {
            Hours = HoursClockedIn + 12;
        }

        System.out.println("" + Hours);
        System.out.println("" + minutes);
        

        hours = hours - HoursClockedIn;
        minutes = minutes - MinutesClockedIn;

        System.out.println("" + minutes);

        
        System.out.printf("Time out: %20s\n", clockOutTime);
        System.out.printf("Hours this shift:     %d:%d", Hours, minutes); 
        System.out.println(); 

        bottomClockOut();

        } 

        if (takeOrder) {

            name = "Darius";
            topOfOrder(formattedTime, formattedDate, name, orderNum);
            orderNum = orderNum++;
            // Add food 
            bottom();

        }


    }

    public static void topOfOrder(String time, String date, String name, int orderNum) {
        for (int i = 1; i < 70; i++) {                      // 69 *
            System.out.print("*");
        }   System.out.println("*");                      // 70 *

        System.out.printf("Check: %d\t\t\t\t\t      Date:%11s\n", orderNum, date); 
        System.out.printf("Customer: %s\t\t\t\t      Time:%11s\n\n\n", name,  time);

        for (int i = 1; i < 70; i++) {                      // 69 *
            System.out.print("=");
        }   System.out.println("=");                      // 70 *
        System.out.println();
    }

    static void bottom() {
        System.out.println();
        for (int i = 1; i < 70; i++) {                      // 69 *
            System.out.print("*");
        }   System.out.println("*");                      // 70 *
    }

    static void bottomClockOut() {
        bottom();
        System.out.println("This is verification for your hours\nworked.\nKeep this for your records.\n");
    }

    static void topClockIn(int unit, String dateString) {
        System.out.println();
        if (isConsoleSupportsAnsi()) {
            // ANSI escape code for bold text
            String boldCode = "\u001B[1m";

            // ANSI escape code for resetting text attributes
            String resetCode = "\u001B[0m";

            // Text to be printed in bold
            String boldText = "Employee Clock Out";

            // Print the bold text
            System.out.printf("\t\t\t");
            System.out.println(boldCode + boldText + resetCode);
        } else {
            System.out.println("Your console does not support ANSI escape codes.");
        }
        System.out.printf("UNIT # %5s \t\t\t\t\t\t    %s\n\n", unit, date);
    }

    private static boolean isConsoleSupportsAnsi() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("nix") || os.contains("nux") || os.contains("mac");
    }

    private static void waitInSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted while waiting: " + e.getMessage());
        }
    }
}