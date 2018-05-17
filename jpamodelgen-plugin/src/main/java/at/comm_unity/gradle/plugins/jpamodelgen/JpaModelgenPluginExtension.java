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
package at.comm_unity.gradle.plugins.jpamodelgen;

public class JpaModelgenPluginExtension {

    public static final String DEFAULT_LIBRARY = "org.hibernate:hibernate-jpamodelgen:5.2.17.Final";
    public static final String DEFAULT_PROCESSOR = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor";

    private String library = DEFAULT_LIBRARY;

    private String processor = DEFAULT_PROCESSOR;

    private String sourcesRootDir;

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


    public String getSourcesRootDir() {
        return sourcesRootDir;
    }

    public void setSourcesRootDir(String sourcesRootDir) {
        this.sourcesRootDir = sourcesRootDir;
    }
}
