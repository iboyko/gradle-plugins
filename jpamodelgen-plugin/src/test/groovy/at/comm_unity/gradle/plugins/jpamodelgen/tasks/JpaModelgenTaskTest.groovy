package at.comm_unity.gradle.plugins.jpamodelgen.tasks;


import static org.assertj.core.api.Assertions.assertThat

import org.gradle.api.Project
import org.gradle.api.tasks.SourceTask;
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import at.comm_unity.gradle.plugins.jpamodelgen.JpaModelgenPlugin

public class JpaModelgenTaskTest {
    private Project project;

    private SourceTask generateTask;

    public JpaModelgenTaskTest() {
	project = ProjectBuilder.builder().build();
	project.plugins.apply(JpaModelgenPlugin.class)
	println(project.tasks)
	generateTask = project.tasks.generateJpaModel as SourceTask
	generateTask.includes += ['**/entities/*.java']
	project.evaluate()
    }

    @Test
    void testIncludes() {
	def includes = generateTask.getIncludes()
	assertThat(includes).contains('**/entities/*.java')
    }
}