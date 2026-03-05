/**
 * Node.java
 * CIS 153 - Data Structures - DMACC
 * 3-5-26
 * 
 * Program description:
 * Node class used to build a custom Linked List for feeding records.
 * Each node contains a FeedingRecord object and a reference to the next node.
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
    public FeedingRecord record;

    // Reference to the next node in the linked list
    public Node next;

    // Constructor
    public Node(FeedingRecord record) {
        this.record = record;
        this.next = null;
    }
}