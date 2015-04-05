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
package com.github.iboyko.jpamodelgen.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

import com.github.iboyko.jpamodelgen.JpaModelgenPlugin

/**
 * This task is responsible for purging the 'jpaModelgen' sources dir.
 *
 * @author Illya Boyko
 * @since 1.0.0
 */
class CleanJpaModelgenSourcesDir extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(CleanJpaModelgenSourcesDir.class)

    static final String DESCRIPTION = "Cleans the JpaModelgen sources dir."

    CleanJpaModelgenSourcesDir() {
	this.group = JpaModelgenPlugin.TASK_GROUP
	this.description = DESCRIPTION
    }

    @TaskAction
    def cleanSourceFolders() {
	LOG.info("Clean JpaModelgen source dir")

	project.sourceSets.jpaModelgen.java.srcDirs.each { dir ->
	    if (dir.exists()) {
		dir.deleteDir()
	    }
	}
    }
}
