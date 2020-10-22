package gradle_spring_webmvc_study.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import gradle_spring_webmvc_study.dto.RegisterRequest;

public class RegisterRequesterValidator implements Validator {

	private static final String emailRegExp= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;

	public RegisterRequesterValidator() {
		this.pattern = Pattern.compile(emailRegExp);
	}
	
	// 전달 받은 clazz 객체가 RegisterRequest 클래스의 인스턴스로 변환이 가능한지 검증.
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	/**
	 * @param target : 검사 대상 객체
	 * @param errors : 검사 결과 에러 코드를 설정하기 위한 객체
	 */
	@Override
	public void validate(Object target, Errors errors) {
		RegisterRequest regReq = (RegisterRequest) target;
		
		// 이메일이 null이거나 비어있는 경우, "필수입력"이라는 코드로 reject
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			// 이메일이 값이 있는 경우, 패턴에 맞는지 검사. 맞지 않는 경우 "bad"라는 코드로 reject
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		
		// 패스워드가 입력됐다면, 비밀번호란과 비밀번호 확인란의 입력값이 동일한지 검사. 같지 않다 -> "nomatch"
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}

}
