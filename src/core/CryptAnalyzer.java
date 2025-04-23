package core;

import core.entity.CryptParams;
import core.service.ceasar.CeasarDecrypt;
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
                FileWriterUtils.write(PARAMS.getFilePath(), result, Mode.ENCRYPT);
            }
            case DECRYPT -> {
                String text = FileReaderUtils.read(PARAMS.getFilePath());
                String result = new CeasarDecrypt(text, PARAMS.getKey()).decrypt();
                FileWriterUtils.write(PARAMS.getFilePath(), result, Mode.DECRYPT);
            }
        }
    }

    public enum Mode {
        ENCRYPT,
        DECRYPT
    }
}
