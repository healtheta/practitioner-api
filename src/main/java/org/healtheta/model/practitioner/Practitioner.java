package org.healtheta.model.practitioner;

import org.healtheta.model.common.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Practitioner")
public class Practitioner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(unique=true, name = "_identifier")
    private Identifier identifier;

    @Column(name = "_active")
    private boolean active;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_name")
    private HumanName name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_telecom")
    private List<ContactPoint> telecom;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_address")
    private List<Address> address;

    @Column(name = "_gender")
    private String gender;

    @Column(name = "_birthDate")
    private Date birthDate;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_photo")
    private Attachment photo;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_qualification")
    private PractitionerQualification qualification;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name = "_communication")
    private List<CodeableConcept> communication;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_reference")
    private Reference reference;

    public Long getId() {
        return id;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public boolean isActive() {
        return active;
    }

    public HumanName getName() {
        return name;
    }

    public List<ContactPoint> getTelecom() {
        return telecom;
    }

    public List<Address> getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Attachment getPhoto() {
        return photo;
    }

    public PractitionerQualification getQualification() {
        return qualification;
    }

    public List<CodeableConcept> getCommunication() {
        return communication;
    }

    public Reference getReference() {
        return reference;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setName(HumanName name) {
        this.name = name;
    }

    public void setTelecom(List<ContactPoint> telecom) {
        this.telecom = telecom;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoto(Attachment photo) {
        this.photo = photo;
    }

    public void setQualification(PractitionerQualification qualification) {
        this.qualification = qualification;
    }

    public void setCommunication(List<CodeableConcept> communication) {
        this.communication = communication;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
