package gradle_spring_webmvc_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import gradle_spring_webmvc_study.dto.Member;
import gradle_spring_webmvc_study.exception.MemberNotFoundException;
import gradle_spring_webmvc_study.spring.MemberDao;

@Component
public class ChangePasswordService {
	@Autowired
	private MemberDao memberDao;
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		try {
			Member member = memberDao.selectByEmail(email);
			member.changePassword(oldPwd, newPwd);
			memberDao.update(member);
		} catch (EmptyResultDataAccessException e) {
			throw new MemberNotFoundException();
		}
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}
