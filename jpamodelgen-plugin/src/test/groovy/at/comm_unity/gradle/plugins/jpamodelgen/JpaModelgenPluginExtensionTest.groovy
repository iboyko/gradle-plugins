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

import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

/**
 * @author Illya Graf-Boyko
 * @since 1.0.0
 */
class JpaModelgenPluginExtensionTest {

    private JpaModelgenPluginExtension extension;

    JpaModelgenPluginExtensionTest() {
        extension = new JpaModelgenPluginExtension()
    }

    @Test
    void testDefaultLibraryIsSet() {
        def defaultLibrary = JpaModelgenPluginExtension.DEFAULT_LIBRARY
        assertThat(extension.library).isEqualTo(defaultLibrary)
    }
}