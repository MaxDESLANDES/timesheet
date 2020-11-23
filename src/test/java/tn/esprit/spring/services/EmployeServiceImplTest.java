package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	@Test
	public void authenticateTest() {
		Employe userRetrieved = employeRepository.getEmployeByEmailAndPassword("max@test.fr", "test");
		assertEquals(1, userRetrieved.getId());
	}
	
	@Test
	public void addOrUpdateEmployeTest() {
		Role r = Role.TECHNICIEN;
		Employe e = new Employe("test", "test", "test@test.fr", "test", true, r);
		Employe eUpdated  = employeRepository.save(e); 
		assertEquals(e.getId(), eUpdated.getId());
	}
	
	@Test
	public void mettreAjourEmailByEmployeIdTest() {
		Employe employe = employeRepository.findById(2).get();
		employe.setEmail(employe.getEmail()+"TEST");
		Employe eUpdated = employeRepository.save(employe);
		assertEquals(employe.getEmail(), eUpdated.getEmail());
	}
	@Test
	public void ajouterContratTest() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2020-03-23");
		Contrat c = new Contrat(d, "CDI", 2000);
		Contrat cUpdated = contratRepoistory.save(c);
		assertEquals(c.getReference(), cUpdated.getReference());

	}
	@Test
	public void affecterContratAEmployeTEST() {
		Contrat contratManagedEntity = contratRepoistory.findById(1).get();
		Employe employeManagedEntity = employeRepository.findById(1).get();

		contratManagedEntity.setEmploye(employeManagedEntity);
		Contrat cUpdated = contratRepoistory.save(contratManagedEntity);
		assertEquals(contratManagedEntity.getEmploye().getId(), cUpdated.getEmploye().getId());
	}
}
