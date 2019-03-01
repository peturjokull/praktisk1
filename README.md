# Sushi Bar Simulation
## The Overall Purpose
In this assignment you are going to simulate a sushi bar. The implementation will need to manage
several threads that are using shared resources. These resources should be protected using the monitor
concept. The implementation should use the producer/consumer model to solve the problem.
The Producer and Consumers should synchronize using the 𝑤𝑎𝑖𝑡( ), 𝑛𝑜𝑡𝑖𝑓𝑦( ) and 𝑛𝑜𝑡𝑖𝑓𝑦𝐴𝑙𝑙( ), and
mutual exclusion should be implemented using the Java monitor.
## The Problem
The sushi bar consists of a clock, a door, a waiting area, a number of waitresses and customers. The bar
is open for only a specific time period, in which the customers can enter the waiting area. Because of the
limited number of waitresses, customers may have to wait in the waiting area. When a waitress fetches
a customer, he/she will order some sushi. Customers have the choice to eat some of their sushi in the
bar and have the rest as takeaway.
When it is closing time, the bar cannot accept more customers and only those who have already entered
the waiting area will be served (i.e. customers already in the waiting area are allowed to order).
## The Customer
When a customer is created it should be given a unique ID. Also, the customer should be able to order
food, when he/she is fetched from the waiting area by a waitress. After ordering, the customer takes
some time eating.
Note that the customer should have minimal functionality, leaving the adding and removing of
customers in the waiting area to the producer (door) and consumers (waitresses).
## The Door
The Door will work as the producer.
The door creates customers at random intervals and tries to put them in the waiting area if there is
room. If there is no more room in the waiting area the door should wait until notified that a customer
left the waiting area.
## The Waiting Area
The waiting area is a common resource shared between the producer and consumers. Customers can
only be added as long as there is room in the waiting area, dictated by 𝑤𝑎𝑖𝑡𝑖𝑛𝑔𝐴𝑟𝑒𝑎𝐶𝑎𝑝𝑎𝑐𝑖𝑡𝑦.
The waiting area should have:
- A way of keeping track of all the waiting customers
- A max capacity
Functionality:
- Let customers enter when there is room
- A way for customers to be fetched by waitresses
## Waitresses
The waitresses will work as the consumers.
When a customer is fetched, the waitress uses some time before taking the customer’s order.
After the customer is finished ordering and done eating, the waitress can fetch a new customer from the
waiting area.
Note that the customers should be fetched from the waiting area in a first come – first serve manner.
This means that the earlier the customer enters the waiting area, the sooner he/she is fetched.
## The Clock
The clock in the bar serves two purposes:
- It will alert the door of the closing time, so the door will generate no more customers.
- The clock keeps track of the current time of simulation, which will be used for logging.
You will not have to implement the clock yourself; instead you must use the provided class: Clock.java.
This clock sets the isOpen variable to false when the simulation time ends. The only thing you need to do
is to initiate the clock (new Clock(duration)), and use the isOpen variable properly
