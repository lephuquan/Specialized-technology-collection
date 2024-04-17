package com.ldcc.evsis.cms.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
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
    @Column(name = "reg_dtime")
    protected LocalDateTime regDTime;

    @LastModifiedBy
    @Column(name = "upd_id", length = 50)
    protected U updId;

    @LastModifiedDate
    @Column(name = "upd_dtime")
    protected LocalDateTime updTime;
}
