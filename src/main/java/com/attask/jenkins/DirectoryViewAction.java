package com.attask.jenkins;

import hudson.model.Action;
import hudson.model.Run;
import org.kohsuke.stapler.export.Exported;

/**
 * User: Joel Johnson
 * Date: 8/14/12
 * Time: 12:27 PM
 */
public abstract class DirectoryViewAction implements Action {
	private final String runId;
	private final Directory directory;
	private transient Run run;

	public DirectoryViewAction(Run run, Directory directory) {
		this.run = run;
		this.runId = run.getExternalizableId();
		this.directory = directory;
	}

	public Run findRun() {
		if(run == null) {
			run = Run.fromExternalizableId(getRunId());
		}
		return run;
	}

	@Exported
	public String getRunId() {
		return runId;
	}

	@Exported
	public Directory getDirectory() {
		return directory;
	}

	public String getIconFileName() {
		return "folder.png"; //TODO
	}
}
