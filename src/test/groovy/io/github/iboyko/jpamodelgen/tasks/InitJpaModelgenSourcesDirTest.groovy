package io.github.iboyko.jpamodelgen.tasks;

import io.github.iboyko.jpamodelgen.JpaModelgenPlugin;

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class InitJpaModelgenSourcesDirTest {

    private Project project;

    private InitJpaModelgenSourcesDir initTask;


    public InitJpaModelgenSourcesDirTest() {
	project = ProjectBuilder.builder().build()
	project.plugins.apply(JpaModelgenPlugin.class)
	project.evaluate()

	initTask = project.tasks.initJpaModelgenSourcesDir as InitJpaModelgenSourcesDir
    }

    @Test
    void testCreateSourceFolders() {
	initTask.createSourceFolders()
	assertThat(project.sourceSets.jpaModelgen, notNullValue())

	File javaDir = project.sourceSets.jpaModelgen.java.srcDirs.first() as File
	assertThat(javaDir.name, equalTo("java"))
    }

    @Test
    public void testGroup() {
	assertThat(initTask.group, equalTo(JpaModelgenPlugin.TASK_GROUP));
    }

    @Test
    public void testDescription() {
	assertThat(initTask.description, equalTo(InitQuerydslSourcesDir.DESCRIPTION));
    }
}