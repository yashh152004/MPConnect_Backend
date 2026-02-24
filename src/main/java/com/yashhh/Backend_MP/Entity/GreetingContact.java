package com.yashhh.Backend_MP.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "greeting_contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GreetingContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String designation;
    private String event; // Birthday / Anniversary
    private String date; // MM-DD
    private String phone;
    private String email;
    private Integer lastGreetedYear;
}
