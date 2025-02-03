package musicalticketbooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;

public class MusicalTicketBooking extends JFrame {
    // Main components
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Map<String, MusicalShow> musicalShows;
    private MusicalShow selectedShow;
    private String selectedDate;
    private String selectedTime;
    private Map<String, Integer> ticketCounts;
    private double totalAmount;
    
    // Constants
    private static final Color PINK_COLOR = new Color(255, 64, 129);
    private static final Color PRIMARY_COLOR = new Color(255, 64, 129);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 32);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);

    // Constructor
    public MusicalTicketBooking() {
        super("London Musical Tickets");
        musicalShows = initializeMusicalShows();
        ticketCounts = new HashMap<>();
        ticketCounts.put("Adult", 0);
        ticketCounts.put("Child", 0);
        ticketCounts.put("Senior", 0);
        createAndShowGUI();
    }

    private void updateReceipt() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static class MusicalShow {
        String name;
        String description;
        String duration;
        double price;
        int rating;
        String theatre;
        String genre;
        String ageRating;

        public MusicalShow(String name, String description, String duration, 
                         double price, int rating, String theatre, String genre, String ageRating) {
            this.name = name;
            this.description = description;
            this.duration = duration;
            this.price = price;
            this.rating = rating;
            this.theatre = theatre;
            this.genre = genre;
            this.ageRating = ageRating;
        }
    }
    
    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(120, 35));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        return comboBox;
    }
    
    private Map<String, MusicalShow> initializeMusicalShows() {
    Map<String, MusicalShow> shows = new HashMap<>();

    shows.put("The Lion King", new MusicalShow(
        "The Lion King",
        "A timeless tale of a lion prince finding his destiny.",
        "2h 30m",
        45.0,
        3,
        "Lyceum Theatre",
        "Family",
        "All Ages"
    ));

    shows.put("Les Misérables", new MusicalShow(
        "Les Misérables",
        "A story of love, passion, and sacrifice during the French Revolution.",
        "3h",
        55.0,
        4,
        "Sondheim Theatre",
        "Drama",
        "Teen"
    ));

    shows.put("Phantom of the Opera", new MusicalShow(
        "Phantom of the Opera",
        "A haunting love story in the depths of an opera house.",
        "2h 45m",
        50.0,
        2,
        "Her Majesty's Theatre",
        "Drama",
        "Teen"
    ));

    shows.put("Mamma Mia!", new MusicalShow(
        "Mamma Mia!",
        "An uplifting musical based on ABBA's greatest hits.",
        "2h 20m",
        40.0,
        4,
        "Novello Theatre",
        "Musical",
        "All Ages"
    ));

    shows.put("Wicked", new MusicalShow(
        "Wicked",
        "The untold story of the witches of Oz.",
        "2h 30m",
        60.0,
        3,
        "Apollo Victoria Theatre",
        "Musical",
        "Family"
    ));

    shows.put("Hamilton", new MusicalShow(
        "Hamilton",
        "The revolutionary musical about Alexander Hamilton.",
        "2h 45m",
        70.0,
        5,
        "Victoria Palace Theatre",
        "Drama",
        "Adult"
    ));

    return shows;
}

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new MusicalTicketBooking());
    }

    private void createAndShowGUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200, 900);
    setLayout(new BorderLayout());

    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);

    // Create initial pages
    JPanel musicalPage = createMusicalPage();
    JPanel schedulePage = createSchedulePage();

    // Add initial pages to card layout
    mainPanel.add(musicalPage, "Musicals");
    mainPanel.add(schedulePage, "Schedule");

    // Header Panel
    JPanel headerPanel = createHeaderPanel();
    add(headerPanel, BorderLayout.NORTH);

    // Main Panel
    add(mainPanel, BorderLayout.CENTER);
    setLocationRelativeTo(null);
    setVisible(true);
}

    // Add these methods to the MusicalTicketBooking class

