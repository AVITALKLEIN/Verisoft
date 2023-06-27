import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ProjectTest {

    WebDriver driver;
    MainPage mainPage;


        public  void readXMLFile() {
            mainPage=new MainPage();
            WebElement table=driver.findElement(By.xpath("//*[@id=\"customers\"]/tbody"));

            try {
                File inputFile = new File("C:\\Automation\\task1\\src\\test\\java\\data.XML");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(inputFile);

                // Get the root element
                Element root = document.getDocumentElement();

                // Get all the <test> elements
                NodeList testElements = root.getElementsByTagName("test");

                // Loop through the <test> elements
                for (int i = 0; i < testElements.getLength(); i++) {
                    Element testElement = (Element) testElements.item(i);

                    // Extract the values from the current <test> element
                    int searchColumn =Integer.parseInt( getElementValue(testElement, "searchColumn"));
                    String searchText = getElementValue(testElement, "searchText");
                    int returnColumnText = Integer.parseInt(getElementValue(testElement, "returnColumnText"));
                    String expectedText = getElementValue(testElement, "expectedText");
                  System.out.println(mainPage.verifyTableCellText(table,searchColumn,searchText,returnColumnText,expectedText));



                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static String getElementValue(Element parentElement, String elementName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            return element.getTextContent();
        }
        return "";
    }
    @Test
    public void test01() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");


        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        readXMLFile();



        driver.quit();
   }





}
