package kr.co.ureca.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import kr.co.ureca.dto.SignInResultDto;
import kr.co.ureca.dto.SignUpResultDto;
import kr.co.ureca.service.SignService;

@RestController
@RequestMapping("/sign-api")
public class SignController {

	private final SignService signService;

	@Autowired
	public SignController(SignService signService) {
		this.signService = signService;
	}

	@PostMapping("/sign-up")
	public SignUpResultDto signUp(
			@Parameter(description = "아이디", required = true) @RequestParam String id
			, @Parameter(description = "패스워드", required = true) @RequestParam String password
			, @Parameter(description = "이름", required = true) @RequestParam String name
			, @Parameter(description = "역할", required = true) @RequestParam String role) {
		System.out.println("START - SignController - signUp");

		SignUpResultDto signUpResultDto
			= signService.signUp(id, password, name, role);

		System.out.println("END - SignController - signUp");

		return signUpResultDto;
	} // signUp

	@PostMapping("/sign-in")
	public SignInResultDto signIn(
			@Parameter(description = "아이디", required = true) @RequestParam String id
			, @Parameter(description = "패스워드", required = true) @RequestParam String password) {
		System.out.println("START - SignController - signIn");

		SignInResultDto signInResultDto = signService.signIn(id, password);

		if(signInResultDto.getCode() == 0) {
			System.out.println("정상 로그인 id : " + id);
			System.out.println("정상 로그인 token : " + signInResultDto.getToken());
		}

		System.out.println("END - SignController - signIn");

		return signInResultDto;
	}

	@GetMapping("/exception")
	public void exceptionTest() throws RuntimeException {
		throw new RuntimeException("접근이 금지됨.");
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity< Map<String, String> > exceptionHandler( RuntimeException e ) {
		System.out.println("START - SignController - exceptionHandler");

		HttpHeaders responseHeaders = new HttpHeaders();

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", "에러 발생.");

		System.out.println("END - SignController - exceptionHandler");

		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}

}
