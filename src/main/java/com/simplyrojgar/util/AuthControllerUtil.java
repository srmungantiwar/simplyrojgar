package com.simplyrojgar.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource;
import com.simplyrojgar.dto.SignUpDto;
import com.simplyrojgar.repository.UserRepository;

public class AuthControllerUtil {
	@Autowired
	private static UserRepository userRepository;

	public static ResponseEntity<?> validate(SignUpDto signUpDto) {
		if (null == signUpDto) {
			return new ResponseEntity<>("Request Payload should not be Empty!", HttpStatus.BAD_REQUEST);
		}

		if (StringUtils.isBlank(signUpDto.getName())) {
			return new ResponseEntity<>("Name should not be Empty!", HttpStatus.BAD_REQUEST);
		}

		if (StringUtils.isBlank(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email should not be Empty!", HttpStatus.BAD_REQUEST);
		}

		if (StringUtils.isBlank(signUpDto.getPhoneNumber())) {
			return new ResponseEntity<>("Phone Number should not be Empty!", HttpStatus.BAD_REQUEST);
		}
		// add check for username exists in a DB
		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// add check for email exists in DB
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}

		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phone = null;
		try {
			phone = phoneNumberUtil.parse(signUpDto.getPhoneNumber(), CountryCodeSource.UNSPECIFIED.name());
		} catch (NumberParseException e) {
			e.printStackTrace();
		}
		if (!phoneNumberUtil.isValidNumber(phone)) {
			return new ResponseEntity<>("Invalid Phone Number!", HttpStatus.BAD_REQUEST);
		}
		return null;

	}

}
