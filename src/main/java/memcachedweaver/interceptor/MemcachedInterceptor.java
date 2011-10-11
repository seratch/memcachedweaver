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
package memcachedweaver.interceptor;

import memcachedweaver.Configuration;
import memcachedweaver.annotation.Memcached;
import memcachedweaver.client.MemcachedClient;
import memcachedweaver.client.MemcachedClientPool;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Resource;
import java.lang.reflect.Method;

public class MemcachedInterceptor implements MethodInterceptor {

	/**
	 * If necessary, override this to provider {@link Configuration} instance.
	 */
	public Configuration getConfiguration() {
		return null;
	}

	@Resource
	private Configuration config = getConfiguration();

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method invokedMethod = invocation.getMethod();
		Memcached annotation = invokedMethod.getAnnotation(Memcached.class);
		if (annotation != null) {
			MemcachedClient memcached = MemcachedClientPool
					.getMemcachedClient(config);
			String cacheKey = annotation.cacheKey().equals("") ? getCacheKey(invocation)
					: annotation.cacheKey();
			Object cachedObject = memcached.get(cacheKey);
			if (cachedObject != null) {
				return cachedObject;
			} else {
				Object value = invocation.proceed();
				memcached.set(cacheKey, annotation.secondsToExpire(), value);
				return value;
			}
		}
		return invocation.proceed();
	}

	String getCacheKey(MethodInvocation invocation) {
		Object[] args = invocation.getArguments();
		StringBuilder argsBuffer = new StringBuilder();
		if (args != null) {
			for (Object arg : args) {
				argsBuffer.append(arg);
				argsBuffer.append(",");
			}
		}
		Method invokedMethod = invocation.getMethod();
		return invokedMethod.toGenericString() + "::" + argsBuffer.toString();
	}

}
