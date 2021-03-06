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
package at.comm_unity.gradle.plugins.jpamodelgen

import at.comm_unity.gradle.plugins.jpamodelgen.tasks.CleanJpaModelSourcesDirTask
import at.comm_unity.gradle.plugins.jpamodelgen.tasks.InitJpaModelSourcesDirTask
import at.comm_unity.gradle.plugins.jpamodelgen.tasks.JpaModelgenTask
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

/**
 * @author Illya Graf-Boyko
 * @since 1.0.0
 */
class JpaModelgenPluginTest {

    private Project project

    JpaModelgenPluginTest() {
        project = ProjectBuilder.builder().build()
        project.plugins.apply(JpaModelgenPlugin.class)
    }

    @Test
    void testPluginAppliesItself() {
        assertThat(project.plugins.hasPlugin(JpaModelgenPlugin.class)).is(true)
    }

    @Test
    void testReApplyDoesNotFail() {
        project.plugins.apply(JpaModelgenPlugin.class)
        assertThat(project.plugins.hasPlugin(JpaModelgenPlugin.class)).is(true)
    }

    @Test
    void testPluginAppliesJavaPlugin() {
        assertThat(project.plugins.hasPlugin(JavaPlugin.class)).is(true)
    }

    @Test
    void testPluginRegistersJpaModelgenConfiguration() {
        assertThat(project.configurations.jpaModelgen).isNotNull()
    }

    @Test
    void testPluginRegistersJpaModelgenExtensions() {
        assertThat(project.extensions.jpaModelgen).isNotNull()
    }


    @Test
    void testPluginProcessorFromJpaModelgenExtensions() {
        assertThat(project.extensions.jpaModelgen.processor).isNotNull()
    }

    @Test
    void testPluginTasksAreAvailable() {
        assertThat(project.tasks.initJpaModelSourcesDir).isNotNull()
        assertThat(project.tasks.cleanJpaModelSourcesDir).isNotNull()
    }

    @Test
    void testTaskTypes() {
        assertThat(project.tasks.initJpaModelSourcesDir).isInstanceOf(InitJpaModelSourcesDirTask.class)
        assertThat(project.tasks.cleanJpaModelSourcesDir).isInstanceOf(CleanJpaModelSourcesDirTask.class)
    }

    @Test
    void testAfterEvaluate() {

        project.evaluate()

        // TODO: dest default dependencies
        //	DefaultExternalModuleDependency lib = project.configurations.jpaModelgen.dependencies
        //			.getAt(0) as DefaultExternalModuleDependency
        //
        //	println(lib)
        //	String id = lib.group + ":" + lib.name + ":" + lib.version
        //
        //	assertThat(id).isEqualTo(JpaModelgenPluginExtension.DEFAULT_LIBRARY)

        assertThat(project.tasks.generateJpaModel).isNotNull()
        assertThat(project.tasks.generateJpaModel).isInstanceOf(JpaModelgenTask.class)
        assertThat(project.tasks.generateJpaModel.processor).isNotNull()
        assertThat(project.tasks.generateJpaModel.processor.get())
                .isEqualTo(JpaModelgenPluginExtension.DEFAULT_PROCESSOR)

        assertThat(project.tasks.generateJpaModel.options.compilerArgs).contains(
                "-proc:only", "-processor", JpaModelgenPluginExtension.DEFAULT_PROCESSOR)

    }
}
