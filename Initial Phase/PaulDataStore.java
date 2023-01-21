import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.io.*;

public class PaulDataStore extends JFrame {
    private HashMap<String, String> map;
    private JTextField keyField;
    private JTextField valueField;
    private JButton addButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea resultArea;

    public PaulDataStore() {
        map = new HashMap<String, String>();
        keyField = new JTextField(30);
        valueField = new JTextField(30);
        addButton = new JButton("Add");
        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        resultArea = new JTextArea(100, 200);
        resultArea.setEditable(false);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String value = valueField.getText();
                map.put(key, value);
                keyField.setText("");
                valueField.setText("");
                writetoFile("key_value.txt");
                resultArea.setText("Key-Value pair Added");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String value = map.get(key);
                if (value != null) {
                    resultArea.setText(key + " : " + value);
                } else {
                    resultArea.setText("Key not found");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String value = valueField.getText();
                if (map.containsKey(key)) {
                    map.put(key, value);
                    keyField.setText("");
                    valueField.setText("");
                    resultArea.setText("Key-Value pair updated");
                } else {
                    resultArea.setText("Key not found");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                if (map.containsKey(key)) {
                    map.remove(key);
                    keyField.setText("");
                    valueField.setText("");
                    resultArea.setText("Key-Value pair deleted");
                } else {
                    resultArea.setText("Key not found");
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Key:"));
        inputPanel.add(keyField);
        inputPanel.add(new JLabel("Value:"));
        inputPanel.add(valueField);
        inputPanel.add(addButton);
        inputPanel.add(searchButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        add(inputPanel, "North");
        add(new JScrollPane(resultArea), "Center");

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Key-Value Store");
        setVisible(true);
    }

    public void readFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String key = parts[0];
                String value = parts[1];
                map.put(key, value);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writetoFile(String fileName) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            for (String key : map.keySet()) {
                br.write(key + ", " + map.get(key));
                br.newLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PaulDataStore gui = new PaulDataStore();
        gui.readFromFile("key_value.txt");
        gui.writetoFile("key_value.txt");
    }
}