private JPanel createHeaderPanel() {
    JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
    headerPanel.setBackground(BACKGROUND_COLOR);
    headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    // Top section
    JPanel topSection = new JPanel(new BorderLayout(20, 0));
    topSection.setBackground(BACKGROUND_COLOR);

    // Title and Exit button section
    JPanel titleExitPanel = new JPanel(new BorderLayout());
    titleExitPanel.setBackground(BACKGROUND_COLOR);

    JLabel titleLabel = new JLabel("London Musical Tickets");
    titleLabel.setFont(TITLE_FONT);
    titleLabel.setForeground(PRIMARY_COLOR);
    titleExitPanel.add(titleLabel, BorderLayout.WEST);

    JButton exitButton = new JButton("Exit");
    exitButton.setPreferredSize(new Dimension(90, 35));
    exitButton.setBackground(PINK_COLOR);
    exitButton.setForeground(Color.WHITE);
    exitButton.setFont(new Font("Arial", Font.BOLD, 16));
    exitButton.setFocusPainted(false);
    exitButton.setBorderPainted(false);
    exitButton.addActionListener(e -> System.exit(0));
    titleExitPanel.add(exitButton, BorderLayout.EAST);

    topSection.add(titleExitPanel, BorderLayout.NORTH);

    // Search and filters section
    JPanel searchFilterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
searchFilterPanel.setBackground(BACKGROUND_COLOR);

    // Search field
    JTextField searchField = new JTextField(20) {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString("Search musicals...", 10, getHeight()/2 + 5);
            g2.dispose();
        }
    }
};
searchField.setPreferredSize(new Dimension(200, 35));
    
    searchFilterPanel.add(searchField);
    // Filter dropdowns
    String[] genres = {"All Genres", "Family Musical", "Historical Drama", "Fantasy Musical", "Comedy Musical"};
    String[] ratings = {"All Ratings", "5 Stars", "4+ Stars", "3+ Stars"};
    String[] ages = {"All Ages", "Family", "Teen", "Adult"};

    JComboBox<String> genreFilter = createStyledComboBox(genres);
    JComboBox<String> ratingFilter = createStyledComboBox(ratings);
    JComboBox<String> ageFilter = createStyledComboBox(ages);

    Component[] filters = {genreFilter, ratingFilter, ageFilter};
    for (Component filter : filters) {
        ((JComboBox) filter).setPreferredSize(new Dimension(120, 35));
        searchFilterPanel.add(filter);
    }

    topSection.add(searchFilterPanel, BorderLayout.CENTER);

    searchField.addActionListener(e -> filterMusicals(
    searchField.getText(),
    (String) genreFilter.getSelectedItem(),
    (String) ratingFilter.getSelectedItem(),
    (String) ageFilter.getSelectedItem()
));

genreFilter.addActionListener(e -> filterMusicals(
    searchField.getText(),
    (String) genreFilter.getSelectedItem(),
    (String) ratingFilter.getSelectedItem(),
    (String) ageFilter.getSelectedItem()
));

ratingFilter.addActionListener(e -> filterMusicals(
    searchField.getText(),
    (String) genreFilter.getSelectedItem(),
    (String) ratingFilter.getSelectedItem(),
    (String) ageFilter.getSelectedItem()
));

ageFilter.addActionListener(e -> filterMusicals(
    searchField.getText(),
    (String) genreFilter.getSelectedItem(),
    (String) ratingFilter.getSelectedItem(),
    (String) ageFilter.getSelectedItem()
));


    // Navigation section
    JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    navPanel.setBackground(Color.WHITE);
    
    String[] sections = {"Musicals", "Schedule", "Ticket Type", "Receipt"};
    for (String section : sections) {
        JButton navButton = new JButton(section);
        navButton.setPreferredSize(new Dimension(150, 40));
        navButton.setBackground(new Color(240, 240, 240));
        navButton.setForeground(Color.BLACK);
        navButton.setFocusPainted(false);
        navButton.setBorderPainted(false);
        navButton.setOpaque(true);

        // Add hover effect
        navButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                navButton.setBackground(PRIMARY_COLOR);
                navButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                navButton.setBackground(new Color(240, 240, 240));
                navButton.setForeground(Color.BLACK);
            }
        });

        navButton.addActionListener(e -> {
    if (section.equals("TicketType") && selectedShow == null) {
        JOptionPane.showMessageDialog(this,
            "Please select a musical show before proceeding to ticket selection.",
            "No Show Selected",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    cardLayout.show(mainPanel, section);
});

    }

    headerPanel.add(topSection, BorderLayout.NORTH);
    headerPanel.add(navPanel, BorderLayout.SOUTH);

    
 
// Add section navigation bar at the bottom of the header panel
JPanel sectionBar = createModernSectionBar();
headerPanel.add(sectionBar, BorderLayout.SOUTH);

return headerPanel; 
}



private JPanel createModernSectionBar() {
    JPanel sectionBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    sectionBar.setBackground(Color.WHITE);
    
    String[] sections = {"Musicals", "Schedule", "Ticket Type", "Receipt"};
    for (String section : sections) {
        JButton sectionButton = new JButton(section);
        sectionButton.setPreferredSize(new Dimension(150, 40));
        sectionButton.setFont(new Font("Arial", Font.BOLD, 14));
        sectionButton.setBackground(new Color(240, 240, 240));
        sectionButton.setForeground(Color.BLACK);
        sectionButton.setBorderPainted(false);
        sectionButton.setFocusPainted(false);

        // Add hover effect
        sectionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sectionButton.setBackground(new Color(255, 64, 129)); // Pink color
                sectionButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sectionButton.setBackground(new Color(240, 240, 240));
                sectionButton.setForeground(Color.BLACK);
            }
        });

        // Add navigation functionality
        sectionButton.addActionListener(e -> {
            if (section.equals("Ticket Type") && selectedShow == null) {
                JOptionPane.showMessageDialog(this,
                    "Please select a musical show before proceeding.",
                    "No Show Selected",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            cardLayout.show(mainPanel, section);
        });

        sectionBar.add(sectionButton);
    }

    return sectionBar;
}

private JPanel createMusicalPage() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(Color.WHITE);

    // Create grid for musicals
    JPanel showsGrid = new JPanel(new GridLayout(0, 3, 20, 20));
    showsGrid.setBackground(Color.WHITE);
    showsGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Add musical shows to grid
    for (MusicalShow show : musicalShows.values()) {
        showsGrid.add(createMusicalPanel(show));
    }

    // Add scrolling
    JScrollPane scrollPane = new JScrollPane(showsGrid);
    scrollPane.setBorder(null);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    panel.add(scrollPane, BorderLayout.CENTER);

    return panel;
}

private JPanel createMusicalPanel(MusicalShow show) {
    JPanel panel = new JPanel(new BorderLayout(0, 10));
    panel.setBackground(Color.WHITE);
    panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200)),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));

    // Image placeholder
    try {
    String imagePath = "/images/" + show.name.toLowerCase()
        .replace(" ", "-")
        .replace("é", "e") // Handle special characters
        .replace("!", "") 
        .replace("'", "") + ".jpg";
    
    // Debug line to check path
    System.out.println("Attempting to load image: " + imagePath);
    
    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
    if (originalIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
        throw new Exception("Image failed to load");
    }
    Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);
    JLabel imageLabel = new JLabel(scaledIcon);
    panel.add(imageLabel, BorderLayout.NORTH);
} catch (Exception e) {
    System.err.println("Error loading image: " + e.getMessage());
    createPlaceholderImage(panel);
} 
    
    // Show details panel
    JPanel detailsPanel = new JPanel();
    detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
    detailsPanel.setBackground(Color.WHITE);

    // Title
    JLabel titleLabel = new JLabel(show.name);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    detailsPanel.add(titleLabel);
    detailsPanel.add(Box.createVerticalStrut(5));

    // Description
    JLabel descLabel = new JLabel("<html><body style='width: 200px'>" + 
        show.description + "</body></html>");
    descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    detailsPanel.add(descLabel);
    detailsPanel.add(Box.createVerticalStrut(5));

    // Duration and Theatre
    JLabel durationLabel = new JLabel(show.duration + " | " + show.theatre);
    durationLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    durationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    detailsPanel.add(durationLabel);
    detailsPanel.add(Box.createVerticalStrut(5));

    // Price
    JLabel priceLabel = new JLabel(String.format("£%.2f", show.price));
    priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
    priceLabel.setForeground(PRIMARY_COLOR);
    priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    detailsPanel.add(priceLabel);
    detailsPanel.add(Box.createVerticalStrut(5));

    // Rating
    JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
    ratingPanel.setBackground(Color.WHITE);
    for (int i = 0; i < show.rating; i++) {
        JLabel star = new JLabel("★");
        star.setForeground(new Color(255, 215, 0));
        ratingPanel.add(star);
    }
    ratingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    detailsPanel.add(ratingPanel);
    detailsPanel.add(Box.createVerticalStrut(10));

    // Book Now button
    JButton bookButton = new JButton("Book Now");
    bookButton.setBackground(PRIMARY_COLOR);
    bookButton.setForeground(Color.WHITE);
    bookButton.setFocusPainted(false);
    bookButton.setBorderPainted(false);
    bookButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    bookButton.addActionListener(e -> {
    selectedShow = show; // Set the selected show
    selectedShowLabel.setText(show.name); // Update label
    cardLayout.show(mainPanel, "Schedule");
});
    
    
    detailsPanel.add(bookButton);

    panel.add(detailsPanel, BorderLayout.SOUTH);

    return panel;
}
private void createPlaceholderImage(JPanel panel) {
    JPanel placeholderPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(240, 240, 240));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GRAY);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            String text = "Show Image";
            FontMetrics fm = g.getFontMetrics();
            int textX = (getWidth() - fm.stringWidth(text)) / 2;
            int textY = (getHeight() + fm.getHeight()) / 2;
            g.drawString(text, textX, textY);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 200);
        }
    };
    panel.add(placeholderPanel, BorderLayout.NORTH);
}
private void filterMusicals(String searchText, String genre, String rating, String age) {
    JPanel showsGrid = (JPanel) ((JScrollPane) ((JPanel) mainPanel.getComponent(0))
        .getComponent(0)).getViewport().getView();
    showsGrid.removeAll();

    for (MusicalShow show : musicalShows.values()) {
        // Check if show matches search text
        boolean matchesSearch = searchText.isEmpty() || 
            show.name.toLowerCase().contains(searchText.toLowerCase()) ||
            show.description.toLowerCase().contains(searchText.toLowerCase());

        // Check if show matches selected genre
        boolean matchesGenre = genre.equals("All Genres") || 
            show.genre.equals(genre);

        // Check if show matches selected rating
        boolean matchesRating;
        if (rating.equals("All Ratings")) {
            matchesRating = true;
        } else if (rating.equals("5 Stars")) {
            matchesRating = show.rating == 5;
        } else {
            int minRating = Integer.parseInt(rating.split("\\+")[0]);
            matchesRating = show.rating >= minRating;
        }

        // Check if show matches selected age rating
        boolean matchesAge = age.equals("All Ages") || 
            show.ageRating.equals(age);

        // Add show if it matches all criteria
        if (matchesSearch && matchesGenre && matchesRating && matchesAge) {
            showsGrid.add(createMusicalPanel(show));
        }
    }

    showsGrid.revalidate();
    showsGrid.repaint();
}

