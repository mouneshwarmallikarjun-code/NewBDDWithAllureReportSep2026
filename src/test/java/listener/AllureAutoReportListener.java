package listener;

import org.testng.IExecutionListener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AllureAutoReportListener implements IExecutionListener {

    private static final String RESULTS_DIR = "target/allure-results";
    private static final String REPORT_DIR  = "target/allure-report";

    @Override
    public void onExecutionStart() {
        File resultsDir = new File(RESULTS_DIR);
        if (resultsDir.exists()) {
            deleteRecursively(resultsDir);
            System.out.println(">>> Cleared old allure-results before this run.");
        }
    }

    private void deleteRecursively(File file) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                deleteRecursively(child);
            }
        }
        file.delete();
    }

    @Override
    public void onExecutionFinish() {
        try {
            System.out.println(">>> [AllureAutoReportListener] Triggered. Generating Allure report...");

            ProcessBuilder pb;
            if (isWindows()) {
                pb = new ProcessBuilder("cmd.exe", "/c", "allure", "generate", RESULTS_DIR, "--clean", "-o", REPORT_DIR);
            } else {
                pb = new ProcessBuilder("allure", "generate", RESULTS_DIR, "--clean", "-o", REPORT_DIR);
            }

            pb.directory(new File(System.getProperty("user.dir")));
            pb.redirectErrorStream(true);

            Process process = pb.start();
            process.getInputStream().transferTo(System.out);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println(">>> Allure report generated at: " + REPORT_DIR + "/index.html");
                openReport();
            } else {
                System.out.println(">>> Allure report generation FAILED. Exit code: " + exitCode);
            }

        } catch (Exception e) {
            System.out.println(">>> [AllureAutoReportListener] ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void openReport() {
        try {
            ProcessBuilder pb;
            if (isWindows()) {
                pb = new ProcessBuilder("cmd.exe", "/c", "allure", "open", REPORT_DIR);
            } else {
                pb = new ProcessBuilder("allure", "open", REPORT_DIR);
            }
            pb.directory(new File(System.getProperty("user.dir")));
            pb.redirectErrorStream(true);
            pb.start(); // don't wait — allure open blocks and keeps a server running
        } catch (IOException e) {
            System.out.println(">>> Could not auto-open report: " + e.getMessage());
        }
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
