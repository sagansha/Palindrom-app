package com.nex.palindrom.service;

import java.util.List;

import com.nex.palindrom.entity.Palindrome;


public interface PalindromeService {

	List<Palindrome> getAllValues();

	boolean isValueExist(Palindrome value);

	List<Palindrome> addNewUsers(Palindrome value);
	
	boolean isPalindrome(Palindrome value);

}
