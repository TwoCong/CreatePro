package Model;



/**
 * Created by Two_Cong on 15/07/01.
 */
public class URL {
    private int URLId;
    private int seedId;
    private String URL;
    private int docsize;
    private String lastCrawlerTime;
    private int URLValue;
    private int pageContentValue;
    private int URLStatus;
    private int cycle;

    public int getURLId() {
        return URLId;
    }

    public void setURLId(int URLId) {
        this.URLId = URLId;
    }

    public int getSeedId() {
        return seedId;
    }

    public void setSeedId(int seedId) {
        this.seedId = seedId;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getDocsize() {
        return docsize;
    }

    public void setDocsize(int docsize) {
        this.docsize = docsize;
    }

    public String getLastCrawlerTime() {
        return lastCrawlerTime;
    }

    public void setLastCrawlerTime(String lastCrawlerTime) {
        this.lastCrawlerTime = lastCrawlerTime;
    }

    public int getURLValue() {
        return URLValue;
    }

    public void setURLValue(int URLValue) {
        this.URLValue = URLValue;
    }

    public int getPageContentValue() {
        return pageContentValue;
    }

    public void setPageContentValue(int pageContentValue) {
        this.pageContentValue = pageContentValue;
    }

    public int getURLStatus() {
        return URLStatus;
    }

    public void setURLStatus(int URLStatus) {
        this.URLStatus = URLStatus;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}
