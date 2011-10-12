# memcachedweaver: Caching methods result with AOP on memcached

## Getting Started

### Maven

```xml
<repositories>
  <repository>
    <id>seratch.github.com releases</id>
    <url>http://seratch.github.com/mvn-repo/releases</url>
  </repository>
  <repository>
    <id>seratch.github.com snapshots</id>
    <url>http://seratch.github.com/mvn-repo/snapshots</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.seratch</groupId>
    <artifactId>memcachedweaver</artifactId>
    <version>0.1-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## Usage

### Using as a wrapper API for several memcached clients

"memcachedweaver" is a pluggable wrapper API for several memcached clients, and it's possible to add new adaptors.

* memcacheweaver.client.adaptor.SpymemcachedAdaptor
* memcacheweaver.client.adaptor.XmemcachedAdaptor

```java
Configuration config = new Configuration();
config.setAdaptorClassName("memcachedweaver.client.adaptor.SpymemcachedAdaptor");
config.setAddressesAsString("server1:11211,server2:11211"); // csv
MemcachedClient memcached = MemcachedClientFactory.create(config);

Thread.sleep(300L);
memcached.set("stopped time", 1, new java.util.Date().toString()); // whitespace will be replaced to underscore
Thread.sleep(300L);
assertThat(memcached.get("stopped time"), is(notNullValue())); // "Wed Oct 12 00:01:54 JST 2011"
Thread.sleep(1000L);
assertThat(memcached.get("stopped time"), is(nullValue())); // null
```

### Setup for Spring Framework

"applicationContext.xml" as follows:

```xml
<bean id="memcachedweaverConfiguration" class="memcachedweaver.Configuration">
  <property name="namespace" value="com.example" />
  <property name="adaptorClassName" value="memcachedweaver.client.adaptor.SpymemcachedAdaptor" />
  <!-- <property name="adaptorClassName" value="memcachedweaver.client.adaptor.XmemcachedAdaptor" /> -->
  <property name="addressesAsString" value="lcoalhost:11211,localhost:11212" />
</bean>
<bean id="memcachedInterceptor" class="memcachedweaver.interceptor.MemcachedInterceptor"/>
<aop:config>
  <aop:advisor advice-ref="memcachedInterceptor" pointcut="..."/>
</aop:config>
```

### Setup for Guice

Give thought to use [GuiceyFruit](http://code.google.com/p/guiceyfruit/).

```java
Injector injector = Guice.createInjector(new Jsr250Module() {

  protected void configure() {
    super.configure();
    bind(Configuration.class).in(Singleton.class);
  }

});
```

### Setup by inheritence

It's also possible to inject the configuration to interceptor by inheritence.

```java
public class MyMemcachedInterceptor extends MemcachedInterceptor {

  @Override
  public Configuration getConfiguration() {
    Configuration config = new Configuration();
    config.setNamespace("....");
    ...
    return config;
  }

}
```

### Using AOP

The value will be cached for the duration of 10 seconds.

```java
package service;

import memcachedweaver.annotation.Memcached;

public class DateService {

  @Memcached(secondsToExpire = 10)
  public String getCurrentAsString(String prefix) {
    return prefix + new java.util.Date().toString();
  }

}
```

### The rule of generated cache key

```
"(namespace)::(Method#toGenericString() and replace "\\s+" to "_")::(args separated by comma)"
```

For example:

```java
String result = new DateService().getCurrentAsString("PREFIX");
```

And thn, the returned value will be cached as "com.example::public_void_service.DateService.getCurrentAsString(String)::PREFIX" on memcached.


## License

```java
/*
 * Copyright 2011 Kazuhiro Sera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
```


