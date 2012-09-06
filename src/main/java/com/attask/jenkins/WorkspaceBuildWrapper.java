package com.attask.jenkins;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.BuildWrapper;
import hudson.tasks.BuildWrapperDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

/**
 * User: Joel Johnson
 * Date: 8/14/12
 * Time: 12:03 PM
 */
public class WorkspaceBuildWrapper extends BuildWrapper {
	@DataBoundConstructor
	public WorkspaceBuildWrapper() {

	}

	@Override
	public Environment setUp(AbstractBuild build, Launcher launcher, final BuildListener listener) throws IOException, InterruptedException {
		listener.getLogger().println("Taking snapshot of workspace (before)");
		Directory beforeSnapshot = snapshot(build.getWorkspace());
		build.addAction(new BeforeBuildDirectoryViewAction(build, beforeSnapshot));

		return new Environment() {
			@Override
			public boolean tearDown(AbstractBuild build, BuildListener listener) throws IOException, InterruptedException {
				listener.getLogger().println("Taking snapshot of workspace (after)");
				Directory afterSnapshot = snapshot(build.getWorkspace());
				build.addAction(new AfterBuildDirectoryViewAction(build, afterSnapshot));
				return true;
			}
		};
	}

	private Directory snapshot(FilePath filePath) throws IOException, InterruptedException {
		return filePath.act(new DirectoryFileCallable());
	}

	@Extension
	public static class DescriptorImpl extends BuildWrapperDescriptor {
		@Override
		public boolean isApplicable(AbstractProject<?, ?> item) {
			return true;
		}

		@Override
		public String getDisplayName() {
			return "Snapshot Workspace On Completion";
		}
	}
}
