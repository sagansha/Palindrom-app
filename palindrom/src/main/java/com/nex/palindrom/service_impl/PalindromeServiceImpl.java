package com.nex.palindrom.service_impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nex.palindrom.entity.Palindrome;
import com.nex.palindrom.repo.PalindromeRepository;
import com.nex.palindrom.service.PalindromeService;

@Service
public class PalindromeServiceImpl implements PalindromeService {

	@Autowired
	PalindromeRepository palindromeRepo;

	@Override
	public List<Palindrome> getAllValues() {
		return (List<Palindrome>) palindromeRepo.findAll();
	}

	@Override
	public boolean isValueExist(Palindrome palindrome) {
		return palindromeRepo.findById(palindrome.getValue()).isPresent();
	}

	@Override
	public List<Palindrome> addNewUsers(Palindrome palindrome) {
		palindromeRepo.save(palindrome);
		return getAllValues();
	}

	@Override
	public boolean isPalindrome(Palindrome palindrome) {
		int length = palindrome.getValue().length();
		String pString = palindrome.getValue().toLowerCase();
		for(int i = 0; i<length/2;i++) {
			if(pString.charAt(i) != pString.charAt(length-1-i)) {
				return false;
			}
		}
		return true;
	}

}
