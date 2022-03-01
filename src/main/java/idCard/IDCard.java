package idCard;

public class IDCard {
    private final RFIDChip rfidChip;

    public IDCard(String code) {
        //@TODO encrypt code
        this.rfidChip = new RFIDChip(code);
    }

    public String getAuthCode() {
        return this.rfidChip.getAuthCode();
    }

}
