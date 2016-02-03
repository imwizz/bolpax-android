package id.co.imwizz.bolpax.data.entity;

/**
 * Created by MDeveloper on 1/27/2016.
 */
public class IssueList {
    String issueDate;
    String issueLastStatus;

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueLastStatus() {
        return issueLastStatus;
    }

    public void setIssueLastStatus(String issueLastStatus) {
        this.issueLastStatus = issueLastStatus;
    }

    String suspect;
}
