package org.plugin;

import org.apache.maven.model.Developer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "info")
public class DevelopersInfoMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        List<Developer> developers = project.getDevelopers();

        if (developers == null || developers.isEmpty()) {
            getLog().info("No developers information found in the POM.");
            return;
        }

        getLog().info("Project Developers Information:");
        getLog().info("--------------------------------");

        for (Developer developer : developers) {
            getLog().info("Developer:");
            if (developer.getId() != null) {
                getLog().info("  ID: " + developer.getId());
            }
            if (developer.getName() != null) {
                getLog().info("  Name: " + developer.getName());
            }
            if (developer.getEmail() != null) {
                getLog().info("  Email: " + developer.getEmail());
            }
            if (developer.getUrl() != null) {
                getLog().info("  URL: " + developer.getUrl());
            }
            if (developer.getOrganization() != null) {
                getLog().info("  Organization: " + developer.getOrganization());
            }
            if (developer.getOrganizationUrl() != null) {
                getLog().info("  Organization URL: " + developer.getOrganizationUrl());
            }
            if (developer.getRoles() != null && !developer.getRoles().isEmpty()) {
                getLog().info("  Roles: " + String.join(", ", developer.getRoles()));
            }
            if (developer.getTimezone() != null) {
                getLog().info("  Timezone: " + developer.getTimezone());
            }
            getLog().info("--------------------------------");
        }
    }
}