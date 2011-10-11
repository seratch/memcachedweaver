package memcachedweaver.client;

import java.util.ArrayList;

import memcachedweaver.Configuration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import memcachedweaver.bean.SampleBean;
import memcachedweaver.client.adaptor.SpymemcachedAdaptor;
import memcachedweaver.client.impl.SpymemcachedClientImpl;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.*;

import memcachedweaver.client.adaptor.MemcachedClientAdaptor;
import memcachedweaver.client.impl.ClientImpl;

import java.net.InetSocketAddress;
import java.util.List;

public class MemcachedClientTest {

	Configuration config = null;
	InetSocketAddress address = null;

	@Before
	public void setUp() throws Exception {
		if (config == null) {
			config = new Configuration();
			config.loadConfigFromProperties();
		}
		address = config.getAddress();
	}

	@Test
	public void type() throws Exception {
		assertThat(MemcachedClient.class, notNullValue());
	}

	@Test
	public void instantiation() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient target = new MemcachedClient(clientAdaptor);
		assertThat(target, notNullValue());
	}

	@Test
	public void getClientImpl_A$() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient target = new MemcachedClient(clientAdaptor);
		ClientImpl actual = target.getClientImpl();
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void initialize_A$InetSocketAddress() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient target = new MemcachedClient(clientAdaptor);
		target.initialize(address);
	}

	@Test
	public void initialize_A$InetSocketAddress$String() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient target = new MemcachedClient(clientAdaptor);
		String namespace = null;
		target.initialize(address, namespace);
	}

	@Test
	public void initialize_A$List() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient target = new MemcachedClient(clientAdaptor);
		List<InetSocketAddress> addresses = new ArrayList<InetSocketAddress>();
		addresses.add(address);
		target.initialize(addresses);
	}

	@Test
	public void initialize_A$List$String() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient target = new MemcachedClient(clientAdaptor);
		List<InetSocketAddress> addresses = new ArrayList<InetSocketAddress>();
		addresses.add(address);
		String namespace = null;
		target.initialize(addresses, namespace);
	}

	@Test
	public void get_A$String() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient target = new MemcachedClient(clientAdaptor);
		target.initialize(address);
		String key = "not_exist";
		Object actual = target.get(key);
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void set_A$String$int$Object() throws Exception {
		MemcachedClientAdaptor clientAdaptor = new SpymemcachedAdaptor();
		MemcachedClient target = new MemcachedClient(clientAdaptor);
		target.initialize(address);
		String key = "something";
		int secondsToExpire = 1;
		SampleBean value = new SampleBean();
		target.set(key, secondsToExpire, value);
	}

}
