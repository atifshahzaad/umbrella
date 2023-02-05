package com.ou.util;

import java.util.UUID;

public class OuHrmsUtil {

	public static UUID getId(String subject) {
		String[] split = subject.split(":");
		return UUID.fromString(split[split.length - 1]);
	}
}