// Add these fields at the top of the class with other fields
private JLabel selectedShowLabel;
private JLabel selectedDateTimeLabel;

// Add this method after createMusicalPanel() method
private JPanel createSchedulePage() {
    JPanel panel = new JPanel(new BorderLayout(20, 0));
    panel.setBackground(Color.WHITE);
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Left panel - Show details
    JPanel leftPanel = new JPanel(new BorderLayout(0, 10));
    leftPanel.setBackground(Color.WHITE);
    leftPanel.setPreferredSize(new Dimension(400, 0));

    // Image placeholder
    JPanel imagePanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(240, 240, 240));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GRAY);
            g.drawString("Selected Show Image", getWidth()/2 - 50, getHeight()/2);
        }
    };
    imagePanel.setPreferredSize(new Dimension(0, 300));
    leftPanel.add(imagePanel, BorderLayout.NORTH);

    // Show details
    JPanel detailsPanel = new JPanel();
    detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
    detailsPanel.setBackground(Color.WHITE);
    
    selectedShowLabel = new JLabel("Please select a show");
    selectedShowLabel.setFont(new Font("Arial", Font.BOLD, 18));
    selectedDateTimeLabel = new JLabel("No date/time selected");
    selectedDateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    
    detailsPanel.add(selectedShowLabel);
    detailsPanel.add(Box.createVerticalStrut(10));
    detailsPanel.add(selectedDateTimeLabel);
    
    leftPanel.add(detailsPanel, BorderLayout.CENTER);

    // Right panel - Date & Time selection
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.setBackground(Color.WHITE);
    rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

    // Date selection
    JPanel dateSection = new JPanel(new BorderLayout(0, 10));
    dateSection.setBackground(Color.WHITE);
    
    JLabel dateLabel = new JLabel("Select Date:");
    dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
    dateSection.add(dateLabel, BorderLayout.NORTH);

    // Calendar grid
    JPanel dateGrid = new JPanel(new GridLayout(0, 7, 5, 5));
    dateGrid.setBackground(Color.WHITE);
    
    // Add day labels
    String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    for (String day : days) {
        JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
        dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
        dateGrid.add(dayLabel);
    }

    // Add date buttons
    LocalDate today = LocalDate.now();
    ButtonGroup dateGroup = new ButtonGroup();
    
    for (int i = 0; i < 14; i++) {
        LocalDate date = today.plusDays(i);
        JToggleButton dateBtn = new JToggleButton(String.valueOf(date.getDayOfMonth()));
        dateBtn.setPreferredSize(new Dimension(50, 40));
        
        styleDateButton(dateBtn);
        
        dateBtn.addActionListener(e -> {
            selectedDate = date.toString();
            updateSelectedDateTime();
        });
        
        dateGroup.add(dateBtn);
        dateGrid.add(dateBtn);
    }

    dateSection.add(dateGrid, BorderLayout.CENTER);
    rightPanel.add(dateSection);
    rightPanel.add(Box.createVerticalStrut(30));

    // Time selection
    JPanel timeSection = new JPanel(new BorderLayout(0, 10));
    timeSection.setBackground(Color.WHITE);
    
    JLabel timeLabel = new JLabel("Select Time:");
    timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
    timeSection.add(timeLabel, BorderLayout.NORTH);

    // Time buttons
    JPanel timeGrid = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    timeGrid.setBackground(Color.WHITE);
    
    String[] times = {"14:00", "19:00", "19:30"};
    ButtonGroup timeGroup = new ButtonGroup();
    
    for (String time : times) {
        JToggleButton timeBtn = new JToggleButton(time);
        timeBtn.setPreferredSize(new Dimension(100, 40));
        
        styleTimeButton(timeBtn);
        
        timeBtn.addActionListener(e -> {
            selectedTime = time;
            updateSelectedDateTime();
        });
        
        timeGroup.add(timeBtn);
        timeGrid.add(timeBtn);
    }

    timeSection.add(timeGrid, BorderLayout.CENTER);
    rightPanel.add(timeSection);
    rightPanel.add(Box.createVerticalStrut(30));

    // Continue button
    JButton continueBtn = new JButton("Continue to Ticket Selection");
    continueBtn.setBackground(new Color(255, 64, 129));
    continueBtn.setForeground(Color.WHITE);
    continueBtn.setFont(new Font("Arial", Font.BOLD, 14));
    continueBtn.setFocusPainted(false);
    continueBtn.setBorderPainted(false);
    continueBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
    continueBtn.addActionListener(e -> {
    if (selectedShow != null && selectedDate != null && selectedTime != null) {
        if (mainPanel.getComponentCount() < 3) { // Prevent duplicate additions
            mainPanel.add(createTicketTypePage(), "TicketType");
        }
        cardLayout.show(mainPanel, "TicketType");
    } else {
        JOptionPane.showMessageDialog(this,
            "Please select a date and time to continue.",
            "Selection Required",
            JOptionPane.WARNING_MESSAGE);
    }
});


    
    rightPanel.add(continueBtn);

    // Add both panels to main panel
    panel.add(leftPanel, BorderLayout.WEST);
    panel.add(rightPanel, BorderLayout.CENTER);

    return panel;
}

