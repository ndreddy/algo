package regex;

import org.junit.Test;

public class RemoveXMLTagRegEx {

    @Test
    public void regEx1() throws Exception{
        String xml = "<Person>" +
                "<Address>\n" +
                "<Location>Beach</Location>\n" +
                "<Dangerous>\n" +
                "    <Flag>N</Flag>\n" +
                "</Dangerous>\n" +
                "</Address>\n" +
                "<Person>";

        // Remove person's address
        //.* don't contains the \n\r , so need use [\s\S] to match all
        //System.out.println(".*? = " + xml.replaceAll("<Address>.*?</Address>$",""));
        System.out.println(xml.replaceAll("<Address>[\\s\\S]*?</Address>",""));
    }
}
