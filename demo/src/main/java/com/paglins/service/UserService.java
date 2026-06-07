package com.paglins.service;
import com.paglins.dto.UserRequestDTO;
import com.paglins.dto.UserResponseDTO;
import com.paglins.exception.ResourceNotFoundException;
import com.paglins.model.User;
import com.paglins.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final RouteService routeService;

    public UserService(UserRepository userRepository, RouteService routeService) {
        this.userRepository = userRepository;
        this.routeService = routeService;
    }

    public List<User> getAll() {
        return userRepository.findAll();}

    public UserResponseDTO save(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setBalance(BigDecimal.ZERO);
        user.setCreateAt(LocalDateTime.now());
        User salvo = userRepository.save(user);
        return new UserResponseDTO(
                salvo.getUserId(),
                salvo.getName(),
                salvo.getLastName(),
                salvo.getEmail(),
                salvo.getBalance(),
                BigDecimal.ZERO
        );
    }

    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Usuário com ID " + id + " não encontrado.");
        }
    }

    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado."));

        BigDecimal nextBalance = routeService.calcularProximoSaldo(id);

        return new UserResponseDTO(
                user.getUserId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getBalance(),
                nextBalance
        );
    }
}
