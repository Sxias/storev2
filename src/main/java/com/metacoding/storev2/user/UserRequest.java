package com.metacoding.storev2.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String fullname;
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
        private String fullname;
    }
}
