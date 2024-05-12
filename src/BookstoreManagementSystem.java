/**
 * @Author: Monei Bakang Mothuti
 * @Date: Sunday 12 May 2024
 * @Time: 8:18 PM
 */

import bean.Book;
import bean.Inventory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookstoreManagementSystem {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    public BookstoreManagementSystem() {
        frame = new JFrame("Bookworm Haven - Bookstore Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(8000, 6000); // Set larger size

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(240, 240, 240)); // Set background color
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        createMenu();
        createTabs();

        frame.pack();
        frame.setVisible(true);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
    }

    private void createTabs() {
        // Create tabs for each feature
        JComponent bookInventoryTab = createBookInventoryTab();
        JComponent bookSalesTab = createBookSalesTab();
        JComponent customerManagementTab = createCustomerManagementTab();
        JComponent reportsTab = createReportsTab();

        tabbedPane.addTab("Book Inventory", bookInventoryTab);
        tabbedPane.addTab("Book Sales", bookSalesTab);
        tabbedPane.addTab("Customer Management", customerManagementTab);
        tabbedPane.addTab("Reports", reportsTab);
    }

    private JComponent createBookInventoryTab() {
        // Create panel for book inventory
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE); // Set background color

        // Add components for adding new books, displaying inventory, and searching
        JButton addBookButton = new JButton("Add New Book");
        addBookButton.setBackground(Color.GREEN); // Set button color
        addBookButton.addActionListener(e -> addNewBook());

        JButton displayInventoryButton = new JButton("Display Inventory");
        displayInventoryButton.setBackground(Color.YELLOW); // Set button color
        displayInventoryButton.addActionListener(e -> displayInventory());

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.ORANGE); // Set button color
        searchButton.addActionListener(e -> searchBooks());

        panel.add(addBookButton, BorderLayout.NORTH);
        panel.add(displayInventoryButton, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.SOUTH);

        return panel;
    }

    private JComponent createBookSalesTab() {
        // Create panel for book sales
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE); // Set background color

        // Add components for processing sales and generating sales reports
        JButton processSaleButton = new JButton("Process Sale");
        processSaleButton.setBackground(Color.BLUE); // Set button color
        processSaleButton.addActionListener(e -> processSale());

        JButton generateReportButton = new JButton("Generate Sales Report");
        generateReportButton.setBackground(Color.RED); // Set button color
        generateReportButton.addActionListener(e -> generateSalesReport());

        panel.add(processSaleButton, BorderLayout.NORTH);
        panel.add(generateReportButton, BorderLayout.CENTER);

        return panel;
    }

    private JComponent createCustomerManagementTab() {
        // Create panel for customer management
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE); // Set background color

        // Add components for adding new customers and viewing purchase history
        JButton addCustomerButton = new JButton("Add New Customer");
        addCustomerButton.setBackground(Color.CYAN); // Set button color
        addCustomerButton.addActionListener(e -> addNewCustomer());

        JButton viewCustomerButton = new JButton("View Customers");
        viewCustomerButton.setBackground(Color.MAGENTA); // Set button color
        viewCustomerButton.addActionListener(e -> viewCustomers());

        JButton viewPurchaseHistoryButton = new JButton("View Purchase History");
        viewPurchaseHistoryButton.setBackground(Color.PINK); // Set button color
        viewPurchaseHistoryButton.addActionListener(e -> viewPurchaseHistory());

        panel.add(viewCustomerButton, BorderLayout.SOUTH);
        panel.add(addCustomerButton, BorderLayout.NORTH);
        panel.add(viewPurchaseHistoryButton, BorderLayout.CENTER);

        return panel;
    }

    private JComponent createReportsTab() {
        // Create panel for reports
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE); // Set background color

        // Add components for displaying total revenue and top-selling books
        JButton displayRevenueButton = new JButton("Display Total Revenue");
        displayRevenueButton.setBackground(Color.GRAY); // Set button color
        displayRevenueButton.addActionListener(e -> displayTotalRevenue());

        JButton displayTopSellingBooksButton = new JButton("Display Top-Selling Books");
        displayTopSellingBooksButton.setBackground(Color.LIGHT_GRAY); // Set button color
        displayTopSellingBooksButton.addActionListener(e -> displayTopSellingBooks());

        panel.add(displayRevenueButton, BorderLayout.NORTH);
        panel.add(displayTopSellingBooksButton, BorderLayout.CENTER);

        return panel;
    }

    // Implement action listeners for each button
    private void addNewBook() {
        // Create a dialog for entering book details
        JDialog dialog = new JDialog(frame, "Add New Book", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        // Create components for entering book details
        JTextField isbnField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();

        // Create labels for each field
        JLabel isbnLabel = new JLabel("ISBN:");
        JLabel titleLabel = new JLabel("Title:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel genreLabel = new JLabel("Genre:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel quantityLabel = new JLabel("Quantity:");

        // Create a panel for the fields and labels
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(isbnLabel);
        panel.add(isbnField);
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(genreLabel);
        panel.add(genreField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);

        // Create a button for adding the book
        JButton addButton = new JButton("Add Book");
        Inventory inventory = new Inventory();
        addButton.addActionListener(e -> {
            // Get the book details from the fields
            String isbn = isbnField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            // Create a new book object with the details
            Book book = new Book(isbn, title, author, genre, price, quantity);

            // Add the book to the inventory
            inventory.addBook(book);

            // Write the book to a text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt", true))) {
                writer.write(book.toString());
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Close the dialog
            dialog.dispose();
        });

        // Add the panel and button to the dialog
        dialog.getContentPane().add(panel, BorderLayout.CENTER);
        dialog.getContentPane().add(addButton, BorderLayout.SOUTH);

        // Show the dialog
        dialog.setVisible(true);
    }

    private void displayInventory() {
        // Create table model with column names
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ISBN", "Title", "Author", "Genre", "Price", "Quantity"});

        // Read data from the text file and populate the table model
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by comma to extract book details
                String[] parts = line.split("\'");
                if (parts.length == 6) {
                    // Add book data to the model
                    model.addRow(parts);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create and configure the JTable
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        // Add the table to a JScrollPane for scrollability
        JScrollPane scrollPane = new JScrollPane(table);

        // Display the table in a dialog
        JDialog dialog = new JDialog(frame, "Book Inventory", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(scrollPane);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private void searchBooks() {
        // Create dialog for search
        JDialog searchDialog = new JDialog(frame, "Search Books", true);
        searchDialog.setLayout(new BorderLayout());

        // Panel for search criteria
        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel searchLabel = new JLabel("Search by:");
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JComboBox<String> searchOptions = new JComboBox<>(new String[]{"Author", "Genre", "Title"});
        searchPanel.add(searchLabel);
        searchPanel.add(searchOptions);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Table to display search results
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ISBN", "Title", "Author", "Genre", "Price", "Quantity"});
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0); // Clear previous search results
                String searchTerm = searchField.getText().toLowerCase().trim();
                String searchOption = (String) searchOptions.getSelectedItem();
                try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split("\'");
                        if (parts.length == 6) {
                            String bookAttribute = "";
                            switch (searchOption) {
                                case "Author":
                                    bookAttribute = parts[2].trim().toLowerCase();
                                    break;
                                case "Genre":
                                    bookAttribute = parts[3].trim().toLowerCase();
                                    break;
                                case "Title":
                                    bookAttribute = parts[1].trim().toLowerCase();
                                    break;
                            }
                            if (bookAttribute.contains(searchTerm)) {
                                model.addRow(parts);
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add components to the dialog
        searchDialog.add(searchPanel, BorderLayout.NORTH);
        searchDialog.add(scrollPane, BorderLayout.CENTER);

        // Set dialog properties and display
        searchDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        searchDialog.setSize(600, 400);
        searchDialog.setLocationRelativeTo(frame);
        searchDialog.setVisible(true);
    }

    private void processSale() {
        // Create a dialog for processing sale
        JDialog saleDialog = new JDialog(frame, "Process Sale", true);
        saleDialog.setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JButton processButton = new JButton("Process");
        inputPanel.add(isbnLabel);
        inputPanel.add(isbnField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel()); // Empty label for spacing
        inputPanel.add(processButton);

        // Add input panel to the dialog
        saleDialog.add(inputPanel, BorderLayout.CENTER);

        // Process button action
        processButton.addActionListener(e -> {
            String isbn = isbnField.getText().trim();
            String quantityStr = quantityField.getText().trim();

            // Validate input
            if (isbn.isEmpty() || quantityStr.isEmpty()) {
                JOptionPane.showMessageDialog(saleDialog, "Please enter ISBN and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(saleDialog, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Process sale
            try {
                BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
                StringBuilder newInventory = new StringBuilder();

                double totalAmount = 0;
                String line;
                boolean bookFound = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 6 && parts[0].trim().equalsIgnoreCase(isbn)) { // Corrected comparison
                        int availableQuantity = Integer.parseInt(parts[5].trim());
                        if (quantity <= availableQuantity) {
                            int remainingQuantity = availableQuantity - quantity;
                            double price = Double.parseDouble(parts[4].trim());
                            totalAmount = price * quantity;

                            // Update inventory
                            newInventory.append(parts[0]).append(",").append(parts[1]).append(",").append(parts[2]).append(",")
                                    .append(parts[3]).append(",").append(parts[4]).append(",").append(remainingQuantity).append("\n");

                            bookFound = true;
                            break;
                        } else {
                            JOptionPane.showMessageDialog(saleDialog, "Insufficient quantity available for sale.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else {
                        newInventory.append(line).append("\n");
                    }
                }
                reader.close();

                // Update inventory file
                BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"));
                writer.write(newInventory.toString());
                writer.close();

                if (!bookFound) {
                    JOptionPane.showMessageDialog(saleDialog, "Book with the specified ISBN not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Record purchase history
                recordPurchaseHistory(isbn, quantity, totalAmount);

                // Generate sales report
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String saleDate = dateFormat.format(new Date());
                String report = "Sale Date: " + saleDate + "\n";
                report += "ISBN: " + isbn + "\n";
                report += "Quantity Sold: " + quantity + "\n";
                report += "Total Amount: $" + totalAmount + "\n\n";

                BufferedWriter reportWriter = new BufferedWriter(new FileWriter("sales_report.txt", true));
                reportWriter.write(report);
                reportWriter.close();

                JOptionPane.showMessageDialog(saleDialog, "Sale processed successfully.\nTotal Amount: $" + totalAmount, "Success", JOptionPane.INFORMATION_MESSAGE);
                saleDialog.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(saleDialog, "An error occurred while processing the sale.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Set dialog properties and display
        saleDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        saleDialog.setSize(400, 200);
        saleDialog.setLocationRelativeTo(frame);
        saleDialog.setVisible(true);
    }

    private void recordPurchaseHistory(String isbn, int quantity, double totalPrice) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("purchase_history.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String purchaseDate = dateFormat.format(new Date());
            String purchaseRecord = String.format("%s,%d,%.2f,%s\n", isbn, quantity, totalPrice, purchaseDate);
            writer.write(purchaseRecord);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while recording purchase history.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void generateSalesReport() {
        // Create a dialog for displaying sales report
        JDialog reportDialog = new JDialog(frame, "Sales Report", true);
        reportDialog.setLayout(new BorderLayout());

        // Panel for displaying sales report
        JPanel reportPanel = new JPanel(new BorderLayout());
        JTextArea reportArea = new JTextArea(20, 50);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        reportPanel.add(scrollPane, BorderLayout.CENTER);

        // Read sales report data from file and display in text area
        try (BufferedReader reader = new BufferedReader(new FileReader("sales_report.txt"))) {
            String line;
            StringBuilder reportText = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                reportText.append(line).append("\n");
            }
            reportArea.setText(reportText.toString());
        } catch (IOException ex) {
            reportArea.setText("Error: Unable to read sales report.");
            ex.printStackTrace();
        }

        // Add components to the dialog
        reportDialog.add(reportPanel, BorderLayout.CENTER);

        // Set dialog properties and display
        reportDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        reportDialog.setSize(600, 400);
        reportDialog.setLocationRelativeTo(frame);
        reportDialog.setVisible(true);
    }


    private void addNewCustomer() {
        // Create a dialog for adding a new customer
        JDialog addCustomerDialog = new JDialog(frame, "Add New Customer", true);
        addCustomerDialog.setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        JButton addButton = new JButton("Add");
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel()); // Empty label for spacing
        inputPanel.add(addButton);

        // Add input panel to the dialog
        addCustomerDialog.add(inputPanel, BorderLayout.CENTER);

        // Add button action
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            // Validate input
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(addCustomerDialog, "Please enter name, email, and phone.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add customer to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt", true))) {
                writer.write(name + "," + email + "," + phone + "\n");
                writer.flush();
                JOptionPane.showMessageDialog(addCustomerDialog, "Customer added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                addCustomerDialog.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(addCustomerDialog, "An error occurred while adding the customer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Set dialog properties and display
        addCustomerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addCustomerDialog.setSize(400, 200);
        addCustomerDialog.setLocationRelativeTo(frame);
        addCustomerDialog.setVisible(true);
    }

    private void viewCustomers() {
        // Create dialog for viewing customers
        JDialog viewCustomerDialog = new JDialog(frame, "View Customers", true);
        viewCustomerDialog.setLayout(new BorderLayout());

        // Panel for displaying customer records
        JPanel customerPanel = new JPanel(new BorderLayout());

        // Table to display customer records
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Name", "Email", "Phone"});
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Read customer data from file and populate table
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    model.addRow(new Object[]{parts[0], parts[1], parts[2]});
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(viewCustomerDialog, "An error occurred while reading customer data.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        customerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add customer panel to dialog
        viewCustomerDialog.add(customerPanel, BorderLayout.CENTER);

        // Set dialog properties and display
        viewCustomerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        viewCustomerDialog.setSize(600, 400);
        viewCustomerDialog.setLocationRelativeTo(frame);
        viewCustomerDialog.setVisible(true);
    }

    private void viewPurchaseHistory() {
        // Create a dialog for viewing purchase history
        JDialog purchaseHistoryDialog = new JDialog(frame, "Purchase History", true);
        purchaseHistoryDialog.setLayout(new BorderLayout());

        // Create text area to display purchase history
        JTextArea purchaseHistoryTextArea = new JTextArea();
        purchaseHistoryTextArea.setEditable(false); // Ensure text area is not editable

        // Read purchase history from file
        try (BufferedReader reader = new BufferedReader(new FileReader("purchase_history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                purchaseHistoryTextArea.append(line + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while reading purchase history.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Create scroll pane and add text area to it
        JScrollPane scrollPane = new JScrollPane(purchaseHistoryTextArea);

        // Add scroll pane to dialog
        purchaseHistoryDialog.add(scrollPane, BorderLayout.CENTER);

        // Set dialog properties and display
        purchaseHistoryDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        purchaseHistoryDialog.setSize(600, 400);
        purchaseHistoryDialog.setLocationRelativeTo(frame);
        purchaseHistoryDialog.setVisible(true);
    }


    private void displayTotalRevenue() {
        double totalRevenue = 0;

        // Read sales records from the sales_report.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader("sales_report.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Extract total amount from each sales record and add it to total revenue
                if (line.startsWith("Total Amount: $")) {
                    double amount = Double.parseDouble(line.substring(15)); // Extract amount excluding "Total Amount: $"
                    totalRevenue += amount;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while reading sales records.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Display total revenue
        JOptionPane.showMessageDialog(frame, "Total Revenue: $" + totalRevenue, "Total Revenue", JOptionPane.INFORMATION_MESSAGE);
    }


    private void displayTopSellingBooks() {
        Map<String, Integer> bookSales = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("sales_report.txt"))) {
            String line;
            String isbn = null;
            int quantity = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ISBN:")) {
                    isbn = line.substring(6).trim(); // Extract ISBN
                } else if (line.startsWith("Quantity Sold:")) {
                    // Extract quantity sold if the previous line was ISBN
                    if (isbn != null) {
                        quantity = Integer.parseInt(line.substring(14).trim()); // Extract quantity sold
                        // Update total sales for the book
                        bookSales.put(isbn, bookSales.getOrDefault(isbn, 0) + quantity);
                    } else {
                        System.err.println("Error: Quantity sold encountered without ISBN.");
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while reading sales records.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Sort the bookSales map entries by sales quantities in descending order
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(bookSales.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Display top-selling books
        StringBuilder topSellingBooks = new StringBuilder("Top Selling Books:\n\n");
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            topSellingBooks.append("ISBN: ").append(entry.getKey()).append("\n");
            topSellingBooks.append("Total Quantity Sold: ").append(entry.getValue()).append("\n\n");
            count++;
            if (count == 2) { // Display top 5 selling books
                break;
            }
        }

        // Show the top-selling books information using a JOptionPane dialog
        JTextArea textArea = new JTextArea(topSellingBooks.toString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "Top Selling Books", JOptionPane.INFORMATION_MESSAGE);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new BookstoreManagementSystem());
//    }
}
