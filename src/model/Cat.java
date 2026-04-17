/**
 * Cat.java
 * CIS 153 - Data Structures - DMACC
 * 4-17-26
 *
 * Program description:
 * Represents a cat profile in the Cat Feeding App. Stores the cat's name
 * and allows it to be used in feeding records.
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

public class Cat {

    // Stores cat's name
    private String name;

    // Creates a Cat object with given name
    public Cat(String name) {
        this.name = name;
    }

    // Returns cat's name
    public String getName() {
        return name;
    }
}