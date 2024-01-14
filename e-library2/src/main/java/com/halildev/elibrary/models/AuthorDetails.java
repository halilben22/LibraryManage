package com.halildev.elibrary.models;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "author_details")
@Data

public class AuthorDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    String name;

    String surname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookdetails_id")
    BookDetails bookDetails = getBookDetails();


}
