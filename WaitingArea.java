import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a waiting area used as the bounded buffer, in the
 * producer/consumer problem.
 */
public class WaitingArea {
    private Queue<Customer> waitingCustomers;
    private int maxCapacity;

    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        this.maxCapacity = size;
        waitingCustomers = new LinkedList<Customer>();
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {

        while (waitingCustomers.size() == maxCapacity) {
            try {
                this.wait();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        if (SushiBar.isOpen) {
            this.notify();
            SushiBar.customerCounter.increment();
            waitingCustomers.add(customer);
        }
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        while (SushiBar.isOpen && this.isEmpty()) {
            try {
                this.wait();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        if (!this.isEmpty()) {
            this.notify();
            return waitingCustomers.remove();
        }
        return null;
    }

    // Add more methods as you see fit
    public synchronized Boolean isEmpty() {
        return waitingCustomers.size() == 0;
    }
}