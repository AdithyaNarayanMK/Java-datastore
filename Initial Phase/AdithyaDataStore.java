import java.io.*;
import java.util.ArrayList;

public class AdithyaDataStore {

    private static String Search(File filename,String key){
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String skey = "";
                int j ;
                for (j=0;j<line.length();j++){
                    char character = line.charAt(j);
                    if(character == ';'){
                        if (skey.equals(key)){
                            String ans ="";
                            j++;
                            while (j<line.length()){
                                char temp = line.charAt(j);
                                ans = ans + temp;
                                j++;
                            }
                            return ans;
                        }
                    }
                    skey = skey +line.charAt(j);
                }
                j=0;
            }
            bufferedReader.close();
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    private static boolean Insert(File filename,String key,String value){
        String str = key+";"+value;
        if(check(filename,key)){
           boolean update = Update(filename,key,value);
            System.out.println(update);
           return false;
        }
        else{
            try {
                FileWriter fileWriter = new FileWriter(filename, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.close();
            }catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private static boolean check(File filename, String key) {
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String skey = "";
                int j ;
                for (j=0;j<line.length();j++){
                    char character = line.charAt(j);
                    if(character == ';'){
                        if (skey.equals(key)){
                            return true;
                        }
                    }
                    skey = skey +line.charAt(j);
                }
            }
            bufferedReader.close();
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private static boolean Update(File filename, String key,String value) {
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            String oldvalue = Search(filename,key);
            String newValue = value;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line.replace(oldvalue,newValue)).append("\n");
            }
            String modifiedText = sb.toString();
            br.close();
            fileReader.close();

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(modifiedText);
            bw.close();
            fw.close();
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    private static boolean Delete(File filename,String Key,String value){
        boolean delete = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            String PairtoDelete = Key+";"+value;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(PairtoDelete)) {
                    lines.remove(i);
                    delete = true;
                }
            }

            FileWriter writer = new FileWriter(filename);
            PrintWriter printer = new PrintWriter(writer);

            for (String s : lines) {
                printer.println(s);
            }

            printer.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return delete;
    }
    public static void main(String[] args){
        File file = new File("D:\\Projects\\DataStore\\src\\com\\adithya\\data-store.txt");
    }
}
