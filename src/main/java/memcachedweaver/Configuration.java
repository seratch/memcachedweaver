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
package memcachedweaver;

import memcachedweaver.client.MemcachedClient;
import memcachedweaver.client.adaptor.MemcachedClientAdaptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Configuration {

	public static final String PROPERTIES_FILENAME = "memcachedweaver.properties";
	public static final String KEY_CLIENT_ADAPTOR_CLASSNAME = "memcachedweaver.client.adaptorClassname";
	public static final String KEY_CLIENT_SERVER_ADDRESSES = "memcachedweaver.server.addresses";

	public void loadConfigFromProperties() throws IOException,
			ClassNotFoundException {
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILENAME));

		setAdaptorClassName(props.getProperty(KEY_CLIENT_ADAPTOR_CLASSNAME));

		String addresses = props.getProperty(KEY_CLIENT_SERVER_ADDRESSES);
		if (addresses != null) {
			setAddressesAsString(addresses);
		}
	}

	private Class<? extends MemcachedClientAdaptor> adaptorClass;

	private List<InetSocketAddress> addresses;

	private String namespace = MemcachedClient.DEFAULT_NAMESPACE;

	public Class<? extends MemcachedClientAdaptor> getAdaptorClass() {
		return adaptorClass;
	}

	public void setAdaptorClass(
			Class<? extends MemcachedClientAdaptor> adaptorClass) {
		this.adaptorClass = adaptorClass;
	}

	@SuppressWarnings("unchecked")
	public void setAdaptorClassName(String adaptorClassName)
			throws ClassNotFoundException {
		this.adaptorClass = (Class<? extends MemcachedClientAdaptor>) Class
				.forName(adaptorClassName);
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public InetSocketAddress getAddress() {
		return getAddress(0);
	}

	public InetSocketAddress getAddress(int idx) {
		return addresses.get(idx);
	}

	public void setAddress(InetSocketAddress address) {
		this.addresses.add(address);
	}

	public List<InetSocketAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<InetSocketAddress> addresses) {
		this.addresses = addresses;
	}

	public void setAddressesAsString(String addresses) {
		setAddressesAsStringArray((addresses != null) ? addresses.split(",")
				: new String[]{addresses});
	}

	public void setAddressesAsStringArray(String[] addresses) {
		this.addresses = new ArrayList<InetSocketAddress>();
		for (String address : addresses) {
			String[] splitted = address.split(":");
			this.addresses.add(new InetSocketAddress(splitted[0], Integer
					.valueOf(splitted[1])));
		}
	}

}
