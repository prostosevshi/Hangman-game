import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    private String word = "Progress";

    public void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
            int a = randNumber();
            String line;
            for (int i = 0; i <= a; i++) {
                word = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int randNumber(){
        int a = (int) (Math.random() * 41000);
        return a;
    }

    public String getWord() {
        readFile();
        return word;
    }
}
