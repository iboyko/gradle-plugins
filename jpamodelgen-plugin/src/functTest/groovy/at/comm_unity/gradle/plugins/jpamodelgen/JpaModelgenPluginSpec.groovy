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
