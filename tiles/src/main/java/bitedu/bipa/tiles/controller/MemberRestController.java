package bitedu.bipa.tiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitedu.bipa.tiles.service.MemberService;

@RestController
@RequestMapping("member/restful")
public class MemberRestController {
	
	private MemberService service;
	
	@Autowired
	public MemberRestController(MemberService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> validationId(@RequestParam("userId") String userId) {
		boolean flag = service.validationUserId(userId);
		if(flag) {
			return new ResponseEntity<String>("available",HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("duplicated",HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/{userId}", method =RequestMethod.DELETE)
	public ResponseEntity<String> removeUser(@PathVariable("userId") String userId) {
		
		//System.out.println(userId);
		
		boolean flag = service.removeUser(userId);
		if(flag) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.OK);
		}
		
		
	}
	

}
