package manager;

/**
 * FeedingManager.java
 * CIS 153 - Data Structures - DMACC
 * 4-17-26
 * 
 * Program description:
 * Manages feeding records using a custom linked list for history
 * & a queue for upcoming feedings. Supports adding, completing,
 * retrieving & displaying feeding records.
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

import model.FeedingRecord;
import model.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class FeedingManager {

    // Head of the linked list
    private Node head;
    
    // Queue storing upcoming feedings
    private Queue<FeedingRecord> upcomingFeedings = new LinkedList<>();

    // Adds a new feeding record to the end of the linked list
    public void addRecord(FeedingRecord record) {
        Node newNode = new Node(record);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(newNode);
        }
    }

    // Displays all feeding records in the linked list
    public void displayRecords() {
        Node current = head;

        while (current != null) {
            System.out.println(current.getRecord());
            current = current.getNext();
        }
    }

    // Returns all feeding records as a List
    public List<FeedingRecord> getRecords() {
        List<FeedingRecord> list = new ArrayList<>();
        Node current = head;

        while (current != null) {
            list.add(current.getRecord());
            current = current.getNext();
        }

        return list;
    }
    
    // -------------------------
    // QUEUE (Upcoming Feedings)
    // -------------------------

    // Adds a feeding record to the upcoming feedings queue
    public void addUpcomingFeeding(FeedingRecord record) {
        upcomingFeedings.offer(record);
    }

    // Removes and returns the next feeding in FIFO order
    public FeedingRecord completeNextFeeding() {
        return upcomingFeedings.poll(); 
    }

    // Returns the queue of upcoming feedings
    public Queue<FeedingRecord> getUpcomingFeedings() {
        return upcomingFeedings;
    }
}