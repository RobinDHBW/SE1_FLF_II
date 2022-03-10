package configuration;

import task4.EncryptionStrategy;

public enum Configuration {
    instance;
    public final String flfIdentifier = "DUS | FLF-5";
    public final String cuIdent = "FT-DUS-FLF-5";
    public final String cuCode = "6072";
    public final String cuSalt = "seawater";

    public final Integer cellPerSubCell = 10;
    public final Integer subCellPerMainCell = 100;
    public final Integer mainCellPerBatterie = 100;

    public final EncryptionStrategy encryptionStrategy = EncryptionStrategy.AES;

    public final String userDirectory = System.getProperty("user.dir");
    public final String fileSeparator = System.getProperty("file.separator");

    public final String pathToJavaArchive = userDirectory + fileSeparator + "task1" + fileSeparator + "jar" + fileSeparator + "task1.jar";
    public final String pathToJarsigner = "C:" + fileSeparator + "Program Files" + fileSeparator + "Java" + fileSeparator + "jdk-17.0.2" + fileSeparator + "bin" + fileSeparator + "jarsigner";

}
