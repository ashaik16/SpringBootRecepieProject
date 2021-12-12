package com.recepies.demo.services.interfaces;

import java.util.List;

import com.recepies.demo.model.Recepie;

public interface RecepieService {
    List<Recepie> getRecepies();

    Recepie getRecepieById(Long id);
    Recepie insert(Recepie recepie);

    void updateRecepie(Long id, Recepie recepie);

    void deleteRecepie(Long recepieId);

	boolean getExistingRecepie(Recepie recepie);
}
