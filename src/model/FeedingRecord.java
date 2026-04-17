package model;

/**
 * FeedingRecord.java
 * CIS 153 - Data Structures - DMACC
 * 4-17-26
 *
 * Program description:
 * Represents a feeding event for a cat, including the cat, feeding 
 * time & date. Used to store & display feeding history.
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

public class FeedingRecord {

    // Cat associated with this feeding record
    private Cat cat;

    // Time & Date of feeding record
    private String feedingTime;
    private String feedingDate;

    // Constructor
    public FeedingRecord(Cat cat, String feedingTime, String feedingDate) {
        this.cat = cat;
        this.feedingTime = feedingTime;
        this.feedingDate = feedingDate;
    }

    // Returns cat associated with this record
    public Cat getCat() {
        return cat;
    }

    // Returns feeding time
    public String getFeedingTime() {
        return feedingTime;
    }
    
    // Returns feeding date
    public String getFeedingDate() {
        return feedingDate;
    }

    @Override
    public String toString() {
        return cat.getName() + " fed at " + feedingTime + " on " + feedingDate;
    }
}