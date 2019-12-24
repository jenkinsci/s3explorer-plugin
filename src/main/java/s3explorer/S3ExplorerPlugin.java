package s3explorer;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.kohsuke.stapler.StaplerRequest;

import hudson.Plugin;
import jenkins.model.Jenkins;
import net.sf.json.JSONObject;

public class S3ExplorerPlugin extends Plugin {
    private final static Logger LOG = Logger.getLogger(S3ExplorerPlugin.class.getName());
    private static String AWS_REGION;
    private static String AWS_ACCESS_KEY_ID;
    private static String AWS_SECRET_ACCESS_KEY;
    private static String BUCKET_NAME;
    private String configFile;
    
    public void start() throws Exception {
        LOG.info("Starting S3Explorer plugin");
        String jenkinsHome = Jenkins.get().getRootDir().getPath();
        LOG.info("Root dir = " + jenkinsHome);
        String configPath = jenkinsHome + "/secrets/s3explorer";
        this.configFile = configPath + "/config.properties";
        
        File file = new File(this.configFile);
        if (file.exists()) {
            readConfig();
        } else {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                if (dir.mkdir()) {
                    LOG.info("Successfully created config directory: " + dir.getPath());
                } else {
                    LOG.info("Could not create config directory: " + dir.getPath());
                }
            }
        }
     }
    
    public void configure(StaplerRequest sr, JSONObject formData) {
        setAwsRegion(formData.optString("awsRegion"));
        setAwsAccessKeyId(formData.optString("awsAccessKeyId"));
        setAwsSecretAccessKey(formData.optString("awsSecretAccessKey"));
        setBucketName(formData.optString("bucketName"));
        
        saveConfig();
    }
    
    private void readConfig() {
        InputStream input = null;
        try {
            input = new FileInputStream(this.configFile);
            Properties prop = new Properties();
            prop.load(input);
            
            setAwsRegion(prop.getProperty("region"));
            setAwsAccessKeyId(prop.getProperty("aws_access_key_id"));
            setAwsSecretAccessKey(prop.getProperty("aws_secret_access_key"));
            setBucketName(prop.getProperty("bucket"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveConfig() {
        OutputStream output = null;
        try {
            output = new FileOutputStream(this.configFile);
            Properties prop = new Properties();
            prop.setProperty("region", S3ExplorerPlugin.AWS_REGION);
            prop.setProperty("aws_access_key_id", S3ExplorerPlugin.AWS_ACCESS_KEY_ID);
            prop.setProperty("aws_secret_access_key", S3ExplorerPlugin.AWS_SECRET_ACCESS_KEY);
            prop.setProperty("bucket", S3ExplorerPlugin.BUCKET_NAME);
            prop.store(output, "Required properties");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getAwsRegion() {
        return S3ExplorerPlugin.AWS_REGION;
    }
    
    public static String getAwsAccessKeyId() {
        return S3ExplorerPlugin.AWS_ACCESS_KEY_ID;
    }
    
    public static String getAwsSecretAccessKey() {
        return S3ExplorerPlugin.AWS_SECRET_ACCESS_KEY;
    }

    public static String getBucketName() {
        return S3ExplorerPlugin.BUCKET_NAME;
    }
    
    public static void setAwsRegion(String awsRegion) {
        S3ExplorerPlugin.AWS_REGION = awsRegion;
    }
    
    public static void setAwsAccessKeyId(String awsAccessKeyId) {
        S3ExplorerPlugin.AWS_ACCESS_KEY_ID = awsAccessKeyId;
    }
    
    public static void setAwsSecretAccessKey(String awsSecretAccessKey) {
        S3ExplorerPlugin.AWS_SECRET_ACCESS_KEY = awsSecretAccessKey;
    }

    public static void setBucketName(String bucketName) {
        S3ExplorerPlugin.BUCKET_NAME = bucketName;
    }

}
