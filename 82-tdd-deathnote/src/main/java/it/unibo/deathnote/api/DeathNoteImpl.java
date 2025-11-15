package it.unibo.deathnote.api;

import java.util.Map;

/** 
 * Implementation of DeathNote interface.
 */
public final class DeathNoteImpl implements DeathNote {

    private static final int DEATH_CAUSE_TIME_LIMIT = 40;
    private static final int DEATH_DETAILS_TIME_LIMIT = 6000 + DEATH_CAUSE_TIME_LIMIT;
    private final Map<String, Death> deathRecords;
    private String lastInserted;

    /** 
     * Constructor of DeathNoteImpl, initializes the death records map and last inserted name.
     */
    public DeathNoteImpl() {
        this.deathRecords = new java.util.LinkedHashMap<>();
        this.lastInserted = null;
    }

    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber <= 0 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Rule number must be positive");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(final String name) {
        deathRecords.putIfAbsent(name, new Death());
        lastInserted = name;
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if (deathRecords.isEmpty() || lastInserted == null) {
            throw new IllegalStateException("No names written in the Death Note");
        }

        final Death currentDeath = deathRecords.get(lastInserted);

        return currentDeath.getTimeOfDeath() + DEATH_CAUSE_TIME_LIMIT >= System.currentTimeMillis() 
            && deathRecords.replace(lastInserted, new Death(cause, currentDeath.getDetails())) != null;
    }

    @Override
    public boolean writeDetails(final String details) {
        if (deathRecords.isEmpty() || lastInserted == null) {
            throw new IllegalStateException("No names written in the Death Note");
        }

        final Death currentDeath = deathRecords.get(lastInserted);

        return currentDeath.getTimeOfDeath() + DEATH_DETAILS_TIME_LIMIT >= System.currentTimeMillis() 
            && deathRecords.replace(lastInserted, new Death(currentDeath.getCause(), details)) != null;
    }

    @Override
    public String getDeathCause(final String name) {
        return deathRecords.get(name).getCause();
    }

    @Override
    public String getDeathDetails(final String name) {
        return deathRecords.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(final String name) {
        return deathRecords.containsKey(name);
    }

}
