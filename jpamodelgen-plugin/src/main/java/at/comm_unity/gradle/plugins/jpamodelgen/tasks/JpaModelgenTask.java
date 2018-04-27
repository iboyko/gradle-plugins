package at.comm_unity.gradle.plugins.jpamodelgen.tasks;

import java.util.Arrays;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.compile.JavaCompile;

import at.comm_unity.gradle.plugins.jpamodelgen.JpaModelgenPlugin;

public class JpaModelgenTask extends JavaCompile {

    private Property<String> processor;

    public JpaModelgenTask() {
	setGroup(JpaModelgenPlugin.TASK_GROUP);
	processor = getProject().getObjects().property(String.class);
	getProject().afterEvaluate(configureOptions());
    }

    public Property<String> getProcessor() {
	return processor;
    }

    private Action<? super Project> configureOptions() {
	return new Action<Project>() {
	    @Override
	    public void execute(Project project) {
		getOptions().getCompilerArgs().addAll(Arrays.asList("-proc:only", "-processor", processor.get()));
	    }
	};
    }

}
