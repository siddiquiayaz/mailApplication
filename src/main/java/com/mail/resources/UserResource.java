package com.mail.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.domain.User;
import com.mail.response.HttpResponse;
import com.mail.service.UserEmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {
	private final UserEmailService userService;

	@PostMapping
	public ResponseEntity<HttpResponse> creatUser(@RequestBody User user) {
		User newUser = userService.saveUser(user);
		return ResponseEntity.created(URI.create(""))
				.body(HttpResponse.builder().timeStamp(LocalDateTime.now().toString())
						.date(Map.of("user", newUser))
						.message("user created").status(HttpStatus.CREATED)
						.statusCode(HttpStatus.CREATED.value())
						.build()

				);
	}
      @GetMapping
	public ResponseEntity<HttpResponse> confirmationUserAccount(@RequestParam("token") String token) {
		Boolean isSucces = userService.veryfyToken(token);
		return ResponseEntity.created(URI.create(""))
				.body(HttpResponse.builder().timeStamp(LocalDateTime.now().toString())
						.date(Map.of("success", isSucces))
						.message("Account vrified").status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value()).build()

				);
	}

}
