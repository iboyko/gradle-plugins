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
package com.github.iboyko.gradle.plugins.jpamodelgen.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

import com.github.iboyko.gradle.plugins.jpamodelgen.JpaModelgenPlugin

/**
 * This task is responsible for removing and recreating the configured jpaModelgen source roots.
 *
 * @author Illya Boyko
 * @since 1.0.0
 */
class InitJpaModelgenSourcesDir extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(InitJpaModelgenSourcesDir.class)

    static final String DESCRIPTION = "Creates the JpaModelgen sources dir."

    InitJpaModelgenSourcesDir() {
	this.group = JpaModelgenPlugin.TASK_GROUP
	this.description = DESCRIPTION
    }

    @TaskAction
    def createSourceFolders() {
	project.file(project.jpaModelgen.jpaModelgenSourcesDir).mkdirs()
    }
}
