package Model;

/**
 * Created by Two_Cong on 15/07/01.
 */
public class Seed {
    private int seedId;
    private String URL;
    private String siteName;
    private String dnLimite;
    private byte status;

    public String getDnLimite() {
        return dnLimite;
    }

    public void setDnLimite(String dnLimite) {
        this.dnLimite = dnLimite;
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }


}
