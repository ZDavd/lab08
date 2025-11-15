package it.unibo.deathnote;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.DeathNoteImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

class TestDeathNote {

    private static final String TEST_NAME = "Test";
    private static final String TEST_NAME_1 = "Test1";
    private static final String KART_ACCIDENT = "Kart Accident";
    private static final int EXCEEDED_DEATH_TIME = 100;
    private static final int EXCEEDED_DETAILS_TIME = 6100;
    private DeathNote deathBook;

    @BeforeEach
    void setUp() {
        this.deathBook = new DeathNoteImpl();
    }

    @Test
    void testInvalidRules() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                deathBook.getRule(0);
            }
        });
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
        assertFalse(exception.getMessage().isBlank());

        // Negative rule test
        final IllegalArgumentException exceptionNegRule = assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                deathBook.getRule(-1);
            }
        });
        assertNotNull(exceptionNegRule.getMessage());
        assertFalse(exceptionNegRule.getMessage().isEmpty());
        assertFalse(exceptionNegRule.getMessage().isBlank());
    }

    @Test
    void testAllRulesAreValid() {
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            final String rule = deathBook.getRule(i);
            assertNotNull(rule);
            assertFalse(rule.isEmpty());
            assertFalse(rule.isBlank());
        }
    }

    @Test
    void testNameWriting() {
        assertFalse(deathBook.isNameWritten(TEST_NAME));
        deathBook.writeName(TEST_NAME);
        assertTrue(deathBook.isNameWritten(TEST_NAME));
        assertFalse(deathBook.isNameWritten(TEST_NAME_1));
        assertFalse(deathBook.isNameWritten(""));
    }

    @Test
    void testDeathCause() throws InterruptedException {
        assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                deathBook.writeDeathCause("Drowned");
            }
        }
        );
        deathBook.writeName(TEST_NAME);
        assertEquals(deathBook.getDeathCause(TEST_NAME), "Heart attack");
        deathBook.writeName(TEST_NAME_1);
        assertTrue(deathBook.writeDeathCause(KART_ACCIDENT));
        assertEquals(deathBook.getDeathCause(TEST_NAME_1), KART_ACCIDENT);
        Thread.sleep(EXCEEDED_DEATH_TIME);
        assertFalse(deathBook.writeDeathCause("Suicide"));
        assertEquals(deathBook.getDeathCause(TEST_NAME_1), KART_ACCIDENT);
    }

    @Test
    void testDeathDetails() throws InterruptedException {
        assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                deathBook.writeDetails("Fell from a building");
            }
        }
        );
        deathBook.writeName(TEST_NAME);
        assertEquals(deathBook.getDeathDetails(TEST_NAME), "");
        assertTrue(deathBook.writeDetails("Ran for too long"));
        assertEquals(deathBook.getDeathDetails(TEST_NAME), "Ran for too long");
        deathBook.writeName(TEST_NAME_1);
        Thread.sleep(EXCEEDED_DETAILS_TIME);
        assertFalse(deathBook.writeDetails("Fell from a building"));
        assertEquals(deathBook.getDeathDetails(TEST_NAME_1), "");
    }
}
