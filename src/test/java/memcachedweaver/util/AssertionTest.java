package memcachedweaver.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AssertionTest {

	@Test
	public void type() throws Exception {
		assertThat(Assertion.class, notNullValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void notNullValue_A$String$Object_failed() throws Exception {
		// given
		String name = "aaa";
		Object value = null;
		Assertion.notNullValue(name, value);
	}

	@Test
	public void notNullValue_A$String$Object_success() throws Exception {
		// given
		String name = "aaa";
		Object value = new Object();
		Assertion.notNullValue(name, value);
	}

}
