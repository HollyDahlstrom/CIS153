/**
 * MainTest.java
 * CIS 153 - Data Structures - DMACC
 * 3-5-26
 * 
 * Program description:
 * Driver class to test the Linked List implementation of feeding records.
 * Creates Cat objects, FeedingRecords, and stores them in FeedingManager.
 * Displays feeding records to verify Linked List functionality.
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

package driver;

import model.Cat;
import model.FeedingRecord;
import manager.FeedingManager;

public class MainTest {

    public static void main(String[] args) {

        // Create some cats
        Cat cat1 = new Cat("Milo");
        Cat cat2 = new Cat("Spot");

        // Create feeding records for each cat
        FeedingRecord r1 = new FeedingRecord(cat1, "8:00 AM");
        FeedingRecord r2 = new FeedingRecord(cat2, "8:01 AM");

        // Initialize FeedingManager
        FeedingManager manager = new FeedingManager();

        // Add feeding records to linked list
        manager.addRecord(r1);
        manager.addRecord(r2);

        // Display feeding records
        System.out.println("Feeding Records:");
        manager.displayRecords();
    }
}