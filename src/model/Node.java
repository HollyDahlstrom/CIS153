/**
 * Node.java
 * CIS 153 - Data Structures - DMACC
 * 4-17-26
 * 
 * Program description:
 * Node class used to build a custom linked list for feeding records.
 * Each node stores a FeedingRecord & a reference to the next node.
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

public class Node {

    // The feeding record stored in this node
    private FeedingRecord record;

    // Reference to the next node in the linked list
    private Node next;

    // Constructor initializes node with a feeding record
    public Node(FeedingRecord record) {
        this.record = record;
        this.next = null;
    }
    
    // Returns the feeding record stored in this node
    public FeedingRecord getRecord() {
        return record;
    }

    // Returns the next node in the list
    public Node getNext() {
        return next;
    }

    // Sets the reference to the next node
    public void setNext(Node next) {
        this.next = next;
    }
}