// Add these helper methods after createSchedulePage()
private void styleDateButton(JToggleButton button) {
    button.setBackground(Color.WHITE);
    button.setForeground(Color.BLACK);
    button.setFocusPainted(false);
    button.setBorderPainted(true);
    button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    
    button.addItemListener(e -> {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            button.setBackground(PRIMARY_COLOR);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
        }
    });
}

private void styleTimeButton(JToggleButton button) {
    button.setBackground(Color.WHITE);
    button.setForeground(Color.BLACK);
    button.setFocusPainted(false);
    button.setBorderPainted(true);
    button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    
    button.addItemListener(e -> {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            button.setBackground(PRIMARY_COLOR);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
        }
    });
}

private void updateSelectedDateTime() {
    if (selectedShow != null) {
        selectedShowLabel.setText(selectedShow.name);
        if (selectedDate != null && selectedTime != null) {
            selectedDateTimeLabel.setText("Selected: " + selectedDate + " at " + selectedTime);
        } else {
            selectedDateTimeLabel.setText("No date/time selected");
        }
    }
}


// Add these fields at the top of the class with other fields
private JLabel totalPriceLabel;
private final double CHILD_DISCOUNT = 0.5; // 50% off for children
private final double SENIOR_DISCOUNT = 0.25; // 25% off for seniors

