package com.finc.s3explorer;


import java.util.logging.Logger;

import org.kohsuke.stapler.StaplerRequest;

import hudson.Plugin;
import net.sf.json.JSONObject;

public class S3ExplorerPlugin extends Plugin {
	private final static Logger LOG = Logger.getLogger(S3ExplorerPlugin.class.getName());
	private static String AWS_REGION;
	private static String AWS_ACCESS_KEY_ID;
	private static String AWS_SECRET_ACCESS_KEY;
	private static String BUCKET_NAME;
	
	public void start() throws Exception {
		LOG.info("Starting S3Explorer plugin");
	}
	
	public void configure(StaplerRequest sr, JSONObject formData) {
		setAwsRegion(formData.optString("awsRegion"));
		setAwsAccessKeyId(formData.optString("awsAccessKeyId"));
		setAwsSecretAccessKey(formData.optString("awsSecretAccessKey"));
		setBucketName(formData.optString("bucketName"));
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
