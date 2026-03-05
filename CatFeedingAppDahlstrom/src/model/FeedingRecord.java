/**
 * FeedingRecord.java
 * CIS 153 - Data Structures - DMACC
 * 3-5-26
 * 
 * Program description:
 * Represents a feeding event for a specific cat, including
 * the cat being fed and the time of feeding.
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

package model;

public class FeedingRecord {

    // Cat associated with this feeding
    private Cat cat;

    // Time of feeding (simple String for Sprint 1)
    private String feedingTime;

    // Constructor
    public FeedingRecord(Cat cat, String feedingTime) {
        this.cat = cat;
        this.feedingTime = feedingTime;
    }

    // Getter for Cat
    public Cat getCat() {
        return cat;
    }

    // Getter for feeding time
    public String getFeedingTime() {
        return feedingTime;
    }

    // ToString method to display the feeding record
    @Override
    public String toString() {
        return cat.getName() + " fed at " + feedingTime;
    }
}