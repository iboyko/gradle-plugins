package com.github.iboyko.gradle.plugins.jpamodelgen.tasks;

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.api.tasks.SourceTask;
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import com.github.iboyko.gradle.plugins.jpamodelgen.JpaModelgenPlugin

public class JpaModelgenCompileTest {
    private Project project;

    private SourceTask compileTask;


    public JpaModelgenCompileTest() {
	project = ProjectBuilder.builder().build();
	project.plugins.apply(JpaModelgenPlugin.class)
	compileTask = project.tasks.compileJpaModelgen as SourceTask
	compileTask.includes += ['**/entities/*.java']
	project.evaluate()

	
    }
    
    @Test
    void tesIncludes() {
	def includes = compileTask.getIncludes()
	assertThat(compileTask.getIncludes(), hasItem(is('**/entities/*.java')))
    }
}