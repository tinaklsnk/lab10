import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) throws IOException {
        mif();
        allPeople();
    }

    public static void mif() throws IOException {
        List<Person> miflist = new ArrayList<>();
        String code = readFileAsString();
        Pattern p = Pattern.compile("ФАКУЛЬТЕТ МАТЕМАТИКИ ТА ІНФОРМАТИКИ([\\s\\S]+?)ФІЗИКО-ТЕХНІЧНИЙ ФАКУЛЬТЕТ", Pattern.MULTILINE);
        Matcher m = p.matcher(code);
        String match = "";
        while (m.find()) {
            match = match.concat(m.group(1));
        }
        Document doc = Jsoup.parse(match);
        System.out.println("\n\t\t\t\tПрацівники МІФ\n");
        findPerson(doc, miflist);
    }

    public static void allPeople() throws IOException {
        System.out.println("\t\t\t\tВсі працівники\n");
        List<Person> all = new ArrayList<>();
        Document doc = Jsoup.connect("https://pnu.edu.ua/%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%D0%BD%D0%B8%D0%B9-%D0%B4%D0%BE%D0%B2%D1%96%D0%B4%D0%BD%D0%B8%D0%BA/#t23").get();
        findPerson(doc, all);
    }

    private static void findPerson(Document doc, List<Person> list) {
        Elements tables = doc.getElementsByTag("tr");
        for (int i = 2; i < tables.size(); i++) {
            Pattern p = Pattern.compile("(\\+38)[- _]*\\(?[- _]*(\\d{3}[- _]*\\)?([- _]*\\d){7}|\\d\\d[- _]*\\d\\d[- _]*\\)?([- _]*\\d){6})");
            Matcher m = p.matcher(tables.get(i).text());
            Pattern p2 = Pattern.compile("[А-ЩЬЮЯЇІЄҐ]{2,}[^\\\\+\\d]+");
            Matcher m2 = p2.matcher(tables.get(i).text());
            while (m.find() && m2.find()) {
                String name = m2.group(0);
                String phone = m.group(0);
                list.add(new Person(name, phone));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "\t" + list.get(i).getPhone() + "\t" + list.get(i).getName());
        }
    }

    private static String readFileAsString() throws IOException {
        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("zavd2.html"));
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

class Person {
    String name;
    String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }
}
