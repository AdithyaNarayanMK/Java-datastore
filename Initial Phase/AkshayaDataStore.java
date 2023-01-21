import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AkshayaDataStore {
    public static void main(String[] args) {
        String fileName = "keyvalue.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter key: ");
        String key = scanner.nextLine();
        
        System.out.print("What would you like to do? (search/update/delete): ");
        String operation = scanner.nextLine();
        
        if(operation.equalsIgnoreCase("search")) {
            searchForKeyValue(fileName, key);
        } else if(operation.equalsIgnoreCase("update")) {
            System.out.print("Enter value: ");
            String value = scanner.nextLine();
            updateOrAddKeyValue(fileName, key, value);
        } else if(operation.equalsIgnoreCase("delete")) {
            deleteKeyValue(fileName, key);
        } else {
            System.out.println("Invalid operation.");
        }
        scanner.close();
    } 

    public static void searchForKeyValue(String fileName, String key) {
        boolean keyFound = false;
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    int index = line.indexOf("=");
                    System.out.println("Value for key '" + key + "' is: " + line.substring(index + 1));
                    keyFound = true;
                    break;
                }
            }
            reader.close();
            if(!keyFound) {
                System.out.println("Key not found in file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateOrAddKeyValue(String fileName, String key, String value) {
        boolean keyFound = false;
        try {
            String line;
            StringBuilder fileContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    int index = line.indexOf("=");
                    line = line.substring(0, index + 1) + value;
                    keyFound = true;
                }
                fileContent.append(line);
                fileContent.append(System.lineSeparator());
            }
            reader.close();
            if(!keyFound) {
                fileContent.append(key + "=" + value + System.lineSeparator());
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(fileContent.toString());
            writer.close();
            System.out.println("Key-value updated or added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
             public static void deleteKeyValue(String fileName, String key) {
                try {
                    String line;
                    StringBuilder fileContent = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    while ((line = reader.readLine()) != null) {
                        if (!line.contains(key)) {
                            fileContent.append(line);
                            fileContent.append(System.lineSeparator());
                        }
                    }
                    reader.close();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(fileContent.toString());
                    writer.close();
                    System.out.println("Key-value deleted successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        