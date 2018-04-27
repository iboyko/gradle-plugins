package at.comm_unity.gradle.plugins.jpamodelgen.tasks;

import java.io.File;
import java.io.IOException;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

public abstract class SourceDirTask extends DefaultTask {
    private Property<File> sourceDir;

    public SourceDirTask() {
	sourceDir = getProject().getObjects().property(File.class);
    }

    @OutputDirectory
    public Property<File> getSourceDir() {
	return sourceDir;
    }

    @TaskAction
    public void perform() throws IOException {
	if (sourceDir.isPresent()) {
	    performWithSourceDir(sourceDir.get());
	}
    }

    protected abstract void performWithSourceDir(File dir);
}
