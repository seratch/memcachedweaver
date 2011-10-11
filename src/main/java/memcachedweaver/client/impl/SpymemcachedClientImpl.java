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
package memcachedweaver.client.impl;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import static memcachedweaver.util.Assertion.notNullValue;

public class SpymemcachedClientImpl extends ClientImplBase {

	private MemcachedClient memcached;

	void waitForConnectionReady() {
		// If no wait, you will see the following warning log message...
		// WARN net.spy.memcached.MemcachedConnection: Could not redistribute to
		// another node, retrying primary node for xxx
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public void initialize(List<InetSocketAddress> addresses)
			throws IOException {
		notNullValue("addresses", addresses);
		memcached = new MemcachedClient(addresses);
		waitForConnectionReady();
	}

	@Override
	public void initialize(List<InetSocketAddress> addresses, String namespace)
			throws IOException {
		notNullValue("addresses", addresses);
		memcached = new MemcachedClient(addresses);
		setNamespace(namespace);
		waitForConnectionReady();
	}

	@Override
	public <T> void set(String key, int secondsToExpire, T value)
			throws Exception {
		notNullValue("key", key);
		memcached.set(getKey(key), secondsToExpire, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String key) throws Exception {
		notNullValue("key", key);
		return (T) memcached.get(getKey(key));
	}


}
