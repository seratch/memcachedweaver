# memcachedweaver: Caching methods result on memcached with AOP

## Getting Started

### Maven

```xml
<repositories>
  <repository>
    <id>seratch.github.com releases</id>
    <url>http://seratch.github.com/mvn-repo/releases</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.seratch</groupId>
    <artifactId>memcachedweaver</artifactId>
    <version>0.1</version>
  </dependency>
</dependencies>
```

## Usage

### Using as a wrapper API for memcached clients

memcachedweaver provides a pluggable wrapper API for memcached clients and developers can access memcached servers transparently.

Currently memcachedweaver supports following 2 libraries, and it's also possible to add new adaptors.

#### memcacheweaver.client.adaptor.SpymemcachedAdaptor

  for http://code.google.com/p/spymemcached/

#### memcacheweaver.client.adaptor.XmemcachedAdaptor

  for http://code.google.com/p/xmemcached/

```java
Configuration config = new Configuration();
config.setAdaptorClassName("memcachedweaver.client.adaptor.SpymemcachedAdaptor");
config.setAddressesAsString("server1:11211,server2:11211"); // csv format
MemcachedClient memcached = MemcachedClientFactory.create(config);

Thread.sleep(500L);
String toBeCached = new java.util.Date().toString();
memcached.set("stopped time", 1, toBeCached); // whitespace in cache key will be replaced to underscore
Thread.sleep(500L);
assertThat(memcached.get("stopped time"), is(equalTo(toBeCached))); // "Wed Oct 12 00:01:54 JST 2011"
Thread.sleep(1000L);
assertThat(memcached.get("stopped time"), is(nullValue())); // null
```

### Setup for Spring Framework

"applicationContext.xml" as follows:

```
<bean id="memcachedweaverConfiguration" class="memcachedweaver.Configuration">
  <property name="namespace" value="com.example" />
  <property name="adaptorClassName" value="memcachedweaver.client.adaptor.SpymemcachedAdaptor" />
  <property name="addressesAsString" value="lcoalhost:11211,localhost:11212" />
</bean>

<bean id="memcachedInterceptor" class="memcachedweaver.interceptor.MemcachedInterceptor"/>

<aop:config>
  <aop:advisor advice-ref="memcachedInterceptor" pointcut="..."/>
</aop:config>
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


