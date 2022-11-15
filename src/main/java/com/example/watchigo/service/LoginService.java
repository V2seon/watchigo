package com.example.watchigo.service;

import com.example.watchigo.entity.UserEntity;
import com.example.watchigo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LoginService {
    private UserRepository userRepository;
    @Transactional
    public int loginAdmin(String userid, String userpw){
        int returnValue = 0;
        Optional<UserEntity> optionalAdminEntity = userRepository.findByAidAndApw(userid, userpw);
        if(!optionalAdminEntity.isPresent()){
            returnValue = 0;
        }else{
            returnValue = 1;
        }
        return returnValue;
    }
}
