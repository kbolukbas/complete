package com.example.validatingforminput;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class WebController implements WebMvcConfigurer {

	private final ArrayList<City> cities = new ArrayList<>(
		Arrays.asList(
			new City("Ankara"), 
			new City("İstanbul"), 
			new City("İzmir")
		)
	);


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(PersonForm personForm, ModelMap modelMap) {
		modelMap.addAttribute("cities", this.cities);

		return "form";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "form";
		}
		
		return "results";
	}
}
