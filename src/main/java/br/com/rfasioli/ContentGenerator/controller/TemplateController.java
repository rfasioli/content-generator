package br.com.rfasioli.ContentGenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rfasioli.ContentGenerator.document.Template;
import br.com.rfasioli.ContentGenerator.repository.TemplateRepository;

/**
 * @author rfasioli
 *
 */
@CrossOrigin(origins="*")
@RestController
@RequestMapping(path = "/reportgenerator/template", produces="application/json")
public class TemplateController {
	@Autowired
	private transient TemplateRepository repo;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Iterable<Template> findAll(
			@RequestParam(required=false) String field,
			@RequestParam(required=false) String operator,
			@RequestParam(required=false) List<String> values,
			@RequestParam(required=false) Integer page, 
			@RequestParam(required=false) Integer size) {
		if (page == null) page = 0;
		if (size == null) size = 20;
		if (field == null || values == null) {
			return repo.findAll(new PageRequest(page, size));
		}
		else {
			if (operator == null) operator = "eq";
			return repo.findByRulesQueriesStatementFieldAndRulesQueriesStatementOperatorAndRulesQueriesStatementValuesIn
					(field, operator, values);
		}
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Template findOne(@PathVariable String id) {
		return repo.findOne(id);
	}

	public TemplateController() {
		super();
	}

}
