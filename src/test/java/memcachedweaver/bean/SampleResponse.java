package memcachedweaver.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class SampleResponse implements Serializable {

	static final long serialVersionUID = 1L;

	private String code;

	private SampleBean sampleBean;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public SampleBean getSampleBean() {
		return sampleBean;
	}

	public void setSampleBean(SampleBean sampleBean) {
		this.sampleBean = sampleBean;
	}
}
