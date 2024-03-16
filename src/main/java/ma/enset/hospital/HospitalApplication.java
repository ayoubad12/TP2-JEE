package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.service.IHospitalService;
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
	CommandLineRunner start(IHospitalService hospitalService){
		return args -> {
			Stream.of("mohamed", "ayoub", "abdrahman")
					.forEach(name->{
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setMalade(false);
						patient.setDateNaissance(new Date());
						hospitalService.savePatient(patient);
					});

			Stream.of("ahmad", "abdelouahed", "ayman")
					.forEach(name->{
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5 ? "Neurologie" : "Cardio");
						hospitalService.saveMedecin(medecin);
					});

			Patient patient=hospitalService.findPatientById(1L) ; //if user with id=1 doesnt exist return null
			Medecin medecin = hospitalService.findMedecinByNom( "ahmad");

			RendezVous rendezVous= new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setMedecin (medecin) ;
			rendezVous.setPatient(patient);
			rendezVous.setStatus(StatusRDV.PENDING) ;
			RendezVous savedRD = hospitalService.saveRD(rendezVous);
			System.out.println(savedRD.getId());

			RendezVous rendezVous1 = hospitalService.findRDById(1L);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("rapport de la consultation ...");
			hospitalService.saveConsultation(consultation);

			//on va mis a jour le patient "ayoub"
			Patient patient1 = new Patient();
			patient1.setNom("ayoub");
			patient1.setMalade(true);
			patient1.setDateNaissance(new Date());
			hospitalService.updatePatient(patient1);

			hospitalService.deletePatientById(2L);
		};
	}
}
