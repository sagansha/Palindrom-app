package com.nex.palindrom.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nex.palindrom.entity.Palindrome;
import com.nex.palindrom.exceptions.PalindromeException;
import com.nex.palindrom.service.PalindromeService;

@RestController
@RequestMapping("/palindrome")
@CrossOrigin(origins="*")
public class PalindromeController {

	public static final Logger logger = LoggerFactory.getLogger(PalindromeController.class);

	@Autowired
	PalindromeService palindromeService;

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity<?> listAllUsers() {
		logger.info("Fetching all values...");
		List<Palindrome> listOfAllValues = palindromeService.getAllValues();
		if (listOfAllValues.isEmpty()) {
			logger.info("No data exist!");
			return new ResponseEntity<>(new PalindromeException("No data exist"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listOfAllValues, HttpStatus.OK);

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> addNewUsers(@Valid @RequestBody Palindrome palindrome) {
		logger.info("Adding a new value {}", palindrome.getValue());
		if (!palindromeService.isPalindrome(palindrome)) {
			logger.error("Unable to create. A data with name {} not a palindrome", palindrome.getValue());
			return new ResponseEntity<>(
					new PalindromeException(
							"Unable to create. A data with name " + palindrome.getValue() + " not a palindrome"),
					HttpStatus.NOT_ACCEPTABLE);
		}
		if (palindromeService.isValueExist(palindrome)) {
			logger.error("Unable to create. A data with name {} already exist", palindrome.getValue());
			return new ResponseEntity<>(
					new PalindromeException(
							"Unable to create. A data with name " + palindrome.getValue() + " already exist"),
					HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(palindromeService.addNewUsers(palindrome), HttpStatus.OK);
	}

}
