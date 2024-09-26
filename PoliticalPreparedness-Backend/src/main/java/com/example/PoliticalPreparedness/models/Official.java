package com.example.PoliticalPreparedness.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "official", schema = "pp_web")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Official {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String party;
    private String phoneNumber;
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference // Prevents serialization of this field to avoid circular reference
    private User user;

    // Parameterized constructor
    public Official(String name, String party, String phoneNumber, String url, User user) {
        this.name = name;
        this.party = party;
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.user = user;
    }

}
