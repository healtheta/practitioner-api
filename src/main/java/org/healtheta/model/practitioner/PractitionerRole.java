package org.healtheta.model.practitioner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.healtheta.model.common.*;
import java.util.List;
import java.util.Date;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "PractitionerRole")
public class PractitionerRole {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_identifier")
    private Identifier identifier;

    @Column(name = "_active")
    private boolean active;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_period")
    private Period period;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "_pracitioner")
    private Reference pracitioner;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "_organization")
    private Reference organization;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_code")
    private List<CodeableConcept> code;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_speciality")
    private List<CodeableConcept> speciality;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_location")
    private List<Reference> location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_healthcareService")
    private List<Reference> healthcareService;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_telecom")
    private List<ContactPoint> telecom;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_availableTime")
    private AvailableTime availableTime;

    @Column(name = "_availabilityExceptions")
    private String availabilityExceptions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_endpoints")
    private List<Reference> endpoints;
}
