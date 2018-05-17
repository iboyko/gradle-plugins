/*
 * Copyright 2018 the original author or authors.
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

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

public abstract class SourceDirTask extends DefaultTask {
    private Property<File> sourceDir;

    SourceDirTask() {
        sourceDir = getProject().getObjects().property(File.class);
    }

    @OutputDirectory
    public Property<File> getSourceDir() {
        return sourceDir;
    }

    @TaskAction
    public void perform() {
        if (sourceDir.isPresent()) {
            performWithSourceDir(sourceDir.get());
        }
    }

    protected abstract void performWithSourceDir(File dir);
}
