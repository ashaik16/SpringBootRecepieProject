package com.recepies.demo.controllers;

import com.recepies.demo.model.Recepie;
import com.recepies.demo.model.ResponseHandler;
import com.recepies.demo.services.interfaces.RecepieService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recepie")
public class RecepieController {
	RecepieService recepieService;

	public RecepieController(RecepieService recepieService) {
		this.recepieService = recepieService;
	}

	// The function receives a GET request, processes it and gives back a list of
	// Todo as a response.
	@GetMapping
	public ResponseEntity<Object> getAllRecepies() {
		// List<Recepie> recepies = recepieService.getRecepies();
		try {
			List<Recepie> recepies = recepieService.getRecepies();
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, recepies);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
		// return new ResponseEntity<>(recepies, HttpStatus.OK);
	}

	// The function receives a GET request, processes it, and gives back a list of
	// Todo as a response.
	@GetMapping({ "/{recepieId}" })
	public ResponseEntity<Object> getTodo(@PathVariable Long recepieId) {

		try {
			Recepie recepie = recepieService.getRecepieById(recepieId);
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, recepie);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
		// return new ResponseEntity<>(recepieService.getRecepieById(recepieId),
		// HttpStatus.OK);
	}

	// The function receives a POST request, processes it, creates a new Todo and
	// saves it to the database, and returns a resource link to the created todo.
	// @PostMapping
	@PostMapping(value = "/")
	public ResponseEntity<Object> saveTodo(@RequestBody Recepie recepie) {

		try {
			boolean isRecepiePresent = recepieService.getExistingRecepie(recepie);
			if (isRecepiePresent)
				return ResponseHandler.generateResponse("This recepie is already present",
						HttpStatus.METHOD_NOT_ALLOWED, null);
			else {
				Recepie recepie1 = recepieService.insert(recepie);
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.add("recepie", "/api/v1/recepie/" + recepie1.getId().toString());
				return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, recepie1);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	// The function receives a PUT request, updates the Todo with the specified Id
	// and returns the updated Todo
	@PutMapping({ "/{recepieId}" })
	public ResponseEntity<Object> updateTodo(@PathVariable("recepieId") Long recepieId, @RequestBody Recepie recepie) {
		
		try {
			boolean isRecepiePresent = recepieService.getExistingRecepie(recepie);
		if (!isRecepiePresent)
			return ResponseHandler.generateResponse("This recepie is not present", HttpStatus.METHOD_NOT_ALLOWED,
					null);
		else {
			recepieService.updateRecepie(recepieId, recepie);
			return new ResponseEntity<>(recepieService.getRecepieById(recepieId), HttpStatus.OK);
		}
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	}
	}

	// The function receives a DELETE request, deletes the Todo with the specified
	// Id.
	@DeleteMapping({ "/{recepieId}" })
	public ResponseEntity<Recepie> deleteTodo(@PathVariable("recepieId") Long recepieId) {
		recepieService.deleteRecepie(recepieId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
