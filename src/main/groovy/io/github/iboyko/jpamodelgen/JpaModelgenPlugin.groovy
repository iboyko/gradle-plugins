package io.github.iboyko.jpamodelgen

import at.comm_unity.gradle.plugins.jpamodelgen.tasks.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.JavaPlugin

/**
 * This plugin can be used to easily create Jpa Metamodel classes and attach them to the project
 * classpath.
 *<p>
 * The plugin registers the extension 'jpaModelgen', so that plugin specific configuration can
 * be overwritten within the build script.
 * </p>
 *
 * The plugin will create an additional source directory into where the jpaModelgen
 * classes will be generated, so that they can be ignored from SCM commits. Per default, this will
 * be {@link JpaModelgenPluginExtension#DEFAULT_JPAMODELGEN_SOURCES_DIR}.
 * <br/><br/>
 * 
 *
 * @author Illya Boyko
 * @since 1.0.0
 */
class JpaModelgenPlugin implements Plugin<Project> {

    public static final String TASK_GROUP = "JpaModelgen tasks"

    private static final Logger LOG = Logging.getLogger(JpaModelgenPlugin.class)

    @Override
    void apply(final Project project) {
	LOG.info("Applying JpaModelgen plugin")

	// do nothing if plugin is already applied
	if (project.plugins.hasPlugin(JpaModelgenPlugin.class)) {
	    return;
	}

	LOG.info("Applying JpaModelgen plugin")

	// apply core 'java' plugin if not present to make 'sourceSets' available
	if (!project.plugins.hasPlugin(JavaPlugin.class)) {
	    project.plugins.apply(JavaPlugin.class)
	}

	// add 'Querydsl' DSL extension
	project.extensions.create(JpaModelgenPluginExtension.NAME, JpaModelgenPluginExtension)

	// add new tasks for creating/cleaning the auto-value sources dir
	project.task(type: CleanJpaModelgenSourcesDir, "cleanJpaModelgenSourcesDir")
	project.task(type: InitJpaModelgenSourcesDir, "initJpaModelgenSourcesDir")

	// make 'clean' depend clean ing querydsl sources
	project.tasks.clean.dependsOn project.tasks.cleanJpaModelgenSourcesDir

	project.afterEvaluate {

	    project.task(type: JpaModelgenCompile, "compileJpaModelgen")
	    project.tasks.compileJpaModelgen.dependsOn project.tasks.initJpaModelgenSourcesDir
	    project.tasks.compileJava.dependsOn project.tasks.compileJpaModelgen

	    File jpaModelgenSourcesDir = jpaModelgenSourcesDir(project)

	    addLibrary(project)
	    addSourceSet(project, jpaModelgenSourcesDir)
	    registerSourceAtCompileJava(project, jpaModelgenSourcesDir)
	}
    }

    private void registerSourceAtCompileJava(Project project, File jpaModelgenSourcesDir) {
	project.compileJava { source jpaModelgenSourcesDir }
    }

    private void addLibrary(Project project) {
	def library = project.extensions.jpaModelgen.library
	LOG.info("JpaModelgen library: {}", library)
	project.dependencies { compile library }
    }

    private void addSourceSet(Project project, File sourcesDir) {
	LOG.info("Create source set 'jpaModelgen'.");

	project.sourceSets {
	    jpaModelgen {
		java.srcDirs = [sourcesDir]
	    }
	}
    }

    private static File jpaModelgenSourcesDir(Project project) {
	String path = project.extensions.jpaModelgen.jpaModelgenSourcesDir
	File jpaModelgenSourcesDir = project.file(path)
	LOG.info("Querydsl sources dir: {}", jpaModelgenSourcesDir.absolutePath);
	return jpaModelgenSourcesDir
    }
}