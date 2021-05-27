import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) throws IOException {
        String text = readFileAsString();
        Pattern p = Pattern.compile("([1-9]|[12]\\d|3[01])\\s*(грудня|лютого|березня|квітня|травня|червня|липня|серпня|вересня|жовтня|листопада)");
        Matcher m = p.matcher(text);
        String replacement = "січня";
        String result = "";
        while (m.find()) {
            result = text.replaceAll("(грудня|лютого|березня|квітня|травня|червня|липня|серпня|вересня|жовтня|листопада)", replacement);
        }
        System.out.println(result);
    }

    private static String readFileAsString() throws IOException {
        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("zavd3.txt"));
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
