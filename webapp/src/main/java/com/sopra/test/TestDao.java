package com.sopra.test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sopra.modele.TrainingDemand;

@Stateless
public class TestDao {
	@PersistenceContext(unitName = "formations")
	EntityManager em;

	public TrainingDemand test() {
		return em.find(TrainingDemand.class, 1256);
	}
}
