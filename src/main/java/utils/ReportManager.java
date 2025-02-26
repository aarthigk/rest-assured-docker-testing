package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportManager {
    private static ExtentReports extent;
   private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String reportName = "Test-Report-" + timestamp + ".html";
//            String reportPath = "/app/test-output/TestReport.html"; // Path inside the container
//            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath+ reportName);
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/" + reportName);
            sparkReporter.config().setDocumentTitle("API Test Automation Report");
            sparkReporter.config().setReportName("Microservices Test Report");
          //  sparkReporter.config().setTheme(Theme.STANDARD);
         //   sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setCss(  ".test-wrapper .tools .font-size-14 { " +
                    "font-size: 18px !important; " +
                    "color: white !important; " +
                    "background-color: #050505 !important; " +  // Almost Vantablack
                    "padding: 5px; " +
                    "border-radius: 5px; " +
                    "}"

            +      ".custom-name { " +
                    "   font-size: 20px !important; " +   // Set font size
                    "   color: #ffcc00 !important; " +    // Gold color for visibility
                    "   font-weight: bold !important; " + // Make it bold
                    "   font-family: 'Arial', sans-serif !important; " + // Custom font
                    "   padding-left: 420px; " +           // Space from logo
                    "   text-transform: uppercase; " + // Make it uppercase
              //      "   text-align: center; " +           // Center align the text
               //     "   margin-top: 10px; " +            // Space from the top
                //    "   width: 100%; " +
                    "   display: flex; " +                // Use flexbox
                 //   "   justify-content: center; " +     // Center horizontally using flex
                    "}");
            sparkReporter.config().setJs("document.addEventListener('DOMContentLoaded', function() {" +
                    "   var testsElement = document.querySelector('.tools li a span.font-size-14');" +
                    "   if (testsElement && testsElement.innerText.trim() === 'Tests') {" +
                    "       testsElement.innerText = 'TestExecutedDetail';" +
                    "   }" +
                    "});"

            +
                            "document.addEventListener('DOMContentLoaded', function() {" +
                            "   var header = document.querySelector('.header.navbar .vheader');" +
                            "   if (header) {" +
                            "       var nameTag = document.createElement('div');" +
                            "       nameTag.className = 'custom-name';" +
                            "       nameTag.innerText = 'TestExecution Reporting';" +  // Replace with your name
                            "       header.appendChild(nameTag);" +  // Append name after the logo
                            "   }" +
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
}