package com.attask.jenkins;

import hudson.FilePath;
import hudson.remoting.VirtualChannel;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

/**
 * User: Joel Johnson
 * Date: 8/14/12
 * Time: 12:23 PM
 */
public class DirectoryFileCallable implements FilePath.FileCallable<Directory> {
	public Directory invoke(File workspace, VirtualChannel channel) throws IOException, InterruptedException {
		final Set<String> visited = new HashSet<String>();
		return generateDirectoryStructure(workspace, visited);
	}

	private Directory generateDirectoryStructure(File currentDirectory, final Set<String> visited) {
		assert currentDirectory.isDirectory() : "Should only be iterating on directories";

		File[] directories = currentDirectory.listFiles(new FileFilter() {
			public boolean accept(File file) {
				if(!file.isDirectory()) {
					return false;
				}
				String canonicalPath = file.getAbsolutePath();
				try {
					canonicalPath = file.getCanonicalPath(); // If it's a symbolic link, get the actual location. Prevent infinite recursion.
				} catch (IOException ignore) {}

				return visited.add(canonicalPath); //true if visited didn't already contain the value
			}
		});
		List<Directory> innerDirectories = new ArrayList<Directory>();
		for (File directory : directories) {
			innerDirectories.add(generateDirectoryStructure(directory, visited));
		}


		List<File2> innerFiles = new ArrayList<File2>();
		File[] files = currentDirectory.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return !file.isDirectory();
			}
		});
		for (File file : files) {
			File2 file2 = new File2(file.getName(), file.length(), new Date(file.lastModified()));
			innerFiles.add(file2);
		}

		return new Directory(currentDirectory.getName(), innerDirectories, innerFiles);
	}
}
