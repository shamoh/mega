Mega Platform
=============

Build it
--------

```shell
gradle -t build
```


Install it
----------

```shell
gradle install
```


Use it
------

![JitPack](https://img.shields.io/github/tag/shamoh/mega.svg?label=JitPack)

Add it in your build.gradle at the end of repositories:

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
    compile "com.github.shamoh.mega:runtime:${megaVersion}"
    compile "com.github.shamoh.mega:grizzly:${megaVersion}"
}
```

(See https://jitpack.io/#shamoh/mega.)


TODO
====

Logging
-------

- simplify logging configuration
- add `@Log` annotation and `LogInterceptor` impl

