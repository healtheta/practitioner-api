package org.healtheta.model.practitioner.repos;

import org.healtheta.model.common.HumanName;
import org.healtheta.model.practitioner.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PractitionerRepo extends JpaRepository<Practitioner, Long> {
    public Practitioner findPractitionerById(Long id);
    public List<Practitioner> findByNameIn(List<HumanName> humanNameList);
    public List<Practitioner> findAll();
}
