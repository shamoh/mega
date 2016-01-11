[![Build Status](https://travis-ci.org/shamoh/mega.svg?branch=master)](https://travis-ci.org/shamoh/mega)
[![Coverage Status](https://coveralls.io/repos/shamoh/mega/badge.svg?branch=master&service=github)](https://coveralls.io/github/shamoh/mega?branch=master)
[![JitPack Status](https://jitpack.io/v/shamoh/mega.svg)](https://jitpack.io/#shamoh/mega)

# Mega Platform

Application Micro container based on [CDI](http://www.cdi-spec.org/).


## Modules

### Core modules

| name | javadoc |
|---|---|
| Runtime | [SNAPSHOT](https://jitpack.io/com/github/shamoh/mega/mega-runtime/-SNAPSHOT/javadoc/) |

### Additional modules

| name | javadoc |
|---|---|
| Grizzly | [SNAPSHOT](https://jitpack.io/com/github/shamoh/mega/mega-grizzly/-SNAPSHOT/javadoc/) |
| JAX-RS | [SNAPSHOT](https://jitpack.io/com/github/shamoh/mega/mega-jaxrs/-SNAPSHOT/javadoc/) |


## Build it

```shell
gradle -t build
```


## Install it

```shell
gradle -t install
```


## Try it

There is dedicated [Mega Examples](https://github.com/shamoh/mega-examples) repository.


## Use it

Add it in your `build.gradle` at the end of repositories:

```groovy
repositories {
    // ...
    maven { url "https://jitpack.io" }
 }
```

Add the dependency:

```groovy
ext {
    megaVersion = '-SNAPSHOT'
}
dependencies {
    compile "com.github.shamoh.mega:mega-runtime:${megaVersion}"
    compile "com.github.shamoh.mega:mega-grizzly:${megaVersion}"
}
```

(See [https://jitpack.io/#shamoh/mega](https://jitpack.io/#shamoh/mega) for details.)
