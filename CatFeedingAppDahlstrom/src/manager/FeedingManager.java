/**
 * FeedingManager.java
 * CIS 153 - Data Structures - DMACC
 * 3-5-26
 * 
 * Program description:
 * Manages feeding records for cats using a custom Linked List.
 * Allows adding feeding records and displaying all records.
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

package manager;

import model.FeedingRecord;
import model.Node;

public class FeedingManager {

    // Head of the linked list
    private Node head;

    // Add a new feeding record to the end of the list
    public void addRecord(FeedingRecord record) {
        Node newNode = new Node(record);

        // If list is empty, new node becomes head
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            // Traverse to the last node
            while (current.next != null) {
                current = current.next;
            }

            // Append new node
            current.next = newNode;
        }
    }

    // Display all feeding records
    public void displayRecords() {
        Node current = head;

        while (current != null) {
            System.out.println(current.record);
            current = current.next;
        }
    }
}