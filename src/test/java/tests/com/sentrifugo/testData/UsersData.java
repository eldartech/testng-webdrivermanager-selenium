package tests.com.sentrifugo.testData;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class UsersData {

    @DataProvider(name = "activeUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"EM01", "sentrifugo"},
                {"EM02", "sentrifugo"},
                {"EM03", "sentrifugo"},
                {"EM04", "sentrifugo"},
                {"EM05", "sentrifugo"},
                {"EM06", "sentrifugo"},
                {"EM07", "sentrifugo"},
                {"AGCY8", "sentrifugo"},
                {"US09", "sentrifugo"}

        };
    }

    @DataProvider(name = "roleData")
    public Object[][] getRoles() {
        return new Object[][]{
                {"EM01", "sentrifugo", Arrays.asList("Dashboard", "Self Service", "Service Request", "HR", "Appraisals", "Recruitments", "Background Check", "Organization", "Analytics", "Site Config", "Modules", "Expenses", "Assets", "Disciplinary", "Time")},
                {"EM02", "sentrifugo",Arrays.asList("Dashboard", "Self Service", "Service Request", "HR", "Appraisals", "Recruitments", "Background Check", "Organization", "Analytics", "Site Config", "Expenses", "Disciplinary", "Time")},
                {"EM03", "sentrifugo",Arrays.asList("Dashboard", "Self Service", "Service Request", "HR", "Appraisals", "Recruitments", "Organization", "Expenses", "Disciplinary", "Time")},
                {"AGCY8", "sentrifugo",Arrays.asList("Dashboard", "Organization")},
                {"US09", "sentrifugo",Arrays.asList("Dashboard","Background Check", "Organization")}

        };
    }
}
