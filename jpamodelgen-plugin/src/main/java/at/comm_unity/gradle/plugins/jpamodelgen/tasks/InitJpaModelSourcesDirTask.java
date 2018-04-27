package at.comm_unity.gradle.plugins.jpamodelgen.tasks;

import java.io.File;

import at.comm_unity.gradle.plugins.jpamodelgen.JpaModelgenPlugin;

/**
 * This task is responsible for creation of the 'jpaModelgen' sources dir.
 *
 * @author Illya Graf-Boyko
 * @since 2.0.0
 */
public class InitJpaModelSourcesDirTask extends SourceDirTask {
    

    public InitJpaModelSourcesDirTask() {
	setGroup(JpaModelgenPlugin.TASK_GROUP);
    }

    @Override
    protected void performWithSourceDir(File dir) {
	getLogger().info("Init JpaModel source dir " + dir);
	dir.mkdirs();
    }
}
