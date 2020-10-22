package gradle_spring_webmvc_study.dto;

import java.util.List;

public class Login {

	private String loginType;
	private String jobCode;
	private String tool;
	private List<String> favoriteOs;
	private List<String> loveOs;
	private List<Code> subjects;

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public List<String> getFavoriteOs() {
		return favoriteOs;
	}

	public void setFavoriteOs(List<String> favoriteOs) {
		this.favoriteOs = favoriteOs;
	}

	public List<String> getLoveOs() {
		return loveOs;
	}

	public void setLoveOs(List<String> loveOs) {
		this.loveOs = loveOs;
	}

	public List<Code> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Code> subject) {
		this.subjects = subject;
	}

	@Override
	public String toString() {
		return String.format("Login [loginType=%s, jobCode=%s, tool=%s, favoriteOs=%s, loveOs=%s, subjects=%s]",
				loginType, jobCode, tool, favoriteOs, loveOs, subjects);
	}

}
