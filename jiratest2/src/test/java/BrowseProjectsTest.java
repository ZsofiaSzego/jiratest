import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.LogoutPage;
import pages.MainPage;
import pages.ProjectsPage;
import util.UtilDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseProjectsTest {
    UtilDriver utilDriver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    ProjectsPage projectPage;


    @BeforeEach
    public void setup() {
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        projectPage = new ProjectsPage(utilDriver.getDriver());
//        mainPage.clickOnViewAllProjects();
    }

    //    @AfterEach
//    public void teardown() {
//        driver.close();
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseProjects/searchcertainprojects.csv")
    public void browseProjectTest_browseFromViewAll_isWorking () {}

    @ParameterizedTest
    @CsvFileSource(resources = "/browseProjects/searchcertainprojects.csv")
    public void browseProjectTest_certainProject_isAvailable (String project)  {
        utilDriver.navigationToCertainProject(project);

        assertTrue(projectPage.verifyProjectIsAvailable(project));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TT"})
    public void browseProjectTest_invalidProject_isNotAvailable (String project) {
        utilDriver.navigationToCertainProject(project);

        assertTrue(projectPage.verifyProjectIsNotAvailable());
    }
}