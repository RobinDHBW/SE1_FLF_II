package configuration;

public enum Configuration {
    instance;
    public final String flfIdentifier = "DUS | FLF-5";
    public final String cuIdent = "FT-DUS-FLF-5";
    public final String cuCode = "6072";

    public final Integer cellPerSubCell = 10;
    public final Integer subCellPerMainCell = 100;
    public final Integer mainCellPerBatterie = 100;
}
