package io.github.iboyko.jpamodelgen
/**
 * DLS extension for the JpaModelgen plugin. Provides some convenient configuration options.
 *
 * @author Illya Boyko
 * @since 1.0.0
 */
class JpaModelgenPluginExtension {
    static final String NAME = "jpaModelgen"
    static final String DEFAULT_JPAMODELGEN_SOURCES_DIR = new File("src/jpaModelgen/java")
    static final String DEFAULT_LIBRARY = "org.hibernate:hibernate-jpamodelgen:4.3.8.Final"
    static final String DEFAULT_PROCESSOR = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor"

    String jpaModelgenSourcesDir = DEFAULT_JPAMODELGEN_SOURCES_DIR
    String library = DEFAULT_LIBRARY

    String processor = DEFAULT_PROCESSOR
}