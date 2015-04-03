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

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import io.github.iboyko.jpamodelgen.tasks.CleanJpaModelgenSourcesDir
import io.github.iboyko.jpamodelgen.tasks.InitJpaModelgenSourcesDir
import io.github.iboyko.jpamodelgen.tasks.JpaModelgenCompile

import org.gradle.api.Project
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.gradle.api.plugins.JavaPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

/**
 * @author Illya Boyko
 * @since 1.0.0
 */
class JpaModelgenPluginTest {
 
    private Project project;
    
    public JpaModelgenPluginTest() {
	project = ProjectBuilder.builder().build()
	project.apply plugin: 'jpaModelgen'
    }

    @Test
    public void testPluginAppliesItself() {
	assertThat(project.plugins.hasPlugin(JpaModelgenPlugin.class), is(true))
    }

    @Test
    public void testReApplyDoesNotFail() {
	project.plugins.apply(JpaModelgenPlugin.class)
    }

    @Test
    public void testPluginAppliesJavaPlugin() {
	assertThat(project.plugins.hasPlugin(JavaPlugin.class), is(true))
    }

    @Test
    public void testPluginRegistersJpaModelgenExtensions() {
	assertThat(project.extensions.jpaModelgen, notNullValue())
    }

    @Test
    public void testPluginProcessorFromJpaModelgenExtensions() {
	assertThat(project.extensions.jpaModelgen.processor, notNullValue())
    }

    @Test
    public void testPluginTasksAreAvailable() {
	assertThat(project.tasks.initJpaModelgenSourcesDir, notNullValue())
	assertThat(project.tasks.cleanJpaModelgenSourcesDir, notNullValue())	
    }

    @Test
    public void testTaskTypes() {
	assertThat(project.tasks.initJpaModelgenSourcesDir, instanceOf(InitJpaModelgenSourcesDir.class))
	assertThat(project.tasks.cleanJpaModelgenSourcesDir, instanceOf(CleanJpaModelgenSourcesDir.class))
    }

    @Test
    public void testAfterEvaluate() {
	project.evaluate()

	DefaultExternalModuleDependency lib = project.configurations.compile.dependencies
		.getAt(0) as DefaultExternalModuleDependency

	String id = lib.group + ":" + lib.name + ":" + lib.version

	assertThat(id, equalTo(JpaModelgenPluginExtension.DEFAULT_LIBRARY));

	assertThat(project.tasks.compileJpaModelgen, notNullValue())
	assertThat(project.tasks.compileJpaModelgen, instanceOf(JpaModelgenCompile.class))
    }
}
