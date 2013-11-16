package br.com.eventoweb.view.main;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MainUI {

	public MainUI() {
		super();
		 System.out.println("Criação do Bean MainUI");

	}

	
}