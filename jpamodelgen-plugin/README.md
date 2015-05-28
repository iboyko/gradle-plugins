# Gradle JPA Modelgen plugin

[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](#copyright-and-license)

:exclamation:IMPORTANT PLUGIN ID CHANGES:exclamation:

It changed from `com.github.iboyko.gradle.plugins.jpamodelgen` to `at.comm_unity.gradle.plugins.jpamodelgen`

### Description

This plugin makes it easy to generate JPA Metamodel classes within a project. 
Only Hibernate annotation processor is now supported. The plugin will not manage 3rd party libraries. 
It is still up to the end-user to add the required dependencies like JPA, Hibernate, ... 
Additionally the repository closure should be also configured for project to retrieve processor dependency.

Plugin is a porting of [querydsl gradle plugin](https://github.com/ewerk/gradle-plugins) implemented by @ewerk.

### Configuration

#### library
The artifact coordinates of the Hibernate JpaModelgen annotation processor library.

Defaults to `org.hibernate:hibernate-jpamodelgen:4.3.8.Final`.

#### jpaModelgenSourcesDir
The project relative path to where the JPA metamodel sources are created in. 
All meta model classes will be created within this directory.

Defaults to `src/jpaModelgen/java`.

### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "at.comm_unity.gradle.plugins.jpamodelgen" version "1.1.0"
}

// The following closure demonstrates some of the configuration defaults and is not necessary.
jpaModelgen {
  library = "org.hibernate:hibernate-jpamodelgen:4.3.8.Final"
  jpaModelgenSourcesDir = "src/jpaModelgen/java"
}

// Important!!! Following configuration of compile java arguments 
// should be ALWAYS present when using jpamodelgen plugin.
compileJava.options.compilerArgs += ["-proc:none"]
```

__Use via JCenter__

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath "at.comm_unity.gradle.plugins.jpamodelgen:plugin:1.1.0"
  }
}

apply plugin: "com.github.iboyko.gradle.plugins.jpamodelgen"

// The following closure demonstrates some of the configuration defaults and is not necessary
jpaModelgen {
  library = "org.hibernate:hibernate-jpamodelgen:4.3.8.Final"
  jpaModelgenSourcesDir = "src/jpaModelgen/java"
}

// Important!!! Following configuration of compile java arguments 
// should be ALWAYS present when using jpamodelgen plugin.
compileJava.options.compilerArgs += ["-proc:none"]
```

__Use together with querydsl plugin__

```groovy

plugins {
   id "at.comm_unity.gradle.plugins.jpamodelgen" version "1.1.0"
   id "com.ewerk.gradle.plugins.querydsl" version "1.0.4"
}


/* Include only entities to ignore conflicts of JPA Metamodel generated classes usage */
compileJpaModelgen {
    includes +=  ['**/*/entity/*.java']
}

/* Include only entities to ignore conflicts of Querydsl generated classes usage */
compileQuerydsl {
	includes += ['**/*/entity/*.java']
}

compileJava.options.compilerArgs += ["-proc:none"]

```

