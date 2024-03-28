package com.aqazadeh.intagramclone.repository;

import com.aqazadeh.intagramclone.model.ConfirmationToken;
import com.aqazadeh.intagramclone.model.enums.ConfirmationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 16.03.2024
 * Time: 00:04
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {


    Optional<ConfirmationToken> findByTokenAndType(String token, ConfirmationType type);
}
