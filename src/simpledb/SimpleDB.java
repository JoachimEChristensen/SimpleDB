package simpledb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joachim
 */
public class SimpleDB {

    public static ArrayList<String> database = new ArrayList<>(100);

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String input;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please make a choice. Insert data (insert), Get latest (get), save current database (save), load old database (load)");
        input = reader.readLine();

        while (!input.isEmpty()) {
            switch (input) {
                case "insert":
                    System.out.println("Please insert data");
                    String insert = reader.readLine();
                    insert(insert);
                    System.out.println("Please make a choice. Insert data (insert), Get latest (get), save current database (save), load old database (load)");
                    input = reader.readLine();
                    break;
                case "get":
                    System.out.println("This is the latest entry: " + get());
                    System.out.println("Please make a choice. Insert data (insert), Get latest (get), save current database (save), load old database (load)");
                    input = reader.readLine();
                    break;
                case "save":
                    System.out.println("Please input filename for database to be saved");
                    save(reader.readLine());
                    System.out.println("Please make a choice. Insert data (insert), Get latest (get), save current database (save), load old database (load)");
                    input = reader.readLine();
                    break;
                case "load":
                    System.out.println("Please input filename for the saved database");
                    load(reader.readLine());
                    System.out.println("Please make a choice. Insert data (insert), Get latest (get), save current database (save), load old database (load)");
                    input = reader.readLine();
                    break;
                default:
                    System.out.println("please input a proper return value.");
                    System.out.println("Please make a choice. Insert data (insert), Get latest (get), save current database (save), load old database (load)");
                    input = reader.readLine();
                    break;
            }
        }

        if (input.equals("")) {
            System.exit(1);
        }
    }

    public static void insert(String s) {
        database.add(s);
    }

    public static String get() {
        return database.get(database.size() - 1);
    }

    public static void save(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename + ".dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(database);
        System.out.println("Database saved under the filename: " + filename + ".dat");
    }

    public static void load(String filename) throws IOException, ClassNotFoundException {
        ArrayList<String> load;
        FileInputStream in = new FileInputStream(filename + ".dat");
        ObjectInputStream ois = new ObjectInputStream(in);
        Object obj = ois.readObject();
        load = (ArrayList) obj;
        database.clear();
        database.addAll(load);
        System.out.println("Database loaded");
    }
}
