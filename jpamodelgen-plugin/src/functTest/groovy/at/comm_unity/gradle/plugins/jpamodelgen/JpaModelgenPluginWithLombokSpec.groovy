package at.comm_unity.gradle.plugins.jpamodelgen


import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class JpaModelgenPluginWithLombokSpec extends AbstractPluginProjectSpec {
   
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
        """
    }

    def "use jpamodelgen and lombok"() {
	given:
	buildFile << """
        dependencies {
		compile 'org.hibernate:hibernate-entitymanager:5.2.16.Final'
		compileOnly 'org.projectlombok:lombok:1.16.18'
	}
        """
	copyProject2TempDir("src/functTest/projects/lombok")

	when:
	def result = GradleRunner.create()
		.withProjectDir(testProjectDir.root)
		.withArguments('generateJpaModel')
		.withPluginClasspath()
		.build()


	then:
	result.task(":generateJpaModel").outcome == SUCCESS
	fileExists("build/generated-src/jpaModelgen/main/com/example/domain/LombokObject1_.java")
    }
}
