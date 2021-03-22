import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Author: PJ Duimstra
 * Date: 22/3/21
 * Creates an AddressBook object class and uses JFrame to create GUI to allow for user input
 * and displaying information from the contacts file.
 */
public class AddressBook extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JButton addContact, saveToFile;
    private JTextField name, address, phone, email;
    private JLabel nameLabel, addressLabel, phoneLabel, emailLabel;
    private JTextArea textArea;

    /**
     * Default constructor for the AddressBook
     */
    public AddressBook() {
        frame = new JFrame();

        panel = new JPanel();

        name = new JTextField(50);
        address = new JTextField(50);
        phone = new JTextField(50);
        email = new JTextField(50);

        nameLabel = new JLabel("Name:     ");
        addressLabel = new JLabel("Address:");
        phoneLabel = new JLabel("Phone:   ");
        emailLabel = new JLabel("Email:   ");

        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);

        addContact = new JButton("Add Contact");
        addContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String complete = name.getText() + ", " + address.getText()
                        + ", " + phone.getText() + ", " + email.getText() + "\n";
                name.setText("");
                address.setText("");
                phone.setText("");
                email.setText("");
                textArea.append(complete);
            }
        });

        saveToFile = new JButton("Save to File");
        saveToFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeContactsToFile(textArea);
            }
        });

        frame.add(panel);
        panel.add(nameLabel);
        panel.add(name);
        panel.add(addressLabel);
        panel.add(address);
        panel.add(phoneLabel);
        panel.add(phone);
        panel.add(emailLabel);
        panel.add(email);
        panel.add(addContact);
        panel.add(saveToFile);
        panel.add(textArea);
    }

    /**
     * Reads contacts to a file and displays them to the user in a JTextArea.
     * @param fileName the file being read from.
     * @param textArea the text area to output the file info to.
     */
    public static void readContactsFromFile(String fileName, JTextArea textArea) {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File(fileName));
            while(fileReader.hasNextLine()) {
                textArea.append(fileReader.nextLine() + "\n");
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getStackTrace());
        } finally {
            fileReader.close();
        }
    }

    /**
     * Writes a given set of contacts from a JTextArea to a file.
     * @param textArea the file where contacts are sourced.
     */
    public static void writeContactsToFile(JTextArea textArea) {
        File contacts = new File("contacts.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(contacts);
            writer.write(textArea.getText());
        } catch (FileNotFoundException f) {
            System.out.println(f.getStackTrace());
        } finally {
            writer.close();
        }
    }

    /**
     * Main driver method for the AddressBook application.
     * Initializes the object, sets the size, default close operation, and visibility of the object.
     * @param args n/a
     */
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        final int FRAME_WIDTH = 680;
        final int FRAME_HEIGHT = 550;
        addressBook.frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        addressBook.setTitle("Address Book Application");
        addressBook.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addressBook.frame.setVisible(true);

        readContactsFromFile("contacts.txt", addressBook.textArea);
    }
}