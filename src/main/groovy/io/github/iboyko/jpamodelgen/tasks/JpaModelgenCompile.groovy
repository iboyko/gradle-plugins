package io.github.iboyko.jpamodelgen.tasks;

import org.gradle.api.tasks.compile.JavaCompile;

/**
 * Compiles the Metamodel using jpaModelgen annotation processors supplied by the jpaModelgen extension configuration.
 * 
 * @author Illya Boyko
 * @since 1.0.0
 */
class JpaModelgenCompile extends JavaCompile {

    JpaModelgenCompile() {

	setSource(project.sourceSets.main.java)
	setClasspath(project.configurations.compile)

	File file = project.file(project.jpaModelgen.jpaModelgenSourcesDir)
	setDestinationDir(file)

	options.compilerArgs += [
	    "-proc:only",
	    "-processor",
	    project.extensions.jpaModelgen.processor
	]
    }
}