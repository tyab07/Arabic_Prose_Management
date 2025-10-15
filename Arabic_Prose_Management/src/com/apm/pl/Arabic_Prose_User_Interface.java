package com.apm.pl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.apm.bll.IBussinessLayerFasade;


public class Arabic_Prose_User_Interface extends JFrame  {

    // Global Components
    private JTextArea outputArea;
    private IBussinessLayerFasade blf;
    
    // Book Fields (title, author_id, era)
    private JTextField bookTitleField = new JTextField(20);
    private JTextField bookAuthorIdField = new JTextField(20); // Changed to author ID
    private JTextField bookEraField = new JTextField(20);

    // Author Fields (author name, biography)
    private JTextField authorNameField = new JTextField(20);
    private JTextArea authorBiographyArea = new JTextArea(4, 20);
    
    // Sentence Fields (book name, sentence number, text, text diacritized, translation, notes, keyword)
    private JTextField sentenceBookNameField = new JTextField(20);
    private JTextField sentenceNumberField = new JTextField(20);
    private JTextArea sentenceTextArea = new JTextArea(3, 20);
    private JTextArea sentenceDiacritizedArea = new JTextArea(3, 20);
    private JTextArea sentenceTranslationArea = new JTextArea(3, 20);
    private JTextArea sentenceNotesArea = new JTextArea(3, 20);
    private JTextField sentenceKeywordField = new JTextField(20); // Added for search

    public Arabic_Prose_User_Interface(IBussinessLayerFasade blf) {
    	
    	this.blf = blf;
        // Frame setup
        setTitle("Arabic Prose Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 800);
        setLocationRelativeTo(null);

        // Set Arabic-supporting font for all components
        Font arabicFont = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("TextField.font", arabicFont);
        UIManager.put("TextArea.font", arabicFont);
        UIManager.put("Label.font", arabicFont);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12));

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.addTab("Book", createBookTab());
        tabbedPane.addTab("Author", createAuthorTab());
        tabbedPane.addTab("Sentence", createSentenceTab());

        // Output Area
        outputArea = new JTextArea("GUI Initialized. Actions will be logged here.", 15, 30);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output / Results"));

        // Layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, scrollPane);
        splitPane.setResizeWeight(0.65);

