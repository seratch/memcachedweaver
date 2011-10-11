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
package memcachedweaver.client;

import memcachedweaver.Configuration;
import memcachedweaver.client.adaptor.MemcachedClientAdaptor;
import memcachedweaver.util.Assertion;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MemcachedClientPool {

	private static final String DEFAULT_CLIENT_ADAPTOR_NAME = "memcachedweaver.client.adaptor.SpymemcachedAdaptor";

	private MemcachedClientPool() {
	}

	private static final Map<String, MemcachedClient> CACHED_CLIENTS = new ConcurrentHashMap<String, MemcachedClient>();

	@SuppressWarnings("unchecked")
	public static MemcachedClient getMemcachedClient(Configuration config)
			throws Exception {

		if (config == null) {
			config = new Configuration();
			config.loadConfigFromProperties();
		}
		String namespace = config.getNamespace();
		if (namespace == null) {
			config.setNamespace(MemcachedClient.DEFAULT_NAMESPACE);
		}

		// cached client instance
		MemcachedClient memcached = CACHED_CLIENTS.get(namespace);
		if (memcached != null) {
			return memcached;
		}

		// create new client instance
		Class<? extends MemcachedClientAdaptor> adaptorClass = config.getAdaptorClass();
		if (adaptorClass == null) {
			adaptorClass = (Class<? extends MemcachedClientAdaptor>) Class.forName(DEFAULT_CLIENT_ADAPTOR_NAME);
		}
		memcached = new MemcachedClient(config.getAdaptorClass().newInstance());
		Assertion.notNullValue("config.getAddresses()", config.getAddresses());
		memcached.initialize(config.getAddresses(), namespace);
		CACHED_CLIENTS.put(namespace, memcached);
		return memcached;
	}

}
