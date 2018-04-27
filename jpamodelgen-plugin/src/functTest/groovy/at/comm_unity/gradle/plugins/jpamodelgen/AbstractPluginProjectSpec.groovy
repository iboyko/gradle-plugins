package at.comm_unity.gradle.plugins.jpamodelgen

import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

import org.junit.After
import org.junit.Before
import org.junit.rules.TemporaryFolder

import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
public class AbstractPluginProjectSpec extends Specification {

    public final TemporaryFolder testProjectDir = new TemporaryFolder()

    @Before
    public void before() {
	testProjectDir.before()
    }

    @After
    public void after() {
	println("Print project folder content");
	printFolderContent(testProjectDir.getRoot())
	testProjectDir.after()
    }

    private def printFolderContent(File file) {
	File[] files = file.listFiles();
	if (files != null) {
	    for (File each : files) {
		println(each);
		if(each.isDirectory()) {
		    printFolderContent(each)
		}
	    }
	}
    }
    
    public boolean fileExists(String path) {
	return Files.exists(testProjectDir.getRoot().toPath().resolve(path))
    }
    
    public boolean fileNotExists(String path) {
	return Files.notExists(testProjectDir.getRoot().toPath().resolve(path))
    }

    public def copyProject2TempDir(String sourceLocation) throws IOException {
	copyProject2TempDir(Paths.get(sourceLocation))
    }
    public def copyProject2TempDir(Path sourceLocation) throws IOException {
	final Path targetPath = testProjectDir.root.toPath()// target
	final Path sourcePath = sourceLocation// source
	Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
		    @Override
		    public FileVisitResult preVisitDirectory(final Path dir,
			    final BasicFileAttributes attrs) throws IOException {
			Files.createDirectories(targetPath.resolve(sourcePath
				.relativize(dir)));
			return FileVisitResult.CONTINUE;
		    }

		    @Override
		    public FileVisitResult visitFile(final Path file,
			    final BasicFileAttributes attrs) throws IOException {
			Files.copy(file,
				targetPath.resolve(sourcePath.relativize(file)));
			return FileVisitResult.CONTINUE;
		    }
		});
    }
}
