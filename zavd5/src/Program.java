import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) throws IOException {
        String text = readFileAsString();
        Pattern p = Pattern.compile("((білий)|(чорний))(\\s)*((кіт)|(пес))");
        Matcher m = p.matcher(text);
        int[] k = new int[4];
        while (m.find()) {
            if (m.group(1).equals("білий")) {
                if (m.group(5).equals("пес")) k[0]++;
                else k[1]++;
            } else {
                if (m.group(5).equals("пес")) k[2]++;
                else k[3]++;
            }
        }
        System.out.println("Білий пес:  " + k[0] + "  разів");
        System.out.println("Білий кіт:  " + k[1] + "  разів");
        System.out.println("Чорний пес:  " + k[2] + "  разів");
        System.out.println("Чорний кіт:  " + k[3] + "  разів");
    }

    private static String readFileAsString() throws IOException {
        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("zavd5.txt"));
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
