package com.halildev.elibrary.dto.requests;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data


public class CreateAuthorRequest {

    Long id;
    String name;
    String surname;

    Long book_id;


}
