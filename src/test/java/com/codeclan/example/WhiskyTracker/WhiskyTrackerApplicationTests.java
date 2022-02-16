package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findWhiskyByYear() {
		List<Whisky> found = whiskyRepository.findByYear(1987);
		assertEquals(1987, found.get(0).getYear());
	}

	@Test
	public void findDistilleriesByRegion() {
		List<Distillery> found = distilleryRepository.findByRegion("Speyside");
		assertEquals("Macallan", found.get(0).getName());
	}

	@Test
	public void getAllWhiskyFromDistilleryByAge() {
		//grab distillery from db by id (could be any id)
		Distillery distillery = distilleryRepository.getById(2L);
		//use combined query from WhiskyRepository
		List<Whisky> found = whiskyRepository.findByDistilleryAndAge(distillery,12);
		//Check the name against the returned whiskies from the query ^^
		assertEquals("The Rosebank 12 - Flora and Fona", found.get(0).getName());
	}
}
