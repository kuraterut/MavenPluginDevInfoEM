package org.plugin;

import org.apache.maven.model.Developer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

@Mojo(name = "info")
public class DevelopersInfoMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        List<Developer> developers = project.getDevelopers();

        if (CollectionUtils.isEmpty(developers)) {
            getLog().warn("No developers information found in the POM.");
            return;
        }

        getLog().info("Project Developers Information:");
        getLog().info("--------------------------------");

        for (Developer developer : developers) {
            getLog().info("Developer:");
            if (StringUtils.isNotEmpty(developer.getId())) {
                getLog().info(String.format("  ID: %s", developer.getId()));
            }
            if (StringUtils.isNotEmpty(developer.getName())) {
                getLog().info(String.format("  Name: %s", developer.getName()));
            }
            if (StringUtils.isNotEmpty(developer.getEmail()) ) {
                getLog().info(String.format("  Email: %s", developer.getEmail()));
            }
            if (StringUtils.isNotEmpty(developer.getUrl())) {
                getLog().info(String.format("  URL: %s", developer.getUrl()));
            }
            if (StringUtils.isNotEmpty(developer.getOrganization())) {
                getLog().info(String.format("  Organization: %s", developer.getOrganization()));
            }
            if (StringUtils.isNotEmpty(developer.getOrganizationUrl())) {
                getLog().info(String.format("  Organization URL: %s", developer.getOrganizationUrl()));
            }
            if (CollectionUtils.isNotEmpty(developer.getRoles())) {
                getLog().info(String.format("  Roles: %s", String.join(", ", developer.getRoles())));
            }
            if (StringUtils.isNotEmpty(developer.getTimezone())) {
                getLog().info(String.format("  Timezone: %s", developer.getTimezone()));
            }
            getLog().info("--------------------------------");
        }
    }
}