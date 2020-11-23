package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTests {
	
	@Autowired
	ContratRepository contratRepository;
	
	@Test
	public void testGetAllContrats() {
		
		List<Contrat> ListContrats = (List<Contrat>) contratRepository.findAll();
		// if there are 1 user in DB : 
		assertEquals(0, ListContrats.size());
	}
}
