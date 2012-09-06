package com.attask.jenkins;

import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import java.io.Serializable;
import java.util.List;

/**
 * User: Joel Johnson
 * Date: 8/14/12
 * Time: 12:06 PM
 */
@ExportedBean
public class Directory implements Serializable {
	private final String name;
	private final List<Directory> directories;
	private final List<File2> files;
	private final long size;

	public Directory(String name, List<Directory> directories, List<File2> files) {
		this.name = name;
		this.directories = directories;
		this.files = files;

		long size = 0;
		for (Directory directory : directories) {
			size += directory.getSize();
		}

		for (File2 file : files) {
			size += file.getSize();
		}

		this.size = size;
	}

	@Exported
	public String getName() {
		return name;
	}

	@Exported
	public List<Directory> getDirectories() {
		return directories;
	}

	@Exported
	public List<File2> getFiles() {
		return files;
	}

	@Exported
	public long getSize() {
		return size;
	}

	@Exported
	public String getPrettySize() {
		return File2.prettyBytes(getSize());
	}
}
