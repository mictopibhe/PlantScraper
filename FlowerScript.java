import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.*;

public class Myproh {

    public static void main(String[] args) throws IOException, InterruptedException {
        HashMap<String, String> plantsMapping = new HashMap<>();
        FirefoxDriver driver = new FirefoxDriver();
        String url = "https://wfoplantlist.org/plant-list/taxon";
        driver.get(url);
        String list_element_id = "quickSearch";
        String input_id = "quick_search_input";
        String result_id = "taxa-suggestions-listbox";

        String taxon_id = "taxon-name";
        String search_items = "suggestions-list-item";
        String[] plants = {"Abutilon theophrasti", "Acer campestre", "Acer negundo", "Acer platanoides", "Acer tataricum"};

        for (String plant: plants){
            driver.get(url);
            By locator = new ById(list_element_id);
            WebElement list_element = driver.findElement(locator);
            By locator2 = new ById(input_id);
            WebElement input_field = driver.findElement(locator2);
            input_field.clear();
            input_field.sendKeys(plant);
            Thread.sleep(1000);
            WebElement result = list_element.findElement(new ById(result_id));
            List<WebElement> items = result.findElements(new By.ByClassName(search_items));
            Thread.sleep(1000);
            if (items.isEmpty()) continue;

            if (items.get(0).getText().equals(plant))
            {
                WebElement link = items.get(0).findElement(new By.ByTagName("a"));
                link.click();
                WebElement full_name = driver.findElement(new By.ByClassName(taxon_id));
                plantsMapping.put(plant, full_name.getText());

            }
        }
        System.out.println(plantsMapping);
    }
}
