package org.healtheta.web;

import org.healtheta.model.common.HumanName;
import org.healtheta.model.common.Identifier;
import org.healtheta.model.common.OperationOutcome;
import org.healtheta.model.common.Reference;
import org.healtheta.model.common.repos.HumanNameRepo;
import org.healtheta.model.common.repos.IdentifierRepo;
import org.healtheta.model.common.repos.ReferenceRepo;
import org.healtheta.model.practitioner.Practitioner;
import org.healtheta.model.practitioner.repos.PractitionerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.Long;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class PractitionerController {
    @Autowired
    PractitionerRepo practitionerRepo;
    @Autowired
    private IdentifierRepo identifierRepo;
    @Autowired
    private HumanNameRepo humanNameRepo;
    @Autowired
    private ReferenceRepo referenceRepo;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Practitioner practitioner){
        Identifier identifer = practitioner.getIdentifier();

        //validate param
        if(identifer.getValue() == null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(), HttpStatus.OK);
        }

        //check whether id exists
        if ( identifierRepo.findIdentifierByValue(identifer.getValue()) != null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordExists(), HttpStatus.OK);
        }

        //save practitioner record;
        try{
            Practitioner tmp = new Practitioner();
            tmp = practitionerRepo.save(tmp);
            practitioner.setId(tmp.getId());

            //Create an internal reference to patient;
            Reference reference = new Reference();
            reference.setIdentifier(practitioner.getIdentifier());
            reference.setDisplay("practitioner-reference");
            practitioner.setReference(reference);
            practitioner = practitionerRepo.save(practitioner);
            return new ResponseEntity<Practitioner>(practitioner, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> read(@PathVariable String id){
        try{
            Long lId = Long.parseLong(id);
            Practitioner practitioner = practitionerRepo.findPractitionerById(lId);
            if(practitioner != null)
                return new ResponseEntity<Practitioner>(practitioner, HttpStatus.OK);
            else
                return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Practitioner practitioner){
        Long id = practitioner.getId();
        Practitioner tmpPatient = practitionerRepo.findPractitionerById(id);
        if ( tmpPatient != null){
            //record exists let;s update.
            //reference are not to be modified.
            practitioner.setReference(tmpPatient.getReference());
            practitioner = practitionerRepo.save(practitioner);
            return new ResponseEntity<Practitioner>(practitioner, HttpStatus.OK);
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> delete(String id) {
        return null;
    }

    @RequestMapping(value = "/search",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam(value = "familyName", required=false) String familyName,
                                    @RequestParam(value = "givenName", required=false) String givenName){

        if ( familyName != null && givenName != null){
            List<HumanName> humanNameList = humanNameRepo.findHumanNameByFamilyAndGiven(familyName, givenName);
            List<Practitioner> practitionerList = practitionerRepo.findByNameIn(humanNameList);
            return new ResponseEntity<List>(practitionerList, HttpStatus.OK);
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.OperationNotSupported(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/format",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> test(){
        Practitioner practitioner =  new Practitioner();
        Identifier identifier = new Identifier();
        identifier.setValue("333333333");
        practitioner.setIdentifier(identifier);
        HumanName name = new HumanName();
        name.setFamily("Jane");
        name.setGiven("Mary");
        practitioner.setName(name);
        return new ResponseEntity<Practitioner>(practitioner, HttpStatus.OK);
    }
}
