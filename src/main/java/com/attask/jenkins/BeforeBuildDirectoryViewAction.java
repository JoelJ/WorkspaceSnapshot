package com.attask.jenkins;

import hudson.model.Action;
import hudson.model.Run;

/**
 * User: Joel Johnson
 * Date: 8/14/12
 * Time: 12:27 PM
 */
public class BeforeBuildDirectoryViewAction extends DirectoryViewAction {
	public BeforeBuildDirectoryViewAction(Run run, Directory directory) {
		super(run, directory);
	}

	public String getDisplayName() {
		return "View Workspace Before Build";
	}

	public String getUrlName() {
		return "workspaceBefore";
	}
}
