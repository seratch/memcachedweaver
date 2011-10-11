package memcachedweaver.client;

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
	public void create_A$MemcachedClientAdaptor() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient actual = MemcachedClientFactory.create(clientAdaptor);
		assertThat(actual, is(notNullValue()));
	}

}
