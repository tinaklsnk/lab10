import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        MyHTMlParser parser = new MyHTMlParser();
        parser.findLinks();
        parser.createHTML();
    }
}

class MyHTMlParser {
    public void findLinks() throws IOException {
        String code = readFileAsString("in.html");
        Document html = Jsoup.parse(code);
        Elements elements = html.getElementsByAttribute("href");
        for (int i = 0; i < elements.size(); i++) {
            Program.list.add(elements.get(i).attr("href"));
        }
    }

    public void createHTML() {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"uk\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<title>Назви</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table border=\"1\">\n";
        for (int i = 0; i < Program.list.size(); i++) {
            html = html.concat("\t<tr>\n" +
                    "\t\t<td>"+ (i + 1) + "</td>\n" +
                    "\t\t<td>" + Program.list.get(i) +"</td>\n" +
                    "\t</tr>\n");
        }
        html = html.concat("</table>\n" +
                "</body>\n" +
                "</html>");
        try(FileWriter writer = new FileWriter("output.html", false))
        {
            writer.write(html);
            writer.flush();
            System.out.println("Створено новий файл output.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
}