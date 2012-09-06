package com.attask.jenkins;

import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Joel Johnson
 * Date: 8/14/12
 * Time: 12:12 PM
 */
@ExportedBean
public class File2 implements Serializable {
	private final String name;
	private final long size;
	private final Date lastModified;

	public File2(String name, long size, Date lastModified) {
		this.size = size;
		this.name = name;
		this.lastModified = lastModified;
	}

	@Exported
	public String getName() {
		return name;
	}

	@Exported
	public long getSize() {
		return size;
	}

	@Exported
	public String getPrettySize() {
		long totalBytes = getSize();
		return prettyBytes(totalBytes);

	}

	public static String prettyBytes(long totalBytes) {
		double kb = (double)totalBytes / 1024.0;
		double mb = kb / 1024.0;
		double gb = mb / 1024.0;

		double value;
		String multiplier;

		if(gb > 1) {
			value = gb;
			multiplier = "gb";
		} else if(mb > 1) {
			value = mb;
			multiplier = "mb";
		} else if(kb > 1) {
			value = kb;
			multiplier = "kb";
		} else {
			return totalBytes + "b";
		}

		String result = String.format("%.2f%s", value, multiplier);
		return result;
	}

	@Exported
	public Date getLastModified() {
		return lastModified;
	}
}
