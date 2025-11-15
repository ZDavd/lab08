package it.unibo.deathnote.api;

/** 
 * Represents a death record.
 */
public final class Death {
    private final String cause;
    private final String details;
    private final long timeOfDeath;

    /**
     * Constructor of Death.
     * 
     * @param cause the cause of death
     * @param details additional details about the death
     */
    public Death(final String cause, final String details) {
        this.cause = cause;
        this.details = details;
        this.timeOfDeath = System.currentTimeMillis();
    }

    /**
     * Default constructor of Death with default cause and details.
     */
    public Death() {
        this.cause = "Heart attack";
        this.details = "";
        this.timeOfDeath = System.currentTimeMillis();
    }

    /** 
     * Gets the cause of death.
     * 
     * @return the cause of death
     */
    public String getCause() {
        return cause;
    }

    /** 
     * Gets additional details about the death.
     * 
     * @return additional details about the death
     */
    public String getDetails() {
        return details;
    }

    /** 
     * Gets the time of death.
     * 
     * @return the time of death
     */
    public long getTimeOfDeath() {
        return timeOfDeath;
    }
}
