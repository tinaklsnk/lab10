import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) throws IOException {
        String text = readFileAsString();
        Pattern p = Pattern.compile("^(\\s*[А-ЩЬЮЯЇІЄҐ][а-щьюяїієґ]+\\s+[А-ЩЬЮЯЇІЄҐ][а-щьюяїієґ]+)", Pattern.MULTILINE);
        Matcher m = p.matcher(text);
        while (m.find()){
            System.out.println(m.group(0));
        }
    }
    private static String readFileAsString() throws IOException {
        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("zavd6.txt"));
        char[] buf = new char[1024];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
}