        mainPanel.add(splitPane, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
//////////////////////////////////////////////Working/////////////////////////////////////////////////////////////////////////////////////
    private JPanel createTabContent(JPanel inputPanel, String tabName) {
        ActionListener placeholderListener = e -> {
            String message = "\n> Action: " + e.getActionCommand() + " | Tab: " + tabName + " | Status: ";
            boolean hasInput = false;
            String action = e.getActionCommand();
            String output = "";
            // Check for input based on tab
            switch (tabName) {
                case "Book":
                    hasInput = !bookTitleField.getText().trim().isEmpty() && 
                              !bookAuthorIdField.getText().trim().isEmpty();
                    if(hasInput)
                    {
                        switch(action) {
                        case "Add":
                        	output = "" + blf.createBook(bookTitleField.getText(), Integer.parseInt(bookAuthorIdField.getText()), bookEraField.getText());
                        	break;
                        case "Retrieve":
                        	output = "" + blf.retrieveBook(bookTitleField.getText()).toString();
                        	break;
                        case "Update":
                        	output = ""+ blf.updateBook(bookTitleField.getText(),bookEraField.getText());
                        	break;
                        case "Delete":
                        	output = ""+blf.deleteBook(bookTitleField.getText());
                        }
                        
                    }
                    break;
                case "Author":
                    hasInput = !authorNameField.getText().trim().isEmpty();
                    break;
                case "Sentence":
                    hasInput = !sentenceBookNameField.getText().trim().isEmpty() || 
                              !sentenceNumberField.getText().trim().isEmpty() || 
                              !sentenceKeywordField.getText().trim().isEmpty();
                    break;
            }
            System.out.println(message + hasInput );
            message += hasInput ? "GUI event fired Output = "+ output : "Failed - Missing required input";
            outputArea.append(message);
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        };

        JPanel tabPanel = new JPanel(new BorderLayout(15, 15));
        JPanel inputWrapper = new JPanel(new BorderLayout());
        inputWrapper.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        inputWrapper.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel(placeholderListener, tabName);
        tabPanel.add(inputWrapper, BorderLayout.NORTH);
        tabPanel.add(buttonPanel, BorderLayout.CENTER);

        return tabPanel;
    }

    private JPanel createButtonPanel(ActionListener listener, String tabName) {
        JPanel panel = new JPanel(new GridLayout(1, 4, 15, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton addButton = createActionButton("Add", new Color(46, 139, 87), "Add", listener);
        JButton retrieveButton = createActionButton(
            tabName.equals("Sentence") ? "Search" : "Retrieve", 
            new Color(30, 144, 255), "Retrieve", listener
        );
        JButton updateButton = createActionButton("Update", new Color(255, 165, 0), "Update", listener);
        JButton deleteButton = createActionButton("Delete", new Color(220, 20, 60), "Delete", listener);

        panel.add(addButton);
        panel.add(retrieveButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapper.add(panel);
        return wrapper;
    }

    private JButton createActionButton(String label, Color color, String command, ActionListener listener) {
        JButton button = new JButton(label);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setActionCommand(command);
        button.addActionListener(listener);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    private JPanel createBookTab() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Book Title:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; formPanel.add(bookTitleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; formPanel.add(new JLabel("Author ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0; formPanel.add(bookAuthorIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0; formPanel.add(new JLabel("Era:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0; formPanel.add(bookEraField, gbc);

        gbc.weightx = 0;
        return createTabContent(formPanel, "Book");
    }

    private JPanel createAuthorTab() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Author Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; formPanel.add(authorNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; formPanel.add(new JLabel("Biography:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0; 
        gbc.gridheight = 4;
        formPanel.add(new JScrollPane(authorBiographyArea), gbc);
        gbc.gridheight = 1;

        gbc.gridx = 0; gbc.gridy = 5; gbc.weighty = 1.0; formPanel.add(new JLabel(""), gbc);

        gbc.weightx = 0;
        gbc.weighty = 0;
        return createTabContent(formPanel, "Author");
    }

    private JPanel createSentenceTab() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; formPanel.add(new JLabel("Book Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = row - 1; gbc.weightx = 1.0; formPanel.add(sentenceBookNameField, gbc);

        gbc.gridx = 0; gbc.gridy = row++; formPanel.add(new JLabel("Sentence Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = row - 1; gbc.weightx = 1.0; formPanel.add(sentenceNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = row++; formPanel.add(new JLabel("Text:"), gbc);
        gbc.gridx = 1; gbc.gridy = row - 1; gbc.weightx = 1.0; 
        gbc.gridheight = 2;
        formPanel.add(new JScrollPane(sentenceTextArea), gbc);
        gbc.gridheight = 1;
        row += 1;

        gbc.gridx = 0; gbc.gridy = row++; formPanel.add(new JLabel("Text Diacritized:"), gbc);
        gbc.gridx = 1; gbc.gridy = row - 1; gbc.weightx = 1.0; 
        gbc.gridheight = 2;
        formPanel.add(new JScrollPane(sentenceDiacritizedArea), gbc);
        gbc.gridheight = 1;
        row += 1;

        gbc.gridx = 0; gbc.gridy = row++; formPanel.add(new JLabel("Translation:"), gbc);
        gbc.gridx = 1; gbc.gridy = row - 1; gbc.weightx = 1.0; 
        gbc.gridheight = 2;
        formPanel.add(new JScrollPane(sentenceTranslationArea), gbc);
        gbc.gridheight = 1;
        row += 1;

        gbc.gridx = 0; gbc.gridy = row++; formPanel.add(new JLabel("Notes:"), gbc);
        gbc.gridx = 1; gbc.gridy = row - 1; gbc.weightx = 1.0; 
        gbc.gridheight = 2;
        formPanel.add(new JScrollPane(sentenceNotesArea), gbc);
        gbc.gridheight = 1;
        row += 1;

        gbc.gridx = 0; gbc.gridy = row++; formPanel.add(new JLabel("Keyword for Search:"), gbc);
        gbc.gridx = 1; gbc.gridy = row - 1; gbc.weightx = 1.0; 
        formPanel.add(sentenceKeywordField, gbc);

        gbc.weightx = 0;
        return createTabContent(formPanel, "Sentence");
    }
    
    

  
}