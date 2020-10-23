package gradle_spring_webmvc_study.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gradle_spring_webmvc_study.dto.AuthInfo;
import gradle_spring_webmvc_study.dto.Code;
import gradle_spring_webmvc_study.dto.Login;
import gradle_spring_webmvc_study.dto.LoginCommand;
import gradle_spring_webmvc_study.dto.Os;
import gradle_spring_webmvc_study.exception.WrongIdPasswordException;
import gradle_spring_webmvc_study.service.AuthService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthService authService;
	
	@GetMapping
	public String formGet(LoginCommand loginCommand/*, Model model*/) {
//		login.setLoginType("기업회원");
//		login.setJobCode("B");
//		model.addAttribute("loginTypes", loginTypes);
//		ModelAndView mav = new ModelAndView("login/loginForm", "loginTypes", loginTypes);
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenicate(loginCommand.getEmail(), loginCommand.getPassword());
			session.setAttribute("authInfo", authInfo);
			return "login/loginSuccess";
		} catch (WrongIdPasswordException ex) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
	
	@ModelAttribute("jobCodes")
	public List<Code> getJobCodes() {
		List<Code> jobCodes = new ArrayList<>();
		jobCodes.add(new Code("A", "사용자"));
		jobCodes.add(new Code("B", "개발자"));
		jobCodes.add(new Code("C", "관리자"));
		return jobCodes;
	}
	
	@ModelAttribute("loginTypes")
	public List<String> getLogin(){
		List<String> loginTypes= new ArrayList<>();
		loginTypes.add("일반회원");
		loginTypes.add("기업회원");
		loginTypes.add("헤드헌터회원");
		return loginTypes;
	}
	
	@ModelAttribute("tools")
	public List<String> getTools(){
		List<String> tools= new ArrayList<>();
		tools.add("Eclipse");
		tools.add("IntelliJ");
		tools.add("VSCode");
		return tools;
	}
	
	@ModelAttribute("favoriteOsNames")
	public List<String> getFavoriteOsNames(){
		List<String> os= new ArrayList<>();
		os.add("Windows");
		os.add("Mac Os");
		os.add("Linux");
		return os;
	}
	
	@ModelAttribute("loveOsNames")
	public List<Os> getLoveOsNames(){
		List<Os> os= new ArrayList<>();
		os.add(new Os("Windows","O1"));
		os.add(new Os("Mac Os", "O2"));
		os.add(new Os("Linux", "O3"));
		return os;
	}
	
    @ModelAttribute("subjects")
    public List<Code> getSubjects(){
        List<Code> subjects = new ArrayList<>();
        subjects.add(new Code("S1", "윈도우10"));
        subjects.add(new Code("S2", "리눅스"));
        subjects.add(new Code("S3", "유닉스"));
        subjects.add(new Code("S4", "칼리리눅스"));
        subjects.add(new Code("S5", "우분투"));
        return subjects;
    }
}
