package com.gtel.user_service.service;

import com.gtel.user_service.common.Const;
import com.gtel.user_service.dto.UserPrincipal;
import com.gtel.user_service.entity.User;
import com.gtel.user_service.exception.ApplicationException;
import com.gtel.user_service.model.request.LoginRequest;
import com.gtel.user_service.model.request.RegisterRequest;
import com.gtel.user_service.model.request.VerifyTokenRequest;
import com.gtel.user_service.model.response.LoginResponse;
import com.gtel.user_service.repositotry.UserRepository;
import com.gtel.user_service.utils.ERROR_CODE;
import com.gtel.user_service.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.prefix:Bearer}")
    private String prefixToken;

    public Boolean register(RegisterRequest request) throws ApplicationException {
        boolean isUserExisted = userRepository.existsByUsername(request.getUsername());
        if (isUserExisted) {
            throw new ApplicationException(ERROR_CODE.INVALID_REQUEST, Const.MessageCode.USER_ALREADY_EXISTS);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return true;
    }


    public LoginResponse login(LoginRequest loginRequest) throws ApplicationException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new ApplicationException(ERROR_CODE.USER_OR_PASS_INCORRECT);
        }

        final String token = jwtTokenUtil.generateToken((UserPrincipal) authentication.getPrincipal());

        final String rfToken = jwtTokenUtil.generateRfToken((UserPrincipal) authentication.getPrincipal());

        return new LoginResponse(token, rfToken, (UserPrincipal) authentication.getPrincipal(), prefixToken);
    }

    public boolean verifyToken(VerifyTokenRequest request) {
//        if (request == null || DataUtil.isNullOrEmpty(request.getUrl())
//                || DataUtil.isNullOrEmpty(request.getMethod())
//                || DataUtil.isNullOrEmpty(request.getSystem())){
//            return false;
//        }

//        long userId;
//        String username = null;
//        try {
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            UserProfileDto resultDTO = userMapper.map((User) principal);
//            if (resultDTO == null || resultDTO.getId() == null){
//                userId = -1;
//            }else{
//                userId = resultDTO.getId();
//                username = resultDTO.getUsername();
//            }
//        }catch (Exception e){
//            userId = -1;
//            log.error(e.getMessage());
//        }
////
//        boolean checkPerm = true;
//
//        try {
//            List<Long> permissionList = systemFunctionRepository.getUserPermission(userId, request.getUrl(), request.getMethod(), request.getSystem());
//            checkPerm =  !DataUtil.isNullOrEmpty(permissionList);
//        }catch (Exception e){
//            log.error(Arrays.toString(e.getStackTrace()));
//            checkPerm =  false;
//        }
//        if (request.getDeviceType() == null){
//            request.setDeviceType(0);
//        }
//        saveLogService.saveAccessLog(request, username, checkPerm);
        return true;
    }

}
