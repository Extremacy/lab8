import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AddressBook extends JFrame {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Address Book Application");

        final int FRAME_WIDTH = 650;
        final int FRAME_HEIGHT = 550;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        JPanel panel = new JPanel();

        //Creating TextFields
        JTextField name = new JTextField(50);
        JTextField address = new JTextField(50);
        JTextField phone = new JTextField(50);
        JTextField email = new JTextField(50);

        //Creating label objects
        JLabel nameLabel = new JLabel("Name:     ");
        JLabel addressLabel = new JLabel("Address:");
        JLabel phoneLabel = new JLabel("Phone:    ");
        JLabel emailLabel = new JLabel("Email:      ");

        JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);


        JButton addContact = new JButton("Add Contact");
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

        JButton saveToFile = new JButton("Save to File");
        saveToFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void readContactsFromFile() {
        Scanner fileReader = new Scanner("contacts.txt");

    }
}