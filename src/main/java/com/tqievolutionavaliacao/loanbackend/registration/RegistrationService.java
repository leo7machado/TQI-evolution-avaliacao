package com.tqievolutionavaliacao.loanbackend.registration;

import com.tqievolutionavaliacao.loanbackend.user.model.User;
import com.tqievolutionavaliacao.loanbackend.user.model.UserAddress;
import com.tqievolutionavaliacao.loanbackend.user.repository.UserRepository;
import com.tqievolutionavaliacao.loanbackend.user.service.UserAddressService;
import com.tqievolutionavaliacao.loanbackend.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final UserAddressService userAddressService;
    private final UserRepository userRepository;

    public String registration(RegistrationRequest request) {


        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getCpf(),
                request.getRg(),
                request.getIncome());

        UserAddress userAddress = new UserAddress(
                request.getState(),
                request.getNumber(),
                request.getCity(),
                request.getStreet(),
                request.getCep());

        user.setUserAddress(userAddress);
        userAddress.setUser(user);

        userService.registerUser(user);
        userAddressService.registerAddress(userAddress);


        return "User created";
    }
}
