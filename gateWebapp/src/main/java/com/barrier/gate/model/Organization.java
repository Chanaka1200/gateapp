package com.barrier.gate.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "oraganization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizationId;
    private String  organizationName;
    private Boolean status;

    @OneToMany(mappedBy = "cardTransactionOrganizationEntity",cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<CardTransaction> cardTransactionOrganizationEntity;

    @OneToMany(mappedBy = "cardOperationOrganizationEntity",cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<CardOperation> cardOperationOrganizationEntity;

    @OneToMany(mappedBy = "cardDetailsOrganizationEntity",cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<CardDetails> cardDetailsOrganizationEntity;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
