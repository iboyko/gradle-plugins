# Gradle JPA Modelgen plugin

[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](#copyright-and-license)
[![Build Status of develop Branch](https://travis-ci.org/iboyko/gradle-plugins.svg?branch=develop)](https://travis-ci.org/iboyko/gradle-plugins)

### Description

This plugin makes it easy to generate JPA Metamodel classes within a project. 
Only Hibernate annotation processor is now supported. The plugin will not manage 3rd party libraries. 
It is still up to the end-user to add the required dependencies like JPA, Hibernate, ... 
Additionally the repository closure should be also configured for project to retrieve processor dependency.

Plugin is a porting of [querydsl gradle plugin](https://github.com/ewerk/gradle-plugins) implemented by @ewerk.

### Whats new in version 2
* SourceSet support - extra folder for generating classes pro sourceset
* Task `compileJpaModelgen` is renamed to `generateJpaModel`

### Configuration

#### library
The artifact coordinates of the Hibernate JpaModelgen annotation processor library.

Defaults to `org.hibernate:hibernate-jpamodelgen:5.2.17.Final`.

#### Metamodel source directory
Target directory for generating Metamodel files. Extra directory is used pro SourceSet and dependes on SourceSet name.

Defaults:
 - main sourceSet `build/generated-src/jpaModelgen/main`
 - test sourceSet `build/generated-src/jpaModelgen/test`

This Root location for Metamodel source directory `build/generated-src/jpaModelgen` can be overridden by specifying
the `sourcesRootDir` parameter. Thsi location should be specified relative to project directory.
SourceSet name is added to the location (src/generated` -> src/generated/main)


### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "at.comm_unity.gradle.plugins.jpamodelgen" version "1.1.4"
}
```

__Library and sourcesRootDir configuration__

```groovy
plugins {
  id "at.comm_unity.gradle.plugins.jpamodelgen" version "1.1.4"
}

// The following closure demonstrates some of the configuration defaults and is not necessary.
jpaModelgen {
  library = "org.hibernate:hibernate-jpamodelgen:4.3.8.Final"
  sourcesRootDir = "src/generated"
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

* [Appling jpamodelgen](https://github.com/iboyko/gradle-plugins/tree/master/samples/jpamodelgen-sample)
