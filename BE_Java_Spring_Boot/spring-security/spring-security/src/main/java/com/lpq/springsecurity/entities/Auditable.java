package com.lpq.springsecurity.entities;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable<U> {

    @CreatedBy
    @Column(name = "reg_id", length = 50)
    protected U regId;

    @CreatedDate
    @Column(name = "create_at")
    protected LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_at")
    protected LocalDateTime updateAt;
}
