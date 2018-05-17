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

import groovy.util.logging.Slf4j
import org.gradle.testkit.runner.GradleRunner

import static org.gradle.testkit.runner.TaskOutcome.NO_SOURCE
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

@Slf4j
class JpaModelgenPluginSpec extends AbstractPluginProjectSpec {

    File buildFile

    def setup() {
	buildFile = testProjectDir.newFile('build.gradle')
	buildFile << """
	plugins {
		id 'at.comm_unity.gradle.plugins.jpamodelgen'
	}

	repositories{
		jcenter()
	}

	dependencies {
		compile 'org.hibernate:hibernate-entitymanager:5.2.16.Final'
	}
        """

    }

    def "generateJpaModel not run with no source"() {

	when:
	def result = GradleRunner.create()
		.withProjectDir(testProjectDir.root)
		.withArguments('generateJpaModel')
		.withPluginClasspath()
		.build()


	then:
	result.task(":generateJpaModel").outcome == NO_SOURCE
    }

    def "generateJpaModel run for simple project"() {
	given:
	copyProject2TempDir("src/functTest/projects/simple")


	when:
	def result = GradleRunner.create()
		.withProjectDir(testProjectDir.root)
		.withArguments('generateJpaModel')
		.withPluginClasspath()
		.build()


	then:
	result.task(":generateJpaModel").outcome == SUCCESS
	fileExists("build/generated-src/jpaModelgen/main/com/example/domain/DomainObject1_.java")
    }
    
    def "generateJpaModel run for simple project with include sources filter"() {
	given: "Creste build.gradle file with generateJpaModel.includes"
	buildFile << """

	generateJpaModel {
		includes += ['**/entities/*.java']
	}
        """
	copyProject2TempDir("src/functTest/projects/simple")


	when: "execule gradle wunner"
	def result = GradleRunner.create()
		.withProjectDir(testProjectDir.root)
		.withArguments('generateJpaModel')
		.withPluginClasspath()
		.build()


	then: "assert task and files"
	result.task(":generateJpaModel").outcome == SUCCESS
	fileExists("build/generated-src/jpaModelgen/main/com/example/entities/DomainEntity1_.java")
	fileNotExists("build/generated-src/jpaModelgen/main/com/example/domain/DomainObject1_.java")
    }

    def "generate main and test SourceSets"() {
	given:
	copyProject2TempDir("src/functTest/projects/simple")

	when:
	def result = GradleRunner.create()
		.withProjectDir(testProjectDir.root)
		.withArguments('generateJpaModel')
		.withArguments('generateTestJpaModel')
		.withPluginClasspath()
		.forwardOutput()
		.build()


	then:
	result.task(":generateJpaModel").outcome == SUCCESS
	result.task(":generateTestJpaModel").outcome == SUCCESS
	fileExists("build/generated-src/jpaModelgen/main/com/example/domain/DomainObject1_.java")
	fileExists("build/generated-src/jpaModelgen/test/com/example/domain/TestDomainObject1_.java")
    }
}
