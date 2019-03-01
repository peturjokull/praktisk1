/**
 * This class implements the consumer part of the producer/consumer problem. One
 * waitress instance corresponds to one consumer.
 */
/*
 * 𝑆𝑢𝑠ℎ𝑖𝐵𝑎𝑟. 𝑤𝑟𝑖𝑡𝑒(𝑇ℎ𝑟𝑒𝑎𝑑. 𝑐𝑢𝑟𝑟𝑒𝑛𝑡𝑇ℎ𝑟𝑒𝑎𝑑().
 * 𝑔𝑒𝑡𝑁𝑎𝑚𝑒() + “: 𝐶𝑢𝑠𝑡𝑜𝑚𝑒𝑟” + 𝑐𝑢𝑠𝑡𝑜𝑚𝑒𝑟𝑁𝑜 + “𝑖𝑠 𝑛𝑜𝑤
 * 𝑐𝑟𝑒𝑎𝑡𝑒𝑑.”);
 */
public class Waitress implements Runnable {
    private WaitingArea waitingArea;
    private Customer customer;

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is created for this instance
     */
    @Override
    public void run() {
        while (SushiBar.isOpen || waitingArea.isEmpty() == false) {
            customer = waitingArea.next();

            // if there are customers
            if (customer != null) {
                SushiBar.write(Thread.currentThread().getName() + ": Customer " + customer.getCustomerID()
                        + " is now waitered");
                try {
                    Thread.sleep(SushiBar.waitressWait); // standard waitress wait
                } catch (Exception e) {
                    e.printStackTrace();
                }
                customer.order();
                SushiBar.write(
                        Thread.currentThread().getName() + ": Customer " + customer.getCustomerID() + " just ordered ");
                SushiBar.write(
                        Thread.currentThread().getName() + ": Customer " + customer.getCustomerID() + " is now eating");
            }
            // Customer eats

            try {
                Thread.sleep(SushiBar.customerWait);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SushiBar.write(Thread.currentThread().getName() + ": Customer " + customer.getCustomerID()
                    + " has finished eating");
        }
    }
}
