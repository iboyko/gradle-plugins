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

import at.comm_unity.gradle.plugins.jpamodelgen.JpaModelgenPlugin;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.compile.JavaCompile;

import java.util.Arrays;

public class JpaModelgenTask extends JavaCompile {

    @Input
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
        return (Action<Project>) project -> getOptions().getCompilerArgs().addAll(Arrays.asList("-proc:only", "-processor", processor.get()));
    }

}
