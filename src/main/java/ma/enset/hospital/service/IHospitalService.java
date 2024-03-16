package ma.enset.hospital.service;

import ma.enset.hospital.entities.Consultation;
import ma.enset.hospital.entities.Medecin;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.entities.RendezVous;

import java.util.List;

public interface IHospitalService {
    //save
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRD(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
    //get all
    List<Patient> getAllPatient();
    //find by id
    Patient findPatientById(Long id);
    Medecin findMedecinById(Long id);
    RendezVous findRDById(Long id);
    Consultation findConsultationById(Long id);

    //find by name
    Patient findPatientByNom(String nom);
    Medecin findMedecinByNom(String nom);

    //update
    Patient updatePatient(Patient patient);

    //delete
    void deletePatientById(Long id);
}
