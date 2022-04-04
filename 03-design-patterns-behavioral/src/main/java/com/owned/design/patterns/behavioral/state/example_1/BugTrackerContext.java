package com.owned.design.patterns.behavioral.state.example_1;

import lombok.Data;

/**
 * The context for a *very* simple bug tracker.  The example shows how the State pattern can be used for keeping
 * tabs on the cost of the work being done to fix the bug as it travels through the various departments.
 * <p>
 * All the different states are listed here.
 * <p>
 * This class controls the state transitions and always 'knows' the current state.
 */
@Data
public class BugTrackerContext {

    /**
     * Initial state
     */
    private BugState customerSupportState;

    /**
     * Another state...
     */
    private BugState engineeringState;

    /**
     * Another state...
     */
    private BugState testingState;

    /**
     * Terminal state.
     */
    private BugState closedState;

    /**
     * The current state
     */
    private BugState currentState;


    /**
     * Constructor initialises the state context.
     * <p>
     * It creates all the state objects and passes the context into each one.
     * <p>
     * It sets the initial state.
     */
    public BugTrackerContext() {
        customerSupportState = new CustomerSupportState(this);
        engineeringState = new EngineeringState(this);
        testingState = new TestingState(this);
        closedState = new ClosedState(this);

        // Set initial state
        currentState = customerSupportState;
    }

    public void setState(BugState state) {
        currentState = state;
    }

    /**
     * Users log their hours on the bug.
     *
     * @param hours hours logged on bug
     */
    public void logHoursWorked(int hours) {
        currentState.updateHoursWorked(hours);
    }

    /**
     * Users notify system they have finished working on bug.
     */
    public void finishWork() {
        currentState.setNextState();
    }

    /**
     * Managers want to know cost of bug fix in current state.
     *
     * @return current state cost
     */
    public int getCostOfFixingBugForCurrentState() {
        return currentState.getCost();
    }

    /**
     * Managers want to know total cost of bug fix
     *
     * @return total cost of fix.
     */
    public int getTotalCostOfFixingBug() {
        return customerSupportState.getCost()
                + engineeringState.getCost()
                + testingState.getCost()
                + closedState.getCost();
    }
}
