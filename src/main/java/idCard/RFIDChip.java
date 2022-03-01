package idCard;

public class RFIDChip {
    private final String authCode;

    public RFIDChip(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthCode() {
        return authCode;
    }
}
