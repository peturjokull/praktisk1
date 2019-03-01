import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SushiBar {

    //SushiBar settings
    private static int waitingAreaCapacity = 15;
    private static int waitressCount = 8;
    private static int duration = 4;
    public static int maxOrder = 10;
    public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 2000; // Used to calculate the time the customer spends eating
    public static int doorWait = 100; // Used to calculate the interval at which the door tries to create a customer
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter;
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) {
        log = new File(path + "log.txt");

        //Initializing shared variables for counting number of orders
        customerCounter = new SynchronizedInteger(0);
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);


        WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);
        Thread thread = new Thread(new Door(waitingArea));
        new Clock(duration);
        thread.start();


        List<Thread> listOfWaitresses = new ArrayList<>();

        for(int i = 0;i<waitressCount;i++){
            Thread waitress = new Thread(new Waitress(waitingArea));
            listOfWaitresses.add(waitress);
            waitress.start();
        }
        try {
            thread.join();
            for(int k=0;k<waitressCount;k++){
                listOfWaitresses.get(k).join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SushiBar.write("The SushiBar is closing!");
        SushiBar.write("Printing Out key figures down below");
        SushiBar.write("Total takeaways: " + SushiBar.takeawayOrders.get());
        SushiBar.write("Total bar orders: " + SushiBar.servedOrders.get());
        SushiBar.write("Total orders: " + SushiBar.totalOrders.get());

        // int take = SushiBar.takeawayOrders.get();
        // int bar = SushiBar.servedOrders.get();
        // int total = SushiBar.totalOrders.get();
        // Boolean sumUp = take+bar == total;
        // System.out.println("Does it sum up? " + sumUp);
    }

    //Writes actions in the log file and console
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
