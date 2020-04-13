package com.example.spring_study.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_study.member.Member;
import com.example.spring_study.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService{

	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	@Autowired
	MemberDao dao;
	
	@Override
	public void memberRegister(Member member) {
		int result = dao.memberInsert(member);
		
		if(result == 0) {
			logger.info("Join Fail");
		} else {
			logger.info("Join Success");
		}
	}
	
	@Override
	public Member memberSearch(Member member) {
		
		Member mem = dao.memberSelect(member);
		
		if(mem == null) {
			logger.info("Login Fail!!");
		} else {
			logger.info("Login Success");
		}
		
		return mem;
	}

	@Override
	public Member memberModify(Member member) {
		// TODO Auto-generated method stub
		
		int result = dao.memberUpdate(member);
		
		if(result == 0) {
			logger.info("Modify Fail");
		} else {
			logger.info("Modify Success");
		}
		
		return member;
	}

	@Override
	public int memberRemove(Member member) {
		// TODO Auto-generated method stub
		int result = dao.memberDelete(member);
		
		if(result == 0) {
			logger.info("Remove Fail");
		} else {
			logger.info("Remove Success");
		}
		
		return result;
	}
		
}
