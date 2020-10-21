package gradle_spring_webmvc_study.survey;

import java.util.List;

public class Question {
	private String title;
	private List<String> options;
	
	
	public Question(String title, List<String> options) {
		super();
		this.title = title;
		this.options = options;
	}
	
	public Question(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public List<String> getOptions() {
		return options;
	}
	
	// 질문이 객관식이냐 주관식이냐 체크 (true: 객관식, false: 주관식)
	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
