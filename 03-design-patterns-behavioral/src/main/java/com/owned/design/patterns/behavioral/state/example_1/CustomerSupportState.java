package com.owned.design.patterns.behavioral.state.example_1;

/**
 * This is the initial state.
 *
 */
public class CustomerSupportState implements BugState {

    /**
     * Cost per hour for Customer Support work
     */
    private static final int HOURLY_RATE = 80;

    /**
     * Reference to the state context
     */
    private BugTrackerContext bugTrackerContext;

    /**
     * Internal state of this object - Cost of the bug
     */
    private int bugCost;

    /**
     * Constructor takes the context as an arg.
     *
     * @param bugTrackerContext the bug tracker
     */
    public CustomerSupportState(BugTrackerContext bugTrackerContext) {
        this.bugTrackerContext = bugTrackerContext;

        // initialise this object's internal state
        bugCost = 0;
    }

    public void updateHoursWorked(int hoursWorked) {

        bugCost += (hoursWorked * HOURLY_RATE);
    }

    public int getCost() {

        return bugCost;
    }

    @Override
    public void setNextState() {
        // control which state we go to next
        bugTrackerContext.setState(bugTrackerContext.getEngineeringState());
    }
}
