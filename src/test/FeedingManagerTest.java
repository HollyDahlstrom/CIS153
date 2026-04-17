package test;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import manager.FeedingManager;
import model.Cat;
import model.FeedingRecord;

/**
 * FeedingManagerTest.java
 * CIS 153 - Data Structures - DMACC
 * 4-17-26
 *
 * Program description:
 * Unit tests for the FeedingManager class. Tests linked list 
 * operations, queue behavior, duplicate detection logic & 
 * insertion sort functionality.
 *
 * @author Holly Dahlstrom
 * @version 1.0
 * @since 1.0
 *
 * OS: Windows 10
 * IDE: Eclipse 2024-12
 * Copyright: This is my own original work
 * based on specifications issued by our instructor
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or
 * unmodified, nor used generative AI as a final draft.
 * I have not given other fellow student(s) access to my program.
 */

public class FeedingManagerTest {

    private FeedingManager manager;

    @Before
    public void setUp() {
        manager = new FeedingManager();
    }

    @Test
    public void testAddRecordLinkedList() {
        FeedingRecord record = new FeedingRecord(
                new Cat("Milo"),
                "08:00 AM",
                "04-13-2026"
        );

        manager.addRecord(record);

        assertEquals(1, manager.getRecords().size());
    }

    @Test
    public void testQueueAddUpcomingFeeding() {
        FeedingRecord record = new FeedingRecord(
                new Cat("Luna"),
                "09:00 AM",
                "04-13-2026"
        );

        manager.addUpcomingFeeding(record);

        assertEquals(1, manager.getUpcomingFeedings().size());
    }

    @Test
    public void testCompleteNextFeedingRemovesFromQueue() {
        FeedingRecord record = new FeedingRecord(
                new Cat("Fluffy"),
                "10:00 AM",
                "04-13-2026"
        );

        manager.addUpcomingFeeding(record);

        FeedingRecord completed = manager.completeNextFeeding();

        assertNotNull(completed);
        assertEquals(0, manager.getUpcomingFeedings().size());
    }

    @Test
    public void testCompleteNextFeedingWhenEmpty() {
        FeedingRecord result = manager.completeNextFeeding();

        assertNull(result);
    }

    @Test
    public void testEmptyManagerStartsEmpty() {
        assertEquals(0, manager.getRecords().size());
        assertEquals(0, manager.getUpcomingFeedings().size());
    }

    @Test
    public void testDuplicateDetectionLogic() {
        FeedingRecord r1 = new FeedingRecord(
                new Cat("Milo"),
                "08:00 AM",
                "04-13-2026"
        );

        FeedingRecord r2 = new FeedingRecord(
                new Cat("Milo"),
                "08:00 AM",
                "04-13-2026"
        );

        manager.addUpcomingFeeding(r1);

        boolean duplicate = manager.getUpcomingFeedings().stream()
                .anyMatch(r ->
                        r.getCat().getName().equalsIgnoreCase(r2.getCat().getName())
                        && r.getFeedingTime().equals(r2.getFeedingTime())
                        && r.getFeedingDate().equals(r2.getFeedingDate())
                );

        assertTrue(duplicate);
    }
    
    @Test
    public void testLinkedListMultipleRecordsOrder() {
        FeedingRecord r1 = new FeedingRecord(new Cat("Milo"), "08:00 AM", "04-13-2026");
        FeedingRecord r2 = new FeedingRecord(new Cat("Luna"), "09:00 AM", "04-13-2026");

        manager.addRecord(r1);
        manager.addRecord(r2);

        List<FeedingRecord> records = manager.getRecords();

        assertEquals("Milo", records.get(0).getCat().getName());
        assertEquals("Luna", records.get(1).getCat().getName());
    }
    
    @Test
    public void testQueueFIFOOrder() {
        FeedingRecord r1 = new FeedingRecord(new Cat("Milo"), "08:00 AM", "04-13-2026");
        FeedingRecord r2 = new FeedingRecord(new Cat("Luna"), "09:00 AM", "04-13-2026");

        manager.addUpcomingFeeding(r1);
        manager.addUpcomingFeeding(r2);

        FeedingRecord first = manager.completeNextFeeding();
        FeedingRecord second = manager.completeNextFeeding();

        assertEquals("Milo", first.getCat().getName());
        assertEquals("Luna", second.getCat().getName());
    }
    
    // Sorting logic mirrors the insertionSort method in FeedingGUI
    // since the method is private & cannot be accessed directly
    @Test
    public void testInsertionSortByName() {
        FeedingRecord r1 = new FeedingRecord(new Cat("Zoe"), "09:00 AM", "04-13-2026");
        FeedingRecord r2 = new FeedingRecord(new Cat("Anna"), "08:00 AM", "04-13-2026");

        manager.addRecord(r1);
        manager.addRecord(r2);

        // Manual sort used to mirror GUI insertion sort logic for validation
        List<FeedingRecord> sorted = manager.getRecords();

        // Simulate same sort logic used in GUI
        List<FeedingRecord> result = new ArrayList<>(sorted);

        for (int i = 1; i < result.size(); i++) {
            FeedingRecord key = result.get(i);
            int j = i - 1;

            while (j >= 0 &&
                result.get(j).getCat().getName().compareToIgnoreCase(key.getCat().getName()) > 0) {
                result.set(j + 1, result.get(j));
                j--;
            }

            result.set(j + 1, key);
        }

        assertEquals("Anna", result.get(0).getCat().getName());
        assertEquals("Zoe", result.get(1).getCat().getName());
    }
    
    @Test
    public void testLinkedListEmptyReturnsEmptyList() {
        assertTrue(manager.getRecords().isEmpty());
    }
}