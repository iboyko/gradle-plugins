package at.comm_unity.gradle.plugins.jpamodelgen;

public class JpaModelgenPluginExtension {

    public static final String DEFAULT_LIBRARY = "org.hibernate:hibernate-jpamodelgen:5.2.17.Final";
    public static final String DEFAULT_PROCESSOR = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor";

    private String library = DEFAULT_LIBRARY;

    private String processor = DEFAULT_PROCESSOR;

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

}
