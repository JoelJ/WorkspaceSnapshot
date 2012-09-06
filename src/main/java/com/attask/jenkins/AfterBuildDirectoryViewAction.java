package com.attask.jenkins;

import hudson.model.Run;

/**
 * User: Joel Johnson
 * Date: 8/14/12
 * Time: 12:27 PM
 */
public class AfterBuildDirectoryViewAction extends DirectoryViewAction {
	public AfterBuildDirectoryViewAction(Run run, Directory directory) {
		super(run, directory);
	}

	public String getDisplayName() {
		return "View Workspace After Build";
	}

	public String getUrlName() {
		return "workspaceAfter";
	}
}
