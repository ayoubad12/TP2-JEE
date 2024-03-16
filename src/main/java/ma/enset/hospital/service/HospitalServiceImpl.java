package ma.enset.hospital.service;

import jakarta.transaction.Transactional;
import ma.enset.hospital.entities.Consultation;
import ma.enset.hospital.entities.Medecin;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.entities.RendezVous;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //tous les classes de la couche metier doit avoir cette attribut
@Transactional
public class HospitalServiceImpl implements IHospitalService{
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRD(RendezVous rendezVous) {
        //dans le cas ou RendezVous.id est de type String. on doit 'set' la valeur de ce id dans la couche metier,comme suivant:
        //rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findPatientById(Long id) {
        return patientRepository.findById((Long)id).orElse(null);
    }

    @Override
    public Medecin findMedecinById(Long id) {
        return medecinRepository.findById((Long)id).orElse(null);
    }

    @Override
    public RendezVous findRDById(Long id) {
        return rendezVousRepository.findById((Long)id).orElse(null);
    }

    @Override
    public Consultation findConsultationById(Long id) {
        return consultationRepository.findById((Long)id).orElse(null);
    }

    @Override
    public Patient findPatientByNom(String nom) {
        return patientRepository.findByNom(nom);
    }

    @Override
    public Medecin findMedecinByNom(String nom) {
        return medecinRepository.findByNom(nom);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        Patient existingPatient = findPatientByNom(patient.getNom());

        if (existingPatient != null) {
            existingPatient.setMalade(patient.isMalade());
            existingPatient.setDateNaissance(patient.getDateNaissance());
            // Update other fields as needed
            return patientRepository.save(existingPatient); // Save the updated patient
        }
        return null;
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}



/*
    Note:
    les fichiers dans la couche metier doit contenir en realité des traitement metier, au contraire de ce qu'existent dans ce fichier qui communique seulement avec l'
*/