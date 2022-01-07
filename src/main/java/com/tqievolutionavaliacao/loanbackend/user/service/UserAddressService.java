package com.tqievolutionavaliacao.loanbackend.user.service;

import com.tqievolutionavaliacao.loanbackend.user.model.UserAddress;
import com.tqievolutionavaliacao.loanbackend.user.repository.UserAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;

    public UserAddress registerAddress(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }
}
