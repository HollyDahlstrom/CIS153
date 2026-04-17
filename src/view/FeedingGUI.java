package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import manager.FeedingManager;
import model.Cat;
import model.FeedingRecord;
import com.github.lgooddatepicker.components.DatePicker;

/**
 * FeedingGUI.java
 * CIS 153 - Data Structures - DMACC
 * 4-17-26
 *
 * Program description:
 * Graphical user interface for the Cat Feeding App. Allows users 
 * to add cats, schedule feedings, complete feedings & view feeding 
 * history.
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

public class FeedingGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
    private FeedingManager manager;

    private JTextField catNameField;
    private JButton addCatButton;

    private JComboBox<String> catDropdown;
    private JComboBox<String> timeDropdown;
    private DatePicker datePicker;
    private JButton logFeedingButton;
    private JButton completeFeedingButton;

    private JTextArea displayArea;

    private List<String> catNamesNormalized;

    public FeedingGUI() {
        // =========================
        // Initialize Data
        // =========================
        manager = new FeedingManager();
        catNamesNormalized = new ArrayList<>();

        // =========================
        // Window Setup
        // =========================
        setTitle("Holly's Cat Feeding App");
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/cat.png"));
        setIconImage(icon.getImage());
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 

        // =========================
        // UI Components - Panels
        // =========================
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); 

        JPanel addCatPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        catNameField = new JTextField(10);
        addCatButton = new JButton("Add Cat");
        
        addCatPanel.add(new JLabel("Cat Name:"));
        addCatPanel.add(catNameField);
        addCatPanel.add(addCatButton);

        JPanel feedingPanel = new JPanel();
        feedingPanel.setLayout(new BoxLayout(feedingPanel, BoxLayout.Y_AXIS));
        feedingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        catDropdown = new JComboBox<>();
        timeDropdown = new JComboBox<>();
        datePicker = new DatePicker();
        
        logFeedingButton = new JButton("Schedule Feeding"); 
        completeFeedingButton = new JButton("Mark Next Feeding as Done"); 

        // =========================
        // Time Selection Setup
        // =========================
        for (int hour = 1; hour <= 12; hour++) {
            for (int min = 0; min < 60; min += 15) {
                String timeStr = String.format("%02d:%02d", hour, min);
                timeDropdown.addItem(timeStr + " AM");
            }
        }
        
        for (int hour = 1; hour <= 12; hour++) {
            for (int min = 0; min < 60; min += 15) {
                String timeStr = String.format("%02d:%02d", hour, min);
                timeDropdown.addItem(timeStr + " PM");
            }
        }

        // =========================
        // Layout Input Row
        // =========================
        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        inputRow.add(new JLabel("Cat:"));
        inputRow.add(catDropdown);
        
        inputRow.add(new JLabel("Time:"));
        inputRow.add(timeDropdown);
        
        inputRow.add(new JLabel("Date:"));
        inputRow.add(datePicker);

        feedingPanel.add(inputRow);
        
        // =========================
        // Action Buttons Panel
        // =========================
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        buttonPanel.add(logFeedingButton);
        buttonPanel.add(completeFeedingButton);

        feedingPanel.add(buttonPanel);

        // =========================
        // Assemble Top Section
        // =========================
        topPanel.add(addCatPanel);
        topPanel.add(feedingPanel);

        // =========================
        // Output Display Area
        // =========================
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // =========================
        // Add Components to Frame
        // =========================
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // =========================
        // Event Handlers
        // =========================
        addCatButton.addActionListener(e -> {
            String name = catNameField.getText().trim();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter a cat name");
                return;
            }
            
            if (!name.matches("[a-zA-Z]+")) { 
                JOptionPane.showMessageDialog(this, "Cat name must contain letters only!");
                return;
            }

            String normalized = name.toLowerCase().replace("_", "");
            if (catNamesNormalized.contains(normalized)) {
                JOptionPane.showMessageDialog(this, "This cat already exists!");
                return;
            }

            catDropdown.addItem(name);
            catNamesNormalized.add(normalized);
            catNameField.setText("");
        });

        logFeedingButton.addActionListener(e -> {
            String catName = (String) catDropdown.getSelectedItem();
            String time = (String) timeDropdown.getSelectedItem();
            LocalDate date = datePicker.getDate();

            if (catName == null || time == null || date == null) {
                JOptionPane.showMessageDialog(this, "Fill all fields");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String dateStr = date.format(formatter);

            Cat cat = new Cat(catName);
            FeedingRecord record = new FeedingRecord(cat, time, dateStr);

            boolean duplicate =
            	    manager.getRecords().stream()
            	        .anyMatch(r -> r.getCat().getName().equalsIgnoreCase(catName)
            	                    && r.getFeedingTime().equals(time)
            	                    && r.getFeedingDate().equals(dateStr))
            	    ||
            	    manager.getUpcomingFeedings().stream()
            	        .anyMatch(r -> r.getCat().getName().equalsIgnoreCase(catName)
            	                    && r.getFeedingTime().equals(time)
            	                    && r.getFeedingDate().equals(dateStr));

            if (duplicate) {
                JOptionPane.showMessageDialog(this, "This feeding record already exists!");
                return;
            }

            manager.addUpcomingFeeding(record); 
            refreshDisplay(); 
        });
        
        completeFeedingButton.addActionListener(e -> {
            FeedingRecord done = manager.completeNextFeeding();

            if (done == null) {
                JOptionPane.showMessageDialog(this, "No upcoming feedings!");
                return;
            }

            manager.addRecord(done); 
            refreshDisplay();
        });
    }

 // =========================
 // Display Feeding Data
 // =========================
    private void refreshDisplay() {
    	
        displayArea.setText(""); 

        // =========================
        // Upcoming Feedings
        // =========================
        displayArea.append("=== Upcoming Feedings ===\n");

        String previousCatUpcoming = null; 

        for (FeedingRecord r : manager.getUpcomingFeedings()) {

            String currentCat = r.getCat().getName();

            // Spacing between cats
            if (previousCatUpcoming != null && !currentCat.equalsIgnoreCase(previousCatUpcoming)) {
                displayArea.append("\n");
            }

            displayArea.append(r.toString() + "\n");
            previousCatUpcoming = currentCat; 
        }

        // =========================
        // Feeding History (Sorted)
        // =========================
        displayArea.append("\n=== Feeding History ===\n");

        List<FeedingRecord> records = manager.getRecords();
        List<FeedingRecord> sorted = insertionSort(records);

        String previousCatHistory = null; 
        
        for (FeedingRecord r : sorted) {

            String currentCat = r.getCat().getName();

            // Add spacing between different cats
            if (previousCatHistory != null && !currentCat.equalsIgnoreCase(previousCatHistory)) {
                displayArea.append("\n");
            }

            displayArea.append(r.toString() + "\n");
            previousCatHistory = currentCat; 
        }
        
    }
    
    // Converts time string into minutes since midnight for sorting comparisons
    private int convertTimeToInt(String time) {
    	
        String[] parts = time.split("[: ]");
        
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        String ampm = parts[2];

        // Convert to 24-hour time format
        if (ampm.equalsIgnoreCase("PM") && hour != 12) hour += 12;
        if (ampm.equalsIgnoreCase("AM") && hour == 12) hour = 0;

        return hour * 60 + minute;
    }
    
    // Sort feeding records by cat name, then feeding time
    private List<FeedingRecord> insertionSort(List<FeedingRecord> records) {

    	List<FeedingRecord> sorted = new ArrayList<>(records);

    	for (int i = 1; i < sorted.size(); i++) {

    		FeedingRecord key = sorted.get(i);
    		int j = i - 1;

    		while (j >= 0 && compareRecords(sorted.get(j), key) > 0) {
    			sorted.set(j + 1, sorted.get(j));
    			j--;
    		}

    		sorted.set(j + 1, key);
    	}

    	return sorted;
    }
    
    // Compare by cat name first, then feeding time
    private int compareRecords(FeedingRecord r1, FeedingRecord r2) {

        int cmp = r1.getCat().getName().compareToIgnoreCase(r2.getCat().getName());

        if (cmp != 0) return cmp;

        return convertTimeToInt(r1.getFeedingTime())
                - convertTimeToInt(r2.getFeedingTime());
    }
}