package gradle_spring_webmvc_study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gradle_spring_webmvc_study.dto.RegisterRequest;
import gradle_spring_webmvc_study.exception.DuplicateMemberException;
import gradle_spring_webmvc_study.service.MemberRegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@GetMapping("/step1")
	public String handleStep1() {
		return "/register/step1";
	}
	
	@PostMapping("/step2")
	public String handleStep2(@RequestParam(value="agree", defaultValue = "false") boolean agree, Model model) {
		if(!agree) {
			return "redirect:/register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "/register/step2";
	}
	
	@GetMapping("/step2")
	public String handleStep2() {
		return "redirect:/register/step1";
	}
	
	@PostMapping("/step3")
	public String handleStep3Get(RegisterRequest regReq, Errors errors) {
		new RegisterRequesterValidator().validate(regReq, errors);
		if(errors.hasErrors()) {
			return "register/step2";
		}
		try {
			memberRegisterService.regist(regReq);
			return "/register/step3";
		} catch(DuplicateMemberException ex) {
			return "register/step2";
		}
	}
}