package memcachedweaver.interceptor;

import memcachedweaver.interceptor.MemcachedInterceptor.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.*;

import memcachedweaver.Configuration;
import memcachedweaver.annotation.Memcached;
import memcachedweaver.client.MemcachedClient;
import memcachedweaver.client.MemcachedClientPool;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

public class MemcachedInterceptorTest {

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
