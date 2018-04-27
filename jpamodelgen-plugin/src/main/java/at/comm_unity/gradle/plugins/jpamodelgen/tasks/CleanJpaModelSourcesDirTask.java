/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.comm_unity.gradle.plugins.jpamodelgen.tasks;

import java.io.File;

import org.gradle.util.GFileUtils;

import at.comm_unity.gradle.plugins.jpamodelgen.JpaModelgenPlugin;

/**
 * This task is responsible for purging the 'jpaModelgen' sources dir.
 *
 * @author Illya Graf-Boyko
 * @since 2.0.0
 */
public class CleanJpaModelSourcesDirTask extends SourceDirTask {

    public CleanJpaModelSourcesDirTask() {
        setGroup(JpaModelgenPlugin.TASK_GROUP);
    }

    @Override
    protected void performWithSourceDir(File dir) {
        getLogger().info("Clean JpaModel source dir " + dir);
        if (dir.exists()) {
            GFileUtils.cleanDirectory(dir);
        }
    }
}