// Add this method after the schedule page methods
private JPanel createTicketTypePage() {
    if (selectedShow == null) {
        JOptionPane.showMessageDialog(this,
            "Please select a musical show before proceeding to ticket selection.",
            "No Show Selected",
            JOptionPane.ERROR_MESSAGE);
        cardLayout.show(mainPanel, "Musicals"); // Redirect to Musicals page
        return new JPanel(); // Return an empty panel
    }    
    
    JPanel panel = new JPanel(new BorderLayout(20, 20));
    panel.setBackground(new Color(245, 245, 245));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Show summary panel at top
    JPanel summaryPanel = new JPanel();
    summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
    summaryPanel.setBackground(Color.WHITE);
    summaryPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
        BorderFactory.createEmptyBorder(15, 15, 15, 15)
    ));

    JLabel bookingTitle = new JLabel("Ticket Selection");
    bookingTitle.setFont(new Font("Arial", Font.BOLD, 24));
    bookingTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    JLabel showInfoLabel = new JLabel();
    showInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    showInfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    if (selectedShow != null) {
        showInfoLabel.setText(String.format("%s at %s | %s at %s", 
            selectedShow.name, selectedShow.theatre, selectedDate, selectedTime));
    }

    summaryPanel.add(bookingTitle);
    summaryPanel.add(Box.createVerticalStrut(10));
    summaryPanel.add(showInfoLabel);

    // Ticket types selection panel
            
    JPanel ticketTypesPanel = new JPanel(new GridLayout(1, 3, 20, 0));
    ticketTypesPanel.setBackground(new Color(245, 245, 245));

    // Create ticket type cards
    ticketTypesPanel.add(createTicketTypeCard("Adult", selectedShow.price, false));
    ticketTypesPanel.add(createTicketTypeCard("Child", selectedShow.price * (1 - CHILD_DISCOUNT), true));
    ticketTypesPanel.add(createTicketTypeCard("Senior", selectedShow.price * (1 - SENIOR_DISCOUNT), true));

    // Bottom panel with total and continue button
    JPanel bottomPanel = new JPanel(new BorderLayout(20, 0));
    bottomPanel.setBackground(Color.WHITE);
    bottomPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
        BorderFactory.createEmptyBorder(15, 15, 15, 15)
    ));

    // Total price
    totalPriceLabel = new JLabel("Total: £0.00");
    totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 18));
    bottomPanel.add(totalPriceLabel, BorderLayout.WEST);

    // Continue button
    JButton continueButton = new JButton("Proceed to Payment");
    continueButton.setBackground(PINK_COLOR);
    continueButton.setForeground(Color.WHITE);
    continueButton.setFont(new Font("Arial", Font.BOLD, 14));
    continueButton.setFocusPainted(false);
    continueButton.setBorderPainted(false);
    continueButton.addActionListener(e -> {
    if (isTotalTicketsSelected()) {
        if (mainPanel.getComponentCount() < 4) { // Prevent duplicate additions
            mainPanel.add(createReceiptPage(), "Receipt");
        }
        cardLayout.show(mainPanel, "Receipt");
        updateReceipt(); // Call method to update receipt details
    } else {
        JOptionPane.showMessageDialog(this,
            "Please select at least one ticket to continue.",
            "Selection Required",
            JOptionPane.WARNING_MESSAGE);
    }
});

    bottomPanel.add(continueButton, BorderLayout.EAST);

    // Add all panels to main panel
    panel.add(summaryPanel, BorderLayout.NORTH);
    panel.add(ticketTypesPanel, BorderLayout.CENTER);
    panel.add(bottomPanel, BorderLayout.SOUTH);

    return panel;
}

private JPanel createTicketTypeCard(String type, double price, boolean hasDiscount) {
    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));

    // Create header panel for icon and type label
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    headerPanel.setBackground(Color.WHITE);
    
    // Add icon if available
    try {
        String iconPath = "/icons/" + type.toLowerCase() + "-ticket.png";
        System.out.println("Attempting to load icon from: " + iconPath); // Debug line
        
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(iconPath));
        if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            throw new Exception("Icon failed to load");
        }
        
        Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);
        headerPanel.add(iconLabel);
        
    } catch (Exception e) {
        System.err.println("Error loading icon for " + type + ": " + e.getMessage());
    }
    
    // Add type label to header
    JLabel typeLabel = new JLabel(type);
    typeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    headerPanel.add(typeLabel);
    
    // Add header panel to card
    card.add(headerPanel);
    card.add(Box.createVerticalStrut(10));

    // Price
    JLabel priceLabel = new JLabel(String.format("£%.2f", price));
    priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
    priceLabel.setForeground(PRIMARY_COLOR);
    priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Discount badge if applicable
    if (hasDiscount) {
        JLabel discountLabel = new JLabel(type.equals("Child") ? "50% OFF" : "25% OFF");
        discountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        discountLabel.setForeground(Color.WHITE);
        discountLabel.setBackground(new Color(76, 175, 80));
        discountLabel.setOpaque(true);
        discountLabel.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        discountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(discountLabel);
        card.add(Box.createVerticalStrut(10));
    }

    // Add price
    card.add(priceLabel);
    card.add(Box.createVerticalStrut(20));

    // Quantity selector
    JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    quantityPanel.setBackground(Color.WHITE);

    JButton minusButton = new JButton("-");
    JLabel quantityLabel = new JLabel("0");
    JButton plusButton = new JButton("+");

    // Style quantity components
    Component[] quantityComponents = {minusButton, quantityLabel, plusButton};
    for (Component comp : quantityComponents) {
        if (comp instanceof JButton) {
            JButton btn = (JButton) comp;
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setPreferredSize(new Dimension(40, 40));
            btn.setBackground(Color.WHITE);
            btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            btn.setFocusPainted(false);
        } else {
            JLabel label = (JLabel) comp;
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(40, 40));
            label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }
    }

    // Add quantity button listeners
    minusButton.addActionListener(e -> {
        int quantity = Integer.parseInt(quantityLabel.getText());
        if (quantity > 0) {
            quantityLabel.setText(String.valueOf(quantity - 1));
            ticketCounts.put(type, quantity - 1);
            updateTotalPrice();
        }
    });

    plusButton.addActionListener(e -> {
        int quantity = Integer.parseInt(quantityLabel.getText());
        if (quantity < 10) {  // Maximum 10 tickets per type
            quantityLabel.setText(String.valueOf(quantity + 1));
            ticketCounts.put(type, quantity + 1);
            updateTotalPrice();
        }
    });

    // Add quantity components
    quantityPanel.add(minusButton);
    quantityPanel.add(quantityLabel);
    quantityPanel.add(plusButton);

    // Add quantity panel
    card.add(quantityPanel);

    return card;
}

