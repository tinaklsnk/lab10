import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) throws IOException {
        String text = readFileAsString();
        Pattern p = Pattern.compile("/\\*((\\s)|())((0[1-9]|[12][0-9]|3[01])[- -.])(((0[1-9]|1[012])|(січня|лютого|березня|квітня|травня|червня|липня|серпня|вересня|жовтня*|листопада|грудня))|(((0[1-9]|1[012])|(січня|лютого|березня|квітня|травня|червня|липня|серпня|вересня|жовтня*|листопада|грудня)))[- -.](19|20\\d\\d))((\\s)|())\\*/");
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(m.group(4) + m.group(6));
        }
    }

    private static String readFileAsString() throws IOException {
        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("zavd4.txt"));
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
