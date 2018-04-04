package br.com.rfasioli.ContentGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rfasioli.ContentGenerator.document.Fragment;
import br.com.rfasioli.ContentGenerator.repository.FragmentRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(path = "/reportgenerator/fragment", produces="application/json")
public class FragmentController {
	@Autowired
	private transient FragmentRepository repo;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Iterable<Fragment> findAll(
			@RequestParam(required=false) Integer page, 
			@RequestParam(required=false) Integer size) {
		if (page == null) page = 0;
		if (size == null) size = 20;
		return repo.findAll(new PageRequest(page, size));
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public Fragment findOne(@PathVariable String id) {
		return repo.findOne(id);
	}

	public FragmentController() {
		super();
	}
	
}
