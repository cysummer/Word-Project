package utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * MyProps
 * Create by cy on 2018/5/19
 **/

@Component
@ConfigurationProperties(prefix="myProps")
public class MyProps {
    private String filePath;

    private String secretKryPath;

    public String getSecretKryPath() {
        return secretKryPath;
    }

    public void setSecretKryPath(String secretKryPath) {
        this.secretKryPath = secretKryPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
