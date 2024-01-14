package com.halildev.elibrary.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "owned_books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnedBooks {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  public   Long id;


    @ManyToOne(fetch = FetchType.EAGER)
   public BookDetails bookDetails;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
   public User user;


}
