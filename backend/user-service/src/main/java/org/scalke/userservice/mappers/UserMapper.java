package org.scalke.userservice.mappers;

import org.mapstruct.Mapper;
import org.scalke.userservice.dtos.UserDTO;
import org.scalke.userservice.entities.User;
import org.scalke.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    public UserRepository userRepository;

    public abstract UserDTO entityToDto(User user);
    public abstract List<UserDTO> mapToDTO(List<User> users);
    public abstract List<User> mapToEntity(List<UserDTO> userDTOS);

    public User dtoToEntity(UserDTO userDTO){
        if (userDTO == null) {
            return null;
        }
        return userRepository.findById(userDTO.getId()).orElse(null);
    }
}
