package at.comm_unity.gradle.plugins.jpamodelgen.tasks

import static org.assertj.core.api.Assertions.assertThat
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue

import org.assertj.core.api.Assertions
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import at.comm_unity.gradle.plugins.jpamodelgen.JpaModelgenPlugin

/**
 * @author Illya Boyko
 * @since 1.0.0
 */
class InitJpaModelSourcesDirTaskTest {

    private Project project

    private InitJpaModelSourcesDirTask initTask


    InitJpaModelSourcesDirTaskTest() {
        project = ProjectBuilder.builder().build()
        project.plugins.apply(JpaModelgenPlugin.class)
        project.evaluate()

        initTask = project.tasks.initJpaModelSourcesDir as InitJpaModelSourcesDirTask
    }

    @Test
    void testCreateSourceFolders() {
        initTask.perform()
        //assertThat(project.sourceSets.jpaModelgen, notNullValue())

        //File javaDir = project.sourceSets.jpaModelgen.java.srcDirs.first() as File
        //assertThat(javaDir.name, equalTo("java"))
    }

    @Test
    void testGroup() {
        assertThat(initTask.group).isEqualTo(JpaModelgenPlugin.TASK_GROUP)
    }

    @Test
    void testDescription() {
        assertThat(initTask.description).isEqualTo("Creates the JpaModelgen sources dir for SourceSet main")
    }
}