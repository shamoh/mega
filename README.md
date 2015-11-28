Mega Platform
=============

Application Micro container based on [CDI](http://www.cdi-spec.org/).


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


Try it
------

There is dedicated [Mega Examples](https://github.com/shamoh/mega-examples) repository.


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
    compile "com.github.shamoh.mega:mega-runtime:${megaVersion}"
    compile "com.github.shamoh.mega:mega-grizzly:${megaVersion}"
}
```

(See [https://jitpack.io/#shamoh/mega](https://jitpack.io/#shamoh/mega) for details.)


TODO
====

Logging
-------

- simplify logging configuration
- add `@Log` annotation and `LogInterceptor` impl

