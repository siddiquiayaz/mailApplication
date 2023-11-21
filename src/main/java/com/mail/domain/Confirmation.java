package com.mail.domain;

import java.time.LocalTime;
import java.util.UUID;

import org.apache.el.parser.AstFalse;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@ToString
@Entity
@Table(name ="confirmations")
public class Confirmation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String token;
    @CreatedDate
    
    private LocalTime createTime;
    @OneToOne(targetEntity = User.class ,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    public Confirmation(User user) {
    	this.user=user;
    	this.createTime=LocalTime.now();
    	this.token=UUID.randomUUID().toString();
    }
}
