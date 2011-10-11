package memcachedweaver;

import memcachedweaver.Configuration.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import memcachedweaver.client.adaptor.MemcachedClientAdaptor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
public class ConfigurationTest {

	@Test
	public void type() throws Exception {
		assertThat(Configuration.class, notNullValue());
	}

	@Test
	public void instantiation() throws Exception {
		Configuration target = new Configuration();
		assertThat(target, notNullValue());
	}

	@Test
	public void loadConfigFromProperties_A$() throws Exception {
		Configuration target = new Configuration();
		target.loadConfigFromProperties();
	}

	@Test
	public void getAdaptorClass_A$() throws Exception {
		Configuration target = new Configuration();
		Object actual = target.getAdaptorClass();
		Object expected = null;
		assertThat(actual, is(equalTo(expected)));
	}

	@Test
	public void setAdaptorClassName_A$String() throws Exception {
		Configuration target = new Configuration();
		String adaptorClassName = "memcachedweaver.client.adaptor.SpymemcachedAdaptor";
		target.setAdaptorClassName(adaptorClassName);
	}

	@Test
	public void setAddressesAsString_A$String() throws Exception {
		Configuration target = new Configuration();
		String addresses = "localhost:11211,localhost:11212";
		target.setAddressesAsString(addresses);
	}

	@Test
	public void setAddressesAsStringArray_A$StringArray() throws Exception {
		Configuration target = new Configuration();
		String[] addresses = new String[] {};
		target.setAddressesAsStringArray(addresses);
	}

}
