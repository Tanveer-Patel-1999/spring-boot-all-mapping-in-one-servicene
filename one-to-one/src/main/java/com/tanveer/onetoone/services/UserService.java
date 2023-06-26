package com.tanveer.onetoone.services;

import com.tanveer.onetoone.entity.UserEntity;
import com.tanveer.onetoone.exception.UserNotFoundException;
import com.tanveer.onetoone.model.UserRequest;
import com.tanveer.onetoone.model.UserResponse;
import com.tanveer.onetoone.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setAge(userRequest.getAge());
        userEntity.setMobile(userRequest.getMobile());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setNationality(userRequest.getNationality());
        userEntity.setGender(userRequest.getGender());
        userRepository.save(userEntity);

        UserResponse response = new UserResponse();
        response.setId(userEntity.getId());
        return response;
    }

    public UserRequest getUserById(Long id) {
        UserRequest userRequest = new UserRequest();
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()){
            userRequest.setName(userEntityOptional.get().getName());
            userRequest.setEmail(userEntityOptional.get().getEmail());
            userRequest.setMobile(userEntityOptional.get().getMobile());
            userRequest.setNationality(userEntityOptional.get().getNationality());
            userRequest.setGender(userEntityOptional.get().getGender());
            userRequest.setAge(userEntityOptional.get().getAge());
        }
        else {
            throw new UserNotFoundException("user not for id :"+id);
        }
        return userRequest;
    }

    public List<UserRequest> getAllUser() {
        UserRequest userRequest = new UserRequest();
        return userRepository.findAll().stream()
                .map(userEntity -> {
                    userRequest.setName(userEntity.getName());
                    userRequest.setEmail(userEntity.getEmail());
                    userRequest.setMobile(userEntity.getMobile());
                    userRequest.setNationality(userEntity.getNationality());
                    userRequest.setGender(userEntity.getGender());
                    userRequest.setAge(userEntity.getAge());
                    return userRequest;
                }).collect(Collectors.toList());
    }

    public UserRequest updateUser(UserRequest userRequest, Long id) {
        UserRequest request = new UserRequest();
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setName(request.getName());
            userEntity.setMobile(request.getMobile());
            userEntity.setAge(request.getAge());
            userEntity.setEmail(request.getEmail());
            userEntity.setNationality(request.getNationality());
            userEntity.setGender(request.getGender());
            userRepository.save(userEntity);
        } else {
            throw new UserNotFoundException("user not for id :"+id);
        }
        return request;
    }

    public void deleteUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()){
            userRepository.deleteById(id);
        }
        else {
            throw new UserNotFoundException("user not for id :"+id);
        }
    }
}
