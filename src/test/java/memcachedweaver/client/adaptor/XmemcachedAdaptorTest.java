package memcachedweaver.client.adaptor;

import memcachedweaver.client.impl.XmemcachedClientImpl;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class XmemcachedAdaptorTest {

	@Test
	public void type() throws Exception {
		assertThat(XmemcachedAdaptor.class, notNullValue());
	}

	@Test
	public void instantiation() throws Exception {
		XmemcachedAdaptor target = new XmemcachedAdaptor();
		assertThat(target, notNullValue());
	}

	@Test
	public void getClientImplClass_A$() throws Exception {
		XmemcachedAdaptor target = new XmemcachedAdaptor();
		Object actual = target.getClientImplClass();
		Object expected = XmemcachedClientImpl.class;
		assertThat(actual, is(equalTo(expected)));
	}

}
