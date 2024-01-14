package com.halildev.elibrary.dto.requests;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOwnedBookRequest {
    Long id;
    Long book_id;
    Long user_id;


}
