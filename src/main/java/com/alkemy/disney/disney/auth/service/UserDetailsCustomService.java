package com.alkemy.disney.disney.auth.service;

import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.entity.UserEntity;
import com.alkemy.disney.disney.auth.repository.UserRepository;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username or password not found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }
    
    public boolean save(UserDTO userDTO) {
    
        if (userDTO.getUsername().isBlank() || userDTO.getUsername() == null) {
            throw new InvalidDTOException("Enter a username");
        }
    
        if (userDTO.getPassword().isBlank() || userDTO.getPassword() == null) {
            throw new InvalidDTOException("Enter a password");
        }
        
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new InvalidDTOException("There is already a registered user with this email address");
        }
        
        UserEntity userEntity = new UserEntity();
        
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        
        userEntity = this.userRepository.save(userEntity);
        
        emailService.sendWelcomeEmailTo(userEntity.getUsername());
        
        /*Replaced by true because if userEntity where null the method would never return anything*/
        /*return userEntity != null;*/
        return true;
    }
}
