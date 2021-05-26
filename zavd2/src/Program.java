import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) throws IOException {
        List<Person> list = new ArrayList<>();

        Document doc = Jsoup.connect("https://pnu.edu.ua/%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%D0%BD%D0%B8%D0%B9-%D0%B4%D0%BE%D0%B2%D1%96%D0%B4%D0%BD%D0%B8%D0%BA/#t23").get();

        Elements tables = doc.getElementsByTag("tr");
        for (int i = 2; i < tables.size(); i++) {
            Pattern p = Pattern.compile("(\\+38)[- _]*\\(?[- _]*(\\d{3}[- _]*\\)?([- _]*\\d){7}|\\d\\d[- _]*\\d\\d[- _]*\\)?([- _]*\\d){6})");
            Matcher m = p.matcher(tables.get(i).text());

            Pattern p2 = Pattern.compile("[А-ЩЬЮЯЇІЄҐ]{2,}[^\\\\+\\d]+");
            Matcher m2 = p2.matcher(tables.get(i).text());
            while (m.find() && m2.find()) {
                System.out.println(i + "\t" + m2.group(0));
                System.out.println(i + "\t" + m.group(0));
            }
            /*
            Pattern p = Pattern.compile("(\\+38)[- _]*\\(?[- _]*(\\d{3}[- _]*\\)?([- _]*\\d){7}|\\d\\d[- _]*\\d\\d[- _]*\\)?([- _]*\\d){6})");
            Matcher m = p.matcher(tables.get(i).text());
            while (m.find()) {
                System.out.println(i + "\t" + m.group(0));
            }*/
        }

    }
}

class Person {
    String phone;
    String name;

    public Person(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
