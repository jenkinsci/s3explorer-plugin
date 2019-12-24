package s3explorer;

import java.io.IOException;
import java.util.logging.Logger;

import org.kohsuke.stapler.StaplerProxy;

import hudson.EnvVars;
import hudson.PluginWrapper;
import hudson.model.AbstractProject;
import hudson.model.Computer;
import hudson.model.Item;
import hudson.model.ProminentProjectAction;
import jenkins.model.Jenkins;

public class S3ExplorerAction implements ProminentProjectAction, StaplerProxy {
    private final static Logger LOG = Logger.getLogger(S3ExplorerPlugin.class.getName());
    private AbstractProject<?, ?> project;
    
    public S3ExplorerAction(final AbstractProject<?, ?> project) {
        this.project = project;
    }

    @Override
    public String getIconFileName() {
        // return the path to the icon file
        PluginWrapper wrapper = Jenkins.get().getPluginManager().getPlugin(S3ExplorerPlugin.class);
        return this.project.hasPermission(Item.BUILD) ? "/plugin/" + wrapper.getShortName() + "/images/amazon-s3.png" : null;
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
        return S3ExplorerPlugin.getBucketName();
    }

    public String getAwsRegion() {
        return S3ExplorerPlugin.getAwsRegion();
    }
    
    public String getAwsAccessKeyId() {
        return S3ExplorerPlugin.getAwsAccessKeyId();
    }
    
    public String getAwsSecretAccessKey() {
        return S3ExplorerPlugin.getAwsSecretAccessKey();
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