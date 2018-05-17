package at.comm_unity.gradle.plugins.jpamodelgen;

import at.comm_unity.gradle.plugins.jpamodelgen.tasks.CleanJpaModelSourcesDirTask;
import at.comm_unity.gradle.plugins.jpamodelgen.tasks.InitJpaModelSourcesDirTask;
import at.comm_unity.gradle.plugins.jpamodelgen.tasks.JpaModelgenTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.internal.file.UnionFileCollection;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.plugins.Convention;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.compile.JavaCompile;

import java.io.File;
import java.nio.file.Path;

public class JpaModelgenPlugin implements Plugin<Project> {

    private static final Logger LOG = Logging.getLogger(JpaModelgenPlugin.class);
    public static final String TASK_GROUP = "JpaModelgen tasks";
    private static final String CONFIGURATION_NAME = "jpaModelgen";

    @Override
    public void apply(final Project project) {

        if (project.getPluginManager().findPlugin("java") == null) {
            project.getPluginManager().apply(JavaPlugin.class);
        }

        final JpaModelgenPluginExtension extension = project.getExtensions().create(CONFIGURATION_NAME,
                JpaModelgenPluginExtension.class);

        LOG.info("Applying {} configuration", CONFIGURATION_NAME);
        final Configuration config = project.getConfigurations().create(CONFIGURATION_NAME).setVisible(false)
                .setDescription("JpaModelgen configurarion.");

        config.defaultDependencies(dependencies -> {
            LOG.info("Applying default dependency: {}", extension.getLibrary());
            dependencies.add(project.getDependencies().create(extension.getLibrary()));
        });

        Convention convention = project.getConvention();
        JavaPluginConvention plugin = convention.getPlugin(JavaPluginConvention.class);
        SourceSetContainer sourceSets = plugin.getSourceSets();
        sourceSets.all(sourceSet -> {
            // for each source set we will:
            // 1) Define generation folder name

            Path outputDirectoryPath;
            if (extension.getSourcesRootDir() == null
                    || extension.getSourcesRootDir().trim().isEmpty()) {
                outputDirectoryPath = project.getBuildDir().toPath().resolve("generated-src").resolve(CONFIGURATION_NAME);
            } else {
                outputDirectoryPath = project.getProjectDir().toPath().resolve(extension.getSourcesRootDir());
            }
            // output is always sourceset specific
            outputDirectoryPath = outputDirectoryPath.resolve(sourceSet.getName());

            final File outputDir = outputDirectoryPath.toFile();

            // 2) Create clean task
            CleanJpaModelSourcesDirTask cleanJpaModelgenSourcesDirTask = project.getTasks().create(
                    sourceSet.getTaskName("clean", "JpaModelSourcesDir"), CleanJpaModelSourcesDirTask.class,
                    task -> task.getSourceDir().set(outputDir));

            project.getTasks().getByName("clean").dependsOn(cleanJpaModelgenSourcesDirTask);

            // 3) Create init generation dir task
            final String initDirTaskName = sourceSet.getTaskName("init", "JpaModelSourcesDir");
            InitJpaModelSourcesDirTask initJpaModelgenDirTask = project.getTasks().create(initDirTaskName,
                    InitJpaModelSourcesDirTask.class, task -> {
                        task.setDescription(
                                "Creates the JpaModelgen sources dir for SourceSet " + sourceSet.getName());
                        task.getSourceDir().set(outputDir);
                    });

            // 4) Create an JpaModelgenTask for this sourceSet following the gradle
            // naming conventions via call to sourceSet.getTaskName()
            final String generateTaskName = sourceSet.getTaskName("generate", "JpaModel");
            JpaModelgenTask jpaModelgenTask = project.getTasks().create(generateTaskName, JpaModelgenTask.class,
                    task -> {
                        task.getProcessor().set(extension.getProcessor());
                        task.setDestinationDir(outputDir);
                    });
            jpaModelgenTask.setDescription("Processes the " + sourceSet.getName() + " JpaModel classes.");
            jpaModelgenTask.dependsOn(initJpaModelgenDirTask);

            // 5) set up convention mapping for default sources (allows user to not have to
            // specify)
            jpaModelgenTask.setSource(sourceSet.getAllSource());
            jpaModelgenTask.setClasspath(new UnionFileCollection(sourceSet.getCompileClasspath(), config));

            // 6) Set up the JpaModelgen output directory (adding to javac inputs!)
            sourceSet.getJava().srcDir(outputDir);

            // 7) register fact that generate should be run before compiling
            JavaCompile javaCompile = (JavaCompile) project.getTasks()
                    .getByName(sourceSet.getCompileJavaTaskName());
            javaCompile.dependsOn(generateTaskName);
        });

    }

}
