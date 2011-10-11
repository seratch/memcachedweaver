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

### Setup for Spring Framework

#### applicationContext.xml

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

### using method anntation

The value will be cached for the duration of 10 seconds.

```java
import memcachedweaver.annotation.Memcached;

public class DateService {

	@Memcached(secondsToExpire = 10)
	public String getCurrentAsString() {
		return new java.util.Date().toString();
	}

}
```


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