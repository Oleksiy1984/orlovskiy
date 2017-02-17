package HW4.Dollar;



import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;


/**
 * @author Alexey.
 */
public class FindDollar {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://finance.i.ua/");
        URLConnection connection = url.openConnection();        //get data
            InputStream is = connection.getInputStream();
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, "UTF-8");
            String theString = writer.toString();
            String buy="USD</th><td><span class=\"value -decrease\"><span>";
            int begin = theString.indexOf(buy);
            System.out.println("Курс USD");
            System.out.println("Покупка: "+theString.substring(begin+buy.length(),begin+buy.length()+7));
            System.out.println("Продажа: "+theString.substring(begin+buy.length()+106,begin+buy.length()+113));

}
}
