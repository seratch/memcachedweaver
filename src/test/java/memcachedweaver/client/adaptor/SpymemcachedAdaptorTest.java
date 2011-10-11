package memcachedweaver.client.adaptor;

import memcachedweaver.client.adaptor.SpymemcachedAdaptor.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.BDDMockito.*;

import memcachedweaver.client.impl.ClientImpl;
import memcachedweaver.client.impl.SpymemcachedClientImpl;
public class SpymemcachedAdaptorTest {

	@Test
	public void type() throws Exception {
		assertThat(SpymemcachedAdaptor.class, notNullValue());
	}

	@Test
	public void instantiation() throws Exception {
		SpymemcachedAdaptor target = new SpymemcachedAdaptor();
		assertThat(target, notNullValue());
	}

	@Test
	public void getClientImplClass_A$() throws Exception {
		SpymemcachedAdaptor target = new SpymemcachedAdaptor();
		Object actual = target.getClientImplClass();
		Object expected = SpymemcachedClientImpl.class;
		assertThat(actual, is(equalTo(expected)));
	}

}
