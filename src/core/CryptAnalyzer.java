package core;

import core.entity.CryptParams;
import core.service.ceasar.CeasarEncrypt;
import util.ArgsValidator;
import util.FileReaderUtils;
import util.FileWriterUtils;

public class CryptAnalyzer {
    private final CryptParams PARAMS;
    public CryptAnalyzer(String [] args) {
        PARAMS = ArgsValidator.checkAndConvert(args);
    }

    public void run () {
        switch (PARAMS.getMode()) {
            case ENCRYPT -> {
                String text = FileReaderUtils.read(PARAMS.getFilePath());
                String result = new CeasarEncrypt(text, PARAMS.getKey()).encrypt();

            }
            case DECRYPT -> {}
        }
    }

    public enum Mode {
        ENCRYPT,
        DECRYPT
    }
}
