package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class ProjectsPage {
    WebDriver driver;
    @FindBy(xpath = "//*[text()=\"You can't view this project\"]")
    WebElement errorMessage;

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebElement findProjectsKey (String project) {
        return driver.findElement(By.xpath("//*[text()='" + project + "']"));
    }

    public WebElement findSummaryPage (String project) {
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        return driver.findElement(By.xpath("//*[@alt='" + project + "']"));

    }

    public void clickOnSummaryPage (String project) {
        findSummaryPage(project).click();
    }

    public boolean verifyProjectIsAvailable (String project) {
        return findProjectsKey(project).getText().equals(project);
    }

    public boolean verifyProjectIsNotAvailable () {
        return errorMessage.getText().equals("You can't view this project");
    }
}
