package io.github.iboyko.jpamodelgen.tasks

import io.github.iboyko.jpamodelgen.JpaModelgenPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * This task is responsible for purging the 'jpaModelgen' sources dir.
 *
 * @author Illya Boyko
 * @since 1.0.0
 */
class CleanJpaModelgenSourcesDir extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(CleanJpaModelgenSourcesDir.class)

    static final String DESCRIPTION = "Cleans the JpaModelgen sources dir."

    CleanJpaModelgenSourcesDir() {
	this.group = JpaModelgenPlugin.TASK_GROUP
	this.description = DESCRIPTION
    }

    @TaskAction
    def cleanSourceFolders() {
	LOG.info("Clean JpaModelgen source dir")

	project.sourceSets.jpaModelgen.java.srcDirs.each { dir ->
	    if (dir.exists()) {
		dir.deleteDir()
	    }
	}
    }
}