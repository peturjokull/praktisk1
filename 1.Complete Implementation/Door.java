/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {
    WaitingArea waitingArea;
    Customer customer;

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the waiting area
     */
    @Override
    public void run() {
        while(SushiBar.isOpen){
            waitingArea.enter(this.createCustomer());
            try {
                Thread.sleep((SushiBar.doorWait));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Add more methods as you see fit
    private synchronized Customer createCustomer() {
        Customer newCustomer = new Customer();
        /*𝑆𝑢𝑠ℎ𝑖𝐵𝑎𝑟. 𝑤𝑟𝑖𝑡𝑒(𝑇ℎ𝑟𝑒𝑎𝑑. 𝑐𝑢𝑟𝑟𝑒𝑛𝑡𝑇ℎ𝑟𝑒𝑎𝑑(). 𝑔𝑒𝑡𝑁𝑎𝑚𝑒() + “: 𝐶𝑢𝑠𝑡𝑜𝑚𝑒𝑟” + 𝑐𝑢𝑠𝑡𝑜𝑚𝑒𝑟𝑁𝑜 + “𝑖𝑠 𝑛𝑜𝑤 𝑐𝑟𝑒𝑎𝑡𝑒𝑑.”);*/
        SushiBar.write(Thread.currentThread().getName() + ": Customer " + newCustomer.getCustomerID() + " is now created");
        return newCustomer;
    }
}
