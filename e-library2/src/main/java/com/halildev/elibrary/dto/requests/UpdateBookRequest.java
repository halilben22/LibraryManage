package com.halildev.elibrary.dto.requests;

import com.halildev.elibrary.models.AuthorDetails;
import lombok.Data;


import java.util.List;

@Data
public class UpdateBookRequest {

    String bookName;

    String book_ISBN;

    int bookStock;

    List<AuthorDetails> authorDetailsList;
}
