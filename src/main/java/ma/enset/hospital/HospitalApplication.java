package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication { //we implement this interface so we can add data to our database ; for this we will need spring data
	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
	CommandLineRunner start(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository){
		return args -> {
			Stream.of("mohamed", "ayoub", "abdrahman")
					.forEach(name->{
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setMalade(false);
						patient.setDateNaissance(new Date());
						patientRepository.save(patient);
					});

			Stream.of("ahmad", "abdelouahed", "ayman")
					.forEach(name->{
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5 ? "Neurologie" : "Cardio");
						medecinRepository.save(medecin);
					});

			Patient patient=patientRepository.findById(1L).orElse(null); //if user with id=1 doesnt exist return null
			Patient patient1 = patientRepository.findByNom("mohamed") ;
			Medecin medecin = medecinRepository.findByNom( "ahmad");

			RendezVous rendezVous= new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setMedecin (medecin) ;
			rendezVous.setPatient(patient);
			rendezVous.setStatus(StatusRDV.PENDING) ;
			rendezVousRepository.save(rendezVous);


			RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null) ;
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("rapport de la consultation ...");
			consultationRepository.save(consultation);
		};
	}
}
