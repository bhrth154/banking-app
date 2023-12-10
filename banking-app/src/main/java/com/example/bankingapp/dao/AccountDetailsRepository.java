package com.example.bankingapp.dao;

import com.example.bankingapp.entity.AccountDetails;
import com.example.bankingapp.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
    @Modifying
    @Query(value = "DELETE FROM AccountDetails a where a.user.id=:userId")
    void deleteByUserId(@Param("userId") long userId);
    @Query(value = "SELECT a FROM AccountDetails a where a.user.id=:userId")
    Set<AccountDetails> findByUserId(@Param("userId") long userId);
}
