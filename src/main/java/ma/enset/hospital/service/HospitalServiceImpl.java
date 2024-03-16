package ma.enset.hospital.service;

import jakarta.transaction.Transactional;
import ma.enset.hospital.entities.Medecin;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.entities.RendezVous;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

@Service //tous les classes de la couche metier doit avoir cette attribut
@Transactional
public class HospitalServiceImpl implements IHospitalService{
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
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
        return rendezVousRepository.save(rendezVous);
    }
}



/*
    Note:
    les fichiers dans la couche metier doit contenir en realité des traitement metier, au contraire de ce qu'existent dans ce fichier qui communique seulement avec l'
*/