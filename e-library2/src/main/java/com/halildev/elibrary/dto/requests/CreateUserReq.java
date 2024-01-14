package com.halildev.elibrary.dto.requests;



import com.halildev.elibrary.utils.Role;
import lombok.Data;

@Data
public class CreateUserReq {

    String firstname;
    String lastname;
    String username;
    String password;
    Role role;


}