private void updateTotalPrice() {
    double total = 0;
    if (selectedShow != null) {
        total += ticketCounts.get("Adult") * selectedShow.price;
        total += ticketCounts.get("Child") * selectedShow.price * (1 - CHILD_DISCOUNT);
        total += ticketCounts.get("Senior") * selectedShow.price * (1 - SENIOR_DISCOUNT);
    }
    totalAmount = total;
    totalPriceLabel.setText(String.format("Total: £%.2f", total));
}

private boolean isTotalTicketsSelected() {
    return ticketCounts.values().stream().mapToInt(Integer::intValue).sum() > 0;
}

// Add this method after the ticket type page methods
private JPanel createReceiptPage() {
    JPanel panel = new JPanel(new BorderLayout(20, 20));
    panel.setBackground(new Color(245, 245, 245));
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    // Create left panel for show details
JPanel leftDetailsPanel = new JPanel();
leftDetailsPanel.setLayout(new BoxLayout(leftDetailsPanel, BoxLayout.Y_AXIS));
leftDetailsPanel.setBackground(Color.WHITE);
leftDetailsPanel.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
    BorderFactory.createEmptyBorder(20, 20, 20, 20)
));
leftDetailsPanel.setPreferredSize(new Dimension(300, 0));

if (selectedShow != null) {
    // Add show image
    try {
        String imagePath = "/images/" + selectedShow.name.toLowerCase()
            .replace(" ", "-")
            .replace("é", "e")
            .replace("!", "")
            .replace("'", "") + ".jpg";
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image scaledImage = originalIcon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftDetailsPanel.add(imageLabel);
        leftDetailsPanel.add(Box.createVerticalStrut(20));
    } catch (Exception e) {
        System.err.println("Error loading show image in receipt: " + e.getMessage());
    }

    // Add show name
    JLabel nameLabel = new JLabel(selectedShow.name);
    nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
    nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    leftDetailsPanel.add(nameLabel);
    leftDetailsPanel.add(Box.createVerticalStrut(10));

    // Add description
    JLabel descLabel = new JLabel("<html><body style='width: 200px'>" + 
        selectedShow.description + "</body></html>");
    descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    leftDetailsPanel.add(descLabel);
}

