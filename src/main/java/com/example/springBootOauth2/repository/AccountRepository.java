package com.example.springBootOauth2.repository;

import com.example.springBootOauth2.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    @Override
    Optional<Account> findById(Integer integer);

    Optional<Account>  findByUsername(String username);
}
