package memcachedweaver.interceptor;

import memcachedweaver.Configuration;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class MemcachedInterceptorTest {

	Configuration config = null;
	InetSocketAddress address = null;

	@Before
	public void setUp() throws Exception {
		if (config == null) {
			config = new Configuration();
			config.loadConfigFromProperties();
		}
		address = config.getAddresses().get(0);
	}

	@Test
	public void type() throws Exception {
		assertThat(MemcachedInterceptor.class, notNullValue());
	}

	@Test
	public void instantiation() throws Exception {
		MemcachedInterceptor target = new MemcachedInterceptor();
		assertThat(target, notNullValue());
	}

	@Test
	public void getCacheKey_A$MethodInvocation() throws Exception {
		MemcachedInterceptor target = new MemcachedInterceptor();
		MethodInvocation invocation = mock(MethodInvocation.class);
		Method method = this.getClass().getMethod("getCacheKey_A$MethodInvocation", (Class<?>[]) null);
		when(invocation.getMethod()).thenReturn(method);
		String actual = target.getCacheKey(invocation);
		String expected = "public void memcachedweaver.interceptor.MemcachedInterceptorTest.getCacheKey_A$MethodInvocation() throws java.lang.Exception::";
		assertThat(actual, is(equalTo(expected)));
	}

}