// Add left panel to main panel
panel.add(leftDetailsPanel, BorderLayout.WEST);

    // Create main receipt panel
    JPanel receiptPanel = new JPanel();
    receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
    receiptPanel.setBackground(Color.WHITE);
    receiptPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
        BorderFactory.createEmptyBorder(25, 25, 25, 25)
    ));

    // Receipt header
    JPanel headerPanel = createReceiptSection("Booking Confirmation");
    
    // Order number
    String orderNumber = generateOrderNumber();
    JLabel orderLabel = new JLabel("Order #: " + orderNumber);
    orderLabel.setFont(new Font("Arial", Font.BOLD, 14));
    headerPanel.add(orderLabel);

    // Show details section
    JPanel showDetailsPanel = createReceiptSection("Show Details");
    if (selectedShow != null) {
        addReceiptDetail(showDetailsPanel, "Show", selectedShow.name);
        addReceiptDetail(showDetailsPanel, "Venue", selectedShow.theatre);
        addReceiptDetail(showDetailsPanel, "Date", selectedDate);
        addReceiptDetail(showDetailsPanel, "Time", selectedTime);
        addReceiptDetail(showDetailsPanel, "Duration", selectedShow.duration);
    }

    // Ticket details section
    JPanel ticketDetailsPanel = createReceiptSection("Ticket Details");
    double total = 0;

    if (selectedShow != null) {
        // Adult tickets
        int adultCount = ticketCounts.get("Adult");
        if (adultCount > 0) {
            double adultTotal = adultCount * selectedShow.price;
            total += adultTotal;
            addReceiptDetail(ticketDetailsPanel, 
                String.format("Adult (£%.2f × %d)", selectedShow.price, adultCount),
                String.format("£%.2f", adultTotal));
        }

        // Child tickets
        int childCount = ticketCounts.get("Child");
        if (childCount > 0) {
            double childPrice = selectedShow.price * (1 - CHILD_DISCOUNT);
            double childTotal = childCount * childPrice;
            total += childTotal;
            addReceiptDetail(ticketDetailsPanel,
                String.format("Child (£%.2f × %d)", childPrice, childCount),
                String.format("£%.2f", childTotal));
        }

        // Senior tickets
        int seniorCount = ticketCounts.get("Senior");
        if (seniorCount > 0) {
            double seniorPrice = selectedShow.price * (1 - SENIOR_DISCOUNT);
            double seniorTotal = seniorCount * seniorPrice;
            total += seniorTotal;
            addReceiptDetail(ticketDetailsPanel,
                String.format("Senior (£%.2f × %d)", seniorPrice, seniorCount),
                String.format("£%.2f", seniorTotal));
        }
    }

    // Total amount
    JPanel totalPanel = new JPanel(new BorderLayout());
    totalPanel.setBackground(Color.WHITE);
    totalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
    
    JLabel totalLabel = new JLabel(String.format("Total Amount: £%.2f", total));
    totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
    totalLabel.setForeground(PRIMARY_COLOR);
    totalPanel.add(totalLabel, BorderLayout.EAST);

    // Button panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    buttonPanel.setBackground(Color.WHITE);

    JButton printButton = new JButton("Print Receipt");
    styleActionButton(printButton);
    printButton.addActionListener(e -> printReceipt());

    JButton homeButton = new JButton("Book Another Show");
    styleActionButton(homeButton);
    homeButton.addActionListener(e -> {
        resetBooking();
        cardLayout.show(mainPanel, "Musicals");
    });

    buttonPanel.add(printButton);
    buttonPanel.add(homeButton);

    // Add all sections to receipt panel
    receiptPanel.add(headerPanel);
    receiptPanel.add(Box.createVerticalStrut(20));
    receiptPanel.add(showDetailsPanel);
    receiptPanel.add(Box.createVerticalStrut(20));
    receiptPanel.add(ticketDetailsPanel);
    receiptPanel.add(Box.createVerticalStrut(20));
    receiptPanel.add(totalPanel);
    receiptPanel.add(Box.createVerticalStrut(30));
    receiptPanel.add(buttonPanel);

    // Add receipt panel to main panel with some padding
    JPanel paddedPanel = new JPanel(new BorderLayout());
    paddedPanel.setBackground(new Color(245, 245, 245));
    paddedPanel.add(receiptPanel, BorderLayout.CENTER);
    
    // Add to scroll pane in case receipt is long
    JScrollPane scrollPane = new JScrollPane(paddedPanel);
    scrollPane.setBorder(null);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    
    panel.add(scrollPane, BorderLayout.CENTER);

    return panel;
}

// Helper methods for receipt page
private JPanel createReceiptSection(String title) {
    JPanel section = new JPanel();
    section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
    section.setBackground(Color.WHITE);
    section.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    section.add(titleLabel);
    section.add(Box.createVerticalStrut(10));

    return section;
}

private void addReceiptDetail(JPanel panel, String label, String value) {
    JPanel detailPanel = new JPanel(new GridLayout(1, 2));
    detailPanel.setBackground(Color.WHITE);

    JLabel labelComponent = new JLabel(label);
    labelComponent.setFont(new Font("Arial", Font.PLAIN, 14));
    
    JLabel valueComponent = new JLabel(value, SwingConstants.RIGHT);
    valueComponent.setFont(new Font("Arial", Font.PLAIN, 14));

    detailPanel.add(labelComponent);
    detailPanel.add(valueComponent);
    
    panel.add(detailPanel);
    panel.add(Box.createVerticalStrut(5));
}

private void styleActionButton(JButton button) {
    button.setBackground(PRIMARY_COLOR);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    button.setPreferredSize(new Dimension(150, 40));
}

private String generateOrderNumber() {
    return "LMT" + System.currentTimeMillis() % 100000;
}

private void printReceipt() {
    JOptionPane.showMessageDialog(this,
        "Receipt has been sent to the printer.",
        "Print Receipt",
        JOptionPane.INFORMATION_MESSAGE);
}

private void resetBooking() {
    selectedShow = null;
    selectedDate = null;
    selectedTime = null;
    ticketCounts.put("Adult", 0);
    ticketCounts.put("Child", 0);
    ticketCounts.put("Senior", 0);
    totalAmount = 0;
    if (totalPriceLabel != null) {
        totalPriceLabel.setText("Total: £0.00");
    }
}

}


