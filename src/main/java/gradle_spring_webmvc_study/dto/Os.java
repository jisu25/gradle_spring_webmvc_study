package gradle_spring_webmvc_study.dto;

public class Os {
	private String label;
	private String code;
	
	public Os(String label, String code) {
		super();
		this.label = label;
		this.code = code;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return String.format("Os [label=%s, code=%s]", label, code);
	}
	
}
