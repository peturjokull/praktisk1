import java.util.Random;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {
    private int id;
    private int numOrders;
    private static int customerCount=0;
    private int takeAwayOrders=0;
    private int eatInOrders=0;
    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer() {
        this.id = ++customerCount;
    }


    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     * The idea is that each customer orders 1-10 orders, the customer can than order both eat in and take the rest away. Randomly determined
     */
    public synchronized int order(){
        Random r = new Random();
        numOrders = (r.nextInt(SushiBar.maxOrder)) + 1; //Generates number from 1 to 10
        for(int i=1;i<=numOrders;i++){
           double p = Math.random();
            if(p>0.5)
                takeAwayOrders++;
            else
                eatInOrders++;
        }
        SushiBar.servedOrders.add(eatInOrders);
        SushiBar.takeawayOrders.add(takeAwayOrders);
        numOrders = takeAwayOrders + eatInOrders;
        SushiBar.totalOrders.add(numOrders);
        return numOrders;
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        return this.id;
    }

    // Add more methods as you see fit
}
