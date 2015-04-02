package io.github.iboyko.jpamodelgen.tasks

import io.github.iboyko.jpamodelgen.JpaModelgenPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * This task is responsible for removing and recreating the configured jpaModelgen source roots.
 *
 * @author Illya Boyko
 * @since 1.0.0
 */
class InitJpaModelgenSourcesDir extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(InitJpaModelgenSourcesDir.class)

    static final String DESCRIPTION = "Creates the Querydsl sources dir."

    InitJpaModelgenSourcesDir() {
	this.group = JpaModelgenPlugin.TASK_GROUP
	this.description = DESCRIPTION
    }

    @TaskAction
    def createSourceFolders() {
	project.file(project.jpaModelgen.jpaModelgenSourcesDir).mkdirs()
    }
}