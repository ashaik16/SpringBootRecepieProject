package com.recepies.demo.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.recepies.demo.model.Recepie;
import com.recepies.demo.repository.RecepieRepository;

@Component
public class RecepieLoader implements CommandLineRunner {
	public final RecepieRepository recepieRepository;

	public RecepieLoader(RecepieRepository todoRepository) {
		this.recepieRepository = todoRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadTodos();
	}

	private void loadTodos() {
		if (recepieRepository.count() == 0) {
		List<String> ingredientsList1 = new ArrayList<String>();
		ingredientsList1.add("1 tsp oil");
		ingredientsList1.add("2 eggs");
		ingredientsList1.add("salt");

		List<String> instructionsList1 = new ArrayList<String>();
		instructionsList1.add("Beat eggs with salt");
		instructionsList1.add("Heat oil in pan");
		instructionsList1.add("Add eggs to pan when hot");
		instructionsList1.add("Add eggs to pan when hot");
		instructionsList1.add("Salt to taste and enjoy");


			recepieRepository.save(Recepie.builder().name("scrambledEggs").ingredients(ingredientsList1)
					.instructions(instructionsList1).build());

			List<String> ingredientsList2 = new ArrayList<String>();
			ingredientsList2.add("500mL water");
			ingredientsList2.add("100g spaghetti");
			ingredientsList2.add("25mL olive oil");
			ingredientsList2.add("4 cloves garlic");
			ingredientsList2.add("Salt");

			List<String> instructionsList2 = new ArrayList<String>();
			instructionsList2.add("Heat garlic in olive oil");
			instructionsList2.add("Boil water in pot");
			instructionsList2.add("Add pasta to boiling water");
			instructionsList2.add("Remove pasta from water and mix with garlic olive oil");
			instructionsList2.add("Salt to taste and enjoy");

			recepieRepository.save(Recepie.builder().name("garlicPasta").ingredients(ingredientsList1).instructions(instructionsList2).build()

			);
			System.out.println("Sample Todos Loaded");
		}
	}
}
