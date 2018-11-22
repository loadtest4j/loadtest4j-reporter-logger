# loadtest4j-logger

[![Build Status](https://travis-ci.com/loadtest4j/loadtest4j-reporter-logger.svg?branch=master)](https://travis-ci.com/loadtest4j/loadtest4j-reporter-logger)
[![Codecov](https://codecov.io/gh/loadtest4j/loadtest4j-reporter-logger/branch/master/graph/badge.svg)](https://codecov.io/gh/loadtest4j/loadtest4j-reporter-logger)


Unified Logging (JEP158) reporter for [loadtest4j](https://github.com/loadtest4j/loadtest4j).

## Setup

Add this library to your project's `pom.xml`:

```xml
<dependency>
    <groupId>org.loadtest4j.reporters</groupId>
    <artifactId>loadtest4j-logger</artifactId>
    <scope>test</scope>
</dependency>
```

**Note:** Reporters reference the core loadtest4j library in `provided` scope. All standard Drivers will provide it automatically. If you are not using a standard Driver, you must add `org.loadtest4j:loadtest4j` to your `pom.xml`.

```xml
<dependencies>
    <dependency>
        <groupId>org.loadtest4j.drivers</groupId>
        <artifactId>loadtest4j-[driver]</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.loadtest4j.reporters</groupId>
        <artifactId>loadtest4j-logger</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Usage

Attach the reporter using the `LoadTesterDecorator`, or any other method that suits you.

```java
public class PetStoreLT {
    private static final LoadTester loadTester = new LoadTesterDecorator()
            .add(new ResultLogger())
            .decorate(LoadTesterFactory.getLoadTester());
    
    @Test
    public void shouldFindPets() {
        // ...
    }
}
```