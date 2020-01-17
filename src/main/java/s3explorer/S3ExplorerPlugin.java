package s3explorer;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import org.kohsuke.stapler.StaplerRequest;

import hudson.Plugin;
import hudson.util.Secret;
import jenkins.model.Jenkins;
import net.sf.json.JSONObject;

public class S3ExplorerPlugin extends Plugin implements Serializable {
	private static final long serialVersionUID = 26L;
	private final static Logger LOG = Logger.getLogger(S3ExplorerPlugin.class.getName());
	private String awsRegion = "";
	private Secret awsAccessKeyId = Secret.fromString("");
	private Secret awsSecretAccessKey = Secret.fromString("");
	private String bucketName = "";

	public void start() throws Exception {
		LOG.info("Starting s3explorer.S3ExplorerPlugin");
		String jenkinsHome = Jenkins.get().getRootDir().getPath();
		LOG.info("Root dir = " + jenkinsHome);

		load();
		LOG.info("Loaded s3explorer.S3ExplorerPlugin's configuration successfully");
	}

	public void configure(StaplerRequest req, JSONObject formData) throws IOException {
		setAwsRegion(formData.optString("awsRegion"));
		setAwsAccessKeyId(formData.optString("awsAccessKeyId"));
		setAwsSecretAccessKey(formData.optString("awsSecretAccessKey"));
		setBucketName(formData.optString("bucketName"));

		save();
		LOG.info("Saved s3explorer.S3ExplorerPlugin's configuration successfully");
	}

	public String getAwsRegion() {
		return this.awsRegion;
	}

	public String getAwsAccessKeyId() {
		return this.awsAccessKeyId.getPlainText();
	}

	public String getAwsSecretAccessKey() {
		return this.awsSecretAccessKey.getPlainText();
	}

	public String getBucketName() {
		return this.bucketName;
	}

	public void setAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
	}

	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = Secret.fromString(awsAccessKeyId);
	}

	public void setAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = Secret.fromString(awsSecretAccessKey);
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
}
