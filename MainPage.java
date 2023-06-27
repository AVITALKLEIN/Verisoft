import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {
    public boolean verifyTableCellText(WebElement table, int searchColumn,
                                       String searchText, int returnColumnText, String expectedText) throws Exception {
        String s=getTableCellTextByXpath( table,  searchColumn,searchText,  returnColumnText);
        return expectedText.equals(s);

    }
    public String getTableCellTextByXpath (WebElement table, int searchColumn,
                                           String searchText, int returnColumnText) throws Exception{
        try{

            WebElement e1=table.findElement(By.xpath("//td["+searchColumn+"][contains(text(),'"+searchText+"')]/parent::tr/td["+returnColumnText+"]"));
            return e1.getText();
        }
        catch (Exception e){

        }
        return null;
    }
}
