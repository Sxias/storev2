package com.metacoding.storev2.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        User userinfo = userRepository.findByUsername(joinDTO.getUsername());
        if (userinfo != null) throw new RuntimeException("이미 존재하는 아이디입니다.");
        userRepository.join(joinDTO.getUsername(), joinDTO.getPassword(), joinDTO.getFullname());
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User userinfo = userRepository.findByUsername(loginDTO.getUsername());
        if(userinfo == null) throw new RuntimeException("회원 정보가 없습니다.");
        if(!(userinfo.getPassword().equals(loginDTO.getPassword()))) {
            throw new RuntimeException("아이디 또는 비밀번호가 틀립니다.");
        }
        return userinfo;
    }
}
