package com.nex.palindrom.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nex.palindrom.entity.Palindrome;

@Repository
public interface PalindromeRepository extends CrudRepository<Palindrome,String> {

}
