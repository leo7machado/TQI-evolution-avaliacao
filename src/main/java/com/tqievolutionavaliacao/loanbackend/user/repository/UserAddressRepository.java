package com.tqievolutionavaliacao.loanbackend.user.repository;

import com.tqievolutionavaliacao.loanbackend.user.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
