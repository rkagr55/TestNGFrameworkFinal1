package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import main.BaseClass;

public class MyAccountPage extends BaseClass {

	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id='content']/ul[1]/li") private List<WebElement> MyAccountSectionList;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int getMyAccountSectionCount() {
		System.out.println(MyAccountSectionList.size());
		return MyAccountSectionList.size();
	}

	public List<String> getAccountsSectionsList() {

		List<String> accountsList = new ArrayList<>();
//		List<WebElement> accountsHeaderList = driver.findElements(By.xpath("//*[@id='content']/ul[1]/li"));
//		System.out.println(accountsHeaderList.size());

		for (WebElement e : MyAccountSectionList) {
			String text = e.getText();
			System.out.println(text);
			accountsList.add(text);
		}

		return accountsList;

	}
}
