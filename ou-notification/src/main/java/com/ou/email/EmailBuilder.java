package com.ou.email;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import lombok.Data;

@Data
public class EmailBuilder {

	private String subject;
	private String mailTo;
	private String template;
	private final VelocityContext velocityContext;
	private final VelocityEngine velocityEngine;

	public EmailBuilder(String subject, String mailTo, String template) {
		this.subject = subject;
		this.mailTo = mailTo;
		this.template = template;
		this.velocityContext = new VelocityContext();
		final Properties properties = new Properties();
		properties.setProperty("input.encoding", "UTF-8");
		properties.setProperty("output.encoding", "UTF-8");
		properties.setProperty("resource.loader", "file, class, jar");
		properties.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		this.velocityEngine = new VelocityEngine(properties);
	}

	public void setValue(String key, String value) {
		velocityContext.put(key, value);
	}

	public Mail createMail() {
		final Template templateEngine = velocityEngine.getTemplate("template/" + this.template);
		final StringWriter stringWriter = new StringWriter();
		templateEngine.merge(this.velocityContext, stringWriter);
		final Mail result = new Mail();
		result.setMailTo(this.mailTo);
		result.setMailContent(stringWriter.toString());
		result.setMailSubject(this.subject);

		return result;
	}
}
