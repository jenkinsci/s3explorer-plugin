package s3explorer;

import org.kohsuke.stapler.StaplerProxy;

import hudson.PluginWrapper;
import hudson.model.AbstractProject;
import hudson.model.Item;
import hudson.model.ProminentProjectAction;
import jenkins.model.Jenkins;

public class S3ExplorerAction implements ProminentProjectAction, StaplerProxy {
	private S3ExplorerPlugin plugin = null;
	private AbstractProject<?, ?> project = null;

	public S3ExplorerAction(final AbstractProject<?, ?> project) {
		this.project = project;
		this.plugin = (S3ExplorerPlugin) Jenkins.get().getPlugin(S3ExplorerPlugin.class);
	}

	@Override
	public String getIconFileName() {
		PluginWrapper wrapper = Jenkins.get().getPluginManager().getPlugin(S3ExplorerPlugin.class);

		// return the path to the icon file
		return this.project.hasPermission(Item.BUILD) ? "/plugin/" + wrapper.getShortName() + "/images/amazon-s3.png"
				: null;
	}

	@Override
	public String getDisplayName() {
		// return the label for your link
		return "S3 Explorer";
	}

	@Override
	public String getUrlName() {
		// defines the suburl, which is appended to ...jenkins/job/jobname
		return "s3explorer";
	}

	@Override
	public Object getTarget() {
		this.project.checkPermission(Item.BUILD);
		return this;
	}

	public AbstractProject<?, ?> getProject() {
		return this.project;
	}

	public String getBucketName() {
		return this.plugin.getBucketName();
	}

	public String getAwsRegion() {
		return this.plugin.getAwsRegion();
	}

	public String getAwsAccessKeyId() {
		return this.plugin.getAwsAccessKeyId();
	}

	public String getAwsSecretAccessKey() {
		return this.plugin.getAwsSecretAccessKey();
	}

	public String getJenkinsUrl() {
		Jenkins jenkins = Jenkins.get();

		String url = jenkins.getRootUrl();
		if ((url != null) && (url.endsWith("/"))) {
			url = url.substring(0, url.length() - 1);
		}

		return url;
	}
}