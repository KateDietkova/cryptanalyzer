package core.entity;

import core.CryptAnalyzer;

public class CryptParams {
    private final CryptAnalyzer.Mode MODE;
    private final String FILE_PATH;
    private final int KEY;

    public CryptParams(CryptAnalyzer.Mode mode, String filePath, int key) {
        this.MODE = mode;
        this.FILE_PATH = filePath;
        this.KEY = key;
    }

    public CryptAnalyzer.Mode getMode() {
        return MODE;
    }

    public String getFilePath() {
        return FILE_PATH;
    }

    public int getKey() {
        return KEY;
    }
}
