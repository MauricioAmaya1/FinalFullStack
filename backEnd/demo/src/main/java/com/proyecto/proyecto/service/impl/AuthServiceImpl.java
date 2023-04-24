package com.proyecto.proyecto.service.impl;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.model.DTO.AuthenticationRequest;
import com.proyecto.proyecto.model.DTO.UserDTO;
import com.proyecto.proyecto.model.DTO.UsersRequest;
import com.proyecto.proyecto.model.Rol;
import com.proyecto.proyecto.model.Users;
import com.proyecto.proyecto.repository.RolRepository;
import com.proyecto.proyecto.repository.UsersRepository;
import com.proyecto.proyecto.security.UserSecurity.model.UserSecurity;
import com.proyecto.proyecto.security.config.JwtService;
import com.proyecto.proyecto.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;
    private final RolRepository rolRepository;
    private final EmailSenderServiceImpl emailSenderService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String addUser(UsersRequest usersRequest){

        var rolUser = rolRepository.findById(1L).get();
        var rolesList = new ArrayList<Rol>();
        rolesList.add(rolUser);

        Users user = Users
                .builder()
                .first_name(usersRequest.getFirst_name())
                .last_name(usersRequest.getLast_name())
                .email(usersRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(usersRequest.getPassword()))
                .roles(rolesList)
                .build();

        usersRepository.save(user);

        emailSenderService.sendEmail(user.getEmail(),
                "Usuario registrado exitosamente",
                "Bienvenido " + user.getFirst_name() + " " + user.getLast_name() +  " a Digital Booking!!");

        return "Usuario registrado exitosamente con email: " + user.getEmail();
    }

    public String  addAdmin(UsersRequest usersRequest) throws BadRequestException{

        var rolUser = rolRepository.findById(1L).get();
        var rolAdmin = rolRepository.findById(2L).get();
        var rolesList = new ArrayList<Rol>();
        rolesList.add(rolUser);
        rolesList.add(rolAdmin);

        Users user = Users
                .builder()
                .first_name(usersRequest.getFirst_name())
                .last_name(usersRequest.getLast_name())
                .email(usersRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(usersRequest.getPassword()))
                .roles(rolesList)
                .build();

        usersRepository.save(user);

        emailSenderService.sendEmail(user.getEmail(),
                "Administrador registrado exitosamente",
                "Bienvenido " + user.getFirst_name() + " " + user.getLast_name() +  " a Digital Booking!!");

        return "Administrador registrado exitosamente con email: " + user.getEmail();
    }

    @Override
    public UserDTO authenticate(AuthenticationRequest request) throws BadRequestException{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Credenciales erroneas"));
        var userSavedSecurity = new UserSecurity(user);
        var jwt =  jwtService.generateToken(userSavedSecurity);

        return UserDTO
               .builder()
               .id(user.getId())
               .first_name(user.getFirst_name())
               .last_name(user.getLast_name())
               .email(user.getEmail())
               .token(jwt)
                .roles(user.getRoles())
               .build();
    }

}
