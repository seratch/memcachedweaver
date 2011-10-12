package memcachedweaver.client.impl;

import memcachedweaver.Configuration;
import memcachedweaver.bean.SampleBean;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SpymemcachedClientImplTest {

	SpymemcachedClientImpl memcached = new SpymemcachedClientImpl();
	Configuration config = null;

	String namespace = "memcachedweaver.testing";

	List<InetSocketAddress> addresses = null;

	@Before
	public void setUp() throws Exception {
		if (config == null) {
			config = new Configuration();
			config.loadConfigFromProperties();
		}
		memcached.initialize(config.getAddresses());
		addresses = config.getAddresses();
	}

	@Test
	public void type() throws Exception {
		assertThat(SpymemcachedClientImpl.class, notNullValue());
	}

	@Test
	public void instantiation() throws Exception {
		SpymemcachedClientImpl target = new SpymemcachedClientImpl();
		assertThat(target, notNullValue());
	}

	@Test
	public void waitForConnectionReady_A$() throws Exception {
		memcached.waitForConnectionReady();
	}

	@Test
	public void initialize_A$List() throws Exception {
		memcached.initialize(addresses);
	}

	@Test
	public void initialize_A$List$String() throws Exception {
		memcached.initialize(addresses, namespace);
	}

	@Test
	public void getNamespace_A$() throws Exception {
		String actual = memcached.getNamespace();
		String expected = "default";
		assertThat(actual, is(equalTo(expected)));
	}

	@Test
	public void setNamespace_A$String() throws Exception {
		memcached.setNamespace(namespace);
	}

	@Test
	public void set_A$String$int$Object() throws Exception {
		String key = "something";
		int secondsToExpire = 0;
		Object value = new SampleBean();
		memcached.set(key, secondsToExpire, value);
	}

	@Test
	public void get_A$String() throws Exception {
		String key = "something";
		int secondsToExpire = 1;
		SampleBean value = new SampleBean();
		value.name = "foo";
		memcached.set(key, secondsToExpire, value);
		SampleBean actual = memcached.get(key);
		assertThat(actual.name, is(equalTo(value.name)));
	}

	@Test
	public void setAndEnsure_A$String$int$Object() throws Exception {
		String key = "setAndEnsure_A$String$int$Object";
		int secondsToExpire = 3;
		SampleBean value = new SampleBean();
		memcached.setAndEnsure(key, secondsToExpire, value);
	}

}
