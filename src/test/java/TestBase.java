
//Not much comments, I try to write self-documenting code.
//Works on Linux and Windows
//Linux: ChromeDriver 74.0.3729.6, Chromium Version 74.0.3729.169
//Windows: ChromeDriver 75.0.3770.8, Chrome Version 75.0.3770.80
//
//Premier League season is over, so I couldn't bet on match event. Instead I've chosen to bet 'outright' on favourite.

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.MainPage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class TestBase {

    final String BASE_URL = "https://sports.williamhill.com/betting/en-gb";
    WebDriver driver;
    MainPage mainPage;

    static File junitReport;
    static BufferedWriter junitWriter;

    @BeforeClass
    public static void testSetUp() throws IOException {
        String junitReportFile = System.getProperty("user.dir") + "\\junitReportFile.html";
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date = new Date();
        junitReport = new File(junitReportFile);
        junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
        junitWriter.write("<html><body>");
        junitWriter.write("<h1>Test Execution Summary - " + dateFormat.format(date)
                + "</h1>");
    }

    @AfterClass
    public static void testTearDown() throws IOException {
        junitWriter.write("</body></html>");
        junitWriter.close();
        Desktop.getDesktop().browse(junitReport.toURI());
    }

    @Rule
    public TestRule watchman = new TestWatcher() {

        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            try {
                junitWriter.write(description.getDisplayName() + " "
                        + "success!");
                junitWriter.write("<br/>");
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            try {
                junitWriter.write(description.getDisplayName() + " "
                        + e.getClass().getSimpleName());
                junitWriter.write("<br/>");
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    };

    @Before
    public void setUp() {
        if (System.getProperty("os.name").toLowerCase().equals("linux")) {
            System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "src\\drivers\\chromedriver.exe");
        }
        System.setProperty("webdriver.http.factory", "apache");

        setUpDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        driver.get(BASE_URL);
        mainPage = PageFactory.initElements(this.driver, MainPage.class);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    void setUpDriver() {
        driver = new ChromeDriver();
    }
}
