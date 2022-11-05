//package com.bbms.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.bbms.repository.UserRepository;
//
//@Service
//public class CheckEmailService {
//
//	@Autowired
//	UserRepository repo;
//	//check duplicate email.
//	public boolean emailExists(long id, String email) {
//		try {			
//			long idByEmail =repo.checkEmail(email); 
//			if(idByEmail!=0) {				
//				if(id==idByEmail) {return false;}
//				else {return true;}
//			}
//			return false;
//		}catch(Exception e) {
//			return false;
//		}
//	}
//}
