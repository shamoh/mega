[![Build Status](https://travis-ci.org/shamoh/mega.svg?branch=master)](https://travis-ci.org/shamoh/mega)
[![Coverage Status](https://coveralls.io/repos/shamoh/mega/badge.svg?branch=master&service=github)](https://coveralls.io/github/shamoh/mega?branch=master)

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
gradle -t install
```


Try it
------

There is dedicated [Mega Examples](https://github.com/shamoh/mega-examples) repository.


Use it
------

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
