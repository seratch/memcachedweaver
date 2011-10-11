package memcachedweaver.client;

import memcachedweaver.Configuration;
import memcachedweaver.client.MemcachedClientFactory.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import memcachedweaver.client.adaptor.SpymemcachedAdaptor;
import org.junit.Test;

import static org.mockito.BDDMockito.*;

import memcachedweaver.client.adaptor.MemcachedClientAdaptor;

public class MemcachedClientFactoryTest {

	@Test
	public void type() throws Exception {
		assertThat(MemcachedClientFactory.class, notNullValue());
	}

	@Test
	public void create_A$Configuration_XmemcachedAdaptor() throws Exception {
		Configuration config = new Configuration();
		config.setAdaptorClassName("memcachedweaver.client.adaptor.XmemcachedAdaptor");
		config.setAddressesAsString("localhost:11211");
		MemcachedClient memcached = MemcachedClientFactory.create(config);
		Thread.sleep(300L);
		memcached.set("time", 1, new java.util.Date().toString());
		Thread.sleep(300L);
		assertThat(memcached.get("time"), is(notNullValue()));
		Thread.sleep(1000L);
		assertThat(memcached.get("time"), is(nullValue()));
	}

}
