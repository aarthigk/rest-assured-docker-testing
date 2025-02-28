package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String REPORT_PATH = ConfigManager.getProperty("report_path");
    private static final String LOG_FILE_PATH = "logs/test-report.log";
    private static final Logger logger = LogManager.getLogger(ReportManager.class);

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String reportName = "Test-Report-" + timestamp + ".html";
            String fullReportPath = (REPORT_PATH == null || REPORT_PATH.isEmpty())
                    ? "test-output/" + reportName
                    : REPORT_PATH + reportName;

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fullReportPath);
            sparkReporter.config().setDocumentTitle("API Test Automation Report");
            sparkReporter.config().setReportName("Microservices Test Report");

            // Custom CSS and JS for the report
            sparkReporter.config().setCss(
                    ".test-wrapper .tools .font-size-14 { " +
                            "font-size: 18px !important; " +
                            "color: white !important; " +
                            "background-color: #050505 !important; " +
                            "padding: 5px; " +
                            "border-radius: 5px; " +
                            "}" +
                            ".custom-name { " +
                            "font-size: 20px !important; " +
                            "color: #ffcc00 !important; " +
                            "font-weight: bold !important; " +
                            "font-family: 'Arial', sans-serif !important; " +
                            "padding-left: 420px; " +
                            "text-transform: uppercase; " +
                            "display: flex; " +
                            "}"
            );

            sparkReporter.config().setJs(
                    "document.addEventListener('DOMContentLoaded', function() {" +
                            "var testsElement = document.querySelector('.tools li a span.font-size-14');" +
                            "if (testsElement && testsElement.innerText.trim() === 'Tests') {" +
                            "testsElement.innerText = 'TestExecutedDetail';" +
                            "}" +
                            "});" +
                            "document.addEventListener('DOMContentLoaded', function() {" +
                            "var header = document.querySelector('.header.navbar .vheader');" +
                            "if (header) {" +
                            "var nameTag = document.createElement('div');" +
                            "nameTag.className = 'custom-name';" +
                            "nameTag.innerText = 'TestExecution Reporting';" +
                            "header.appendChild(nameTag);" +
                            "}" +
                            "});"
            );

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User", "Test Archive");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void attachLogsToReport(ExtentTest test) {
        try {
            List<String> logLines = Files.readAllLines(Paths.get(LOG_FILE_PATH));
            StringBuilder logContent = new StringBuilder();
            for (String line : logLines) {
                logContent.append(line).append("<br>"); // Formatting for HTML view in Extent Report
            }
            test.log(Status.INFO, "Test Logs:<br>" + logContent.toString());
        } catch (IOException e) {
            logger.error("Failed to read log file: " + e.getMessage());
            test.log(Status.WARNING, "Could not attach logs due to an error.");
        }
    }
}