package org.healtheta.model.practitioner;

import org.healtheta.model.common.CodeableConcept;
import org.healtheta.model.common.Period;
import org.healtheta.model.common.Reference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PractitionerQualification")
public class PractitionerQualification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @Column(name = "_code")
    private List<CodeableConcept> code;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_period")
    private Period period;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_issuer")
    private Reference issuer;
}
