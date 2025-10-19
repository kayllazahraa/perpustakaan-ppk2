package com.polstat.perpustakaan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "members")

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String phone_number;
}