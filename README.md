# Gradle JPA Modelgen plugin

[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](#copyright-and-license)


### Description

This plugin makes it easy to generate JPA Metamodel classes within a project. 
Only Hibernate annotation processor is now supported. The plugin will not manage 3rd party libraries. 
It is still up to the end-user to add the required dependencies like JPA, Hibernate, ... 

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
  id "io.github.iboyko.jpamodelgen" version "1.0.0"
}
```

__Use via JCenter__

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath "io.github.iboyko.jpamodelgen:plugin:1.0.0"
  }
}

apply plugin: "io.github.iboyko.jpamodelgen"

// the following closure demonstrates some of the configuration defaults and is not necessary
jpaModelgen {
  library = "org.hibernate:hibernate-jpamodelgen:4.3.8.Final"
  jpaModelgenSourcesDir = "src/jpaModelgen/java"
}
```


__Use together with querydsl plugin__

```groovy
include com.ewerk.gradle.plugins.tasks.QuerydlsCompile
include com.github.iboyko.jpamodelgen.tasks.JpaModelgenCompile

plugins {
   id "io.github.iboyko.jpamodelgen" version "1.0.0"
   id "com.ewerk.gradle.plugins.querydsl" version "1.0.3"
}


/* Include only entities to ignore conflicts of Querydsl generated classes usage*/
project.tasks.withType(JpaModelgenCompile){ task ->
	task.includes += ['*/*Entity.java']
}

/* Include only entities to ignore conflicts of JPA Metamodel generated classes usage*/
project.tasks.withType(QuerydlsCompile){ task ->
	task.includes += ['*/*Entity.java']
}

```

