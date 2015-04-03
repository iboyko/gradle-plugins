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
