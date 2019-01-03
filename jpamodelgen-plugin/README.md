# Gradle JPA Modelgen plugin

[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Build Status of develop Branch](https://travis-ci.org/iboyko/gradle-plugins.svg?branch=develop)](https://travis-ci.org/iboyko/gradle-plugins)
[![Version](https://img.shields.io/badge/current%20version-1.1.4-red.svg)](https://github.com/iboyko/gradle-plugins/releases/tag/jpamodelgen-1.1.4)

### Description

This plugin makes it easy to generate JPA Metamodel classes within a project. 
Only Hibernate annotation processor is now supported. The plugin will not manage 3rd party libraries. 
It is still up to the end-user to add the required dependencies like JPA, Hibernate, ... 
Additionally the repository closure should be also configured for project to retrieve processor dependency.



### Whats new in Version 2.0.0
**Version 2.0.0 is not released yet!!!**

Version 2.0.0 and 1.x are not compatible. Plugin configuration, when such is present, should be changed to Version 2.0.0.

Changes:
* SourceSet support - extra folder for generating classes pro sourceset
* Task `compileJpaModelgen` is renamed to `generateJpaModel`

### Configuration

#### Library
The artifact coordinates of the Hibernate JpaModelgen annotation processor library.

Defaults to `org.hibernate:hibernate-jpamodelgen:5.2.17.Final`.

#### Metamodel source directory
Target directory for generating metamodel files. Extra directory is created pro SourceSet, it dependes on SourceSet name.

Defaults:
 - main sourceSet `build/generated-src/jpaModelgen/main`
 - test sourceSet `build/generated-src/jpaModelgen/test`

This Root location for Metamodel source directory `build/generated-src/jpaModelgen` can be overridden by specifying
the `sourcesRootDir` parameter. This location should be specified relative to the project directory.
SourceSet name is added to this location, for example `src/generated` -> `src/generated/main`.


### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "at.comm_unity.gradle.plugins.jpamodelgen" version "2.0.0"
}
```

__Library and sourcesRootDir configuration__

```groovy
plugins {
  id "at.comm_unity.gradle.plugins.jpamodelgen" version "2.0.0"
}

// The following closure demonstrates some of the configuration defaults and is not necessary.
jpaModelgen {
  library = "org.hibernate:hibernate-jpamodelgen:5.2.17.Final"
  sourcesRootDir = "build/generated-src/jpaModelgen"
}
```

__Generation filters__

```groovy
plugins {
   id "at.comm_unity.gradle.plugins.jpamodelgen" version "2.0.0"
}

/* Include only entities to ignore conflicts of JPA Metamodel generated classes usage */
generateJpaModel {
    includes +=  ['**/*/entity/*.java']
}
```

__Sample projects__

* Applying jpamodelgen plugin: [jpamodelgen_sample](https://github.com/iboyko/gradle-plugins/tree/master/samples/jpamodelgen-sample)
* Jpamodelgen and Lombok: [jpamodelgen and lombok](https://github.com/iboyko/gradle-plugins/tree/master/samples/jpamodelgen-and-lombok)


## License

    Copyright (C) 2018 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
