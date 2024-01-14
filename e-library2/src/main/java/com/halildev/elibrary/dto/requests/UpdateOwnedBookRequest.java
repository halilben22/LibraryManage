package com.halildev.elibrary.dto.requests;


import lombok.Data;

@Data
public class UpdateOwnedBookRequest {


    Long book_id;

    Long user_id;
}
