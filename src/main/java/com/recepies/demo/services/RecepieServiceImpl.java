package com.recepies.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.recepies.demo.model.Recepie;
import com.recepies.demo.repository.RecepieRepository;
import com.recepies.demo.services.interfaces.RecepieService;

@Service
public class RecepieServiceImpl implements RecepieService {
	RecepieRepository recepieRepository;

	public RecepieServiceImpl(RecepieRepository recepieRepository) {
		this.recepieRepository = recepieRepository;
	}

	@Override
	public List<Recepie> getRecepies() {
		List<Recepie> recepies = new ArrayList<>();
		recepieRepository.findAll().forEach(recepies::add);
		return recepies;
	}

	@Override
	public Recepie getRecepieById(Long id) {
		return recepieRepository.findById(id).get();
	}

	@Override
	public Recepie insert(Recepie recepie) {

		return recepieRepository.save(recepie);

	}

	@Override
	public void updateRecepie(Long id, Recepie recepie) {
		Recepie recepieFromDb = recepieRepository.findById(id).get();
		System.out.println(recepieFromDb.toString());
//        todoFromDb.setTodoStatus(todo.getTodoStatus());
//        todoFromDb.setDescription(todo.getDescription());
//        todoFromDb.setTitle(todo.getTitle());
//		List<String> ingredientsList = new ArrayList<String>();
//		ingredientsList.add("1 bagel");
//		ingredientsList.add("butter");
//
//		List<String> instructionsList = new ArrayList<String>();
//		instructionsList.add("cut the bagel");
//		instructionsList.add("spread butter on bagel");
//	
//        recepieFromDb.setName("butteredBagel");
		recepieFromDb.setName(recepie.getName());
		recepieFromDb.setIngredients(recepie.getIngredients());
		recepieFromDb.setInstructions(recepie.getInstructions());
		recepieRepository.save(recepieFromDb);
	}

	@Override
	public void deleteRecepie(Long recepieId) {
		recepieRepository.deleteById(recepieId);
	}

	@Override
	public boolean getExistingRecepie(Recepie recepie) {
		Recepie exixtingRecepie = recepieRepository.findByName(recepie.getName());
		if (exixtingRecepie != null)
			return true;

		return false;
	}

}