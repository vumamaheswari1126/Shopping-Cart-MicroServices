package com.example.authservice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.authservice.dto.AuthenticatedUserResponse;
import com.example.authservice.dto.LoginResponse;
import com.example.authservice.dto.LoginUser;
import com.example.authservice.dto.RegisterUser;
import com.example.authservice.entity.Role;
import com.example.authservice.entity.UserEntity;
import com.example.authservice.exception.InvalidCredentialsException;
import com.example.authservice.exception.UserAlreadyExistsException;
import com.example.authservice.repository.AuthRepository;
import com.example.authservice.security.JwtService;

@Service
public class AuthService
{
	private PasswordEncoder passwordEncoder;
	private AuthRepository authRepository;
	private JwtService jwtService;
	private AuthenticationManager authenticationManager;
	public AuthService(PasswordEncoder passwordEncoder,AuthRepository authRepository,JwtService jwtService,AuthenticationManager authenticationManager)
	{
		this.passwordEncoder=passwordEncoder;
		this.authRepository=authRepository;
		this.jwtService=jwtService;
		this.authenticationManager=authenticationManager;
	}

	public UserEntity registerUser(RegisterUser registerUser)
	{
		UserEntity existinguser=this.authRepository.findByCustomerId(registerUser.getCustomerId());

		if(existinguser!=null)
		{
			throw new UserAlreadyExistsException("User Already Registered");
		}
		UserEntity user=new UserEntity();
		user.setCustomerId(registerUser.getCustomerId());
		user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
		user.setRole(Role.USER);

		return this.authRepository.save(user);


	}

	public UserEntity createAdmin(RegisterUser registerUser) {

	    UserEntity existingUser =authRepository.findByCustomerId(registerUser.getCustomerId());

	    if (existingUser != null)
	    {
	        throw new UserAlreadyExistsException("User Already Registered");
	    }

	    UserEntity admin = new UserEntity();

	    admin.setCustomerId(registerUser.getCustomerId());
	    admin.setPassword(passwordEncoder.encode(registerUser.getPassword()));
	    admin.setRole(Role.ADMIN);

	    return authRepository.save(admin);
	}
	public AuthenticatedUserResponse authenticatedUser(LoginUser loginUser)
	{
     try {


    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    String.valueOf(loginUser.getCustomerId()),
                    loginUser.getPassword()
            )
    );

      }catch(AuthenticationException ax)
     {
    	  throw new InvalidCredentialsException("Invalid Customer ID or Password");
     }
        UserEntity existingUser =
                authRepository.findByCustomerId(loginUser.getCustomerId());



        AuthenticatedUserResponse response = new AuthenticatedUserResponse();

        response.setCustomerId(existingUser.getCustomerId());
        response.setRole(existingUser.getRole().name());

        return response;
    }
//	public LoginResponse loginUser(LoginUser loginUser)
//	{
//     try {
//
//
//    authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(
//                    String.valueOf(loginUser.getCustomerId()),
//                    loginUser.getPassword()
//            )
//    );
//
//      }catch(AuthenticationException ax)
//     {
//    	  throw new InvalidCredentialsException("Invalid Customer ID or Password");
//     }
//        UserEntity existingUser =
//                authRepository.findByCustomerId(loginUser.getCustomerId());
//
//        String token = jwtService.generateToken(
//                existingUser.getCustomerId(),
//                existingUser.getRole()
//        );
//
//        LoginResponse response = new LoginResponse();
//
//        response.setCustomerId(existingUser.getCustomerId());
//        response.setToken(token);
//
//        return response;
//    }

//		UserEntity existinguser=this.authRepository.findByCustomerId(loginUser.getCustomerId());
//		if(existinguser==null)
//		{
//			throw new RuntimeException("Invalid CustomerID or Password");
//		}
//
//		if(!passwordEncoder.matches(loginUser.getPassword(),existinguser.getPassword()))
//		{
//			throw new RuntimeException("Invalid Password");
//		}
//
//
//		String token=jwtService.generateToken(existinguser.getCustomerId(),existinguser.getRole());
//
//		LoginResponse user=new LoginResponse();
//		user.setCustomerId(existinguser.getCustomerId());
//		user.setToken(token);
//
//		return user;


}
