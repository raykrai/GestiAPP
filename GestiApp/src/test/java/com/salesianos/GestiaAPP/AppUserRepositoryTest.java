package com.salesianos.GestiaAPP;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.salesianostriana.dam.gestiapp.model.AppUser;
import com.salesianostriana.dam.gestiapp.repository.AppUserRepository;

@DataJpaTest
@ActiveProfiles("test")
class AppUserRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Test
	public void testEncontrarPrimeroPorEmail() {
		
		AppUser a= new AppUser();
		a.setName("Daniel");
		a.setSurname("Troncoso Rubio");
		a.setPassword("1234");
		a.setUserEmail("buenas@gmail.com");
		a.setAdmin(true);
		a.setValidated(false);
		a.setReserveList(null);
		a.setSchool(null);
		entityManager.persist(a);
		
		AppUser user = appUserRepository.findFirstByUserEmail(a.getUserEmail());
		
		assertThat(user.getName()).isEqualTo("Daniel");
		
	}

}
