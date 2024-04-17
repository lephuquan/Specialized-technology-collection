package com.ldcc.evsis.cms.entities;

import com.ldcc.evsis.cms.entities.enums.TokenType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", nullable = false)
    public Long tokenId;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    public Users users;
}
