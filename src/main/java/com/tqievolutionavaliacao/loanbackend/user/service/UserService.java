package com.tqievolutionavaliacao.loanbackend.user.service;

import com.tqievolutionavaliacao.loanbackend.user.model.UserRole;
import com.tqievolutionavaliacao.loanbackend.user.model.User;
import com.tqievolutionavaliacao.loanbackend.user.repository.UserAddressRepository;
import com.tqievolutionavaliacao.loanbackend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserAddressService userAddressService;
    private final UserAddressRepository userAddressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }

    public User registerUser(User user) {
        boolean present = userRepository.findByEmail(user.getEmail()).isPresent();

        if (present){
            throw new IllegalStateException("Email already exist");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.USER);


        return userRepository.save(user);

    }

}
