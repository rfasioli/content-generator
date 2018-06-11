package br.com.rfasioli.ContentGenerator;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rfasioli.ContentGenerator.document.Fragment;
import br.com.rfasioli.ContentGenerator.document.Template;
import br.com.rfasioli.ContentGenerator.document.TemplateBase;
import br.com.rfasioli.ContentGenerator.dto.QueryDto;
import br.com.rfasioli.ContentGenerator.dto.RuleDto;
import br.com.rfasioli.ContentGenerator.dto.StatementDto;
import br.com.rfasioli.ContentGenerator.dto.TemplateNestedDto;
import br.com.rfasioli.ContentGenerator.repository.FragmentRepository;
import br.com.rfasioli.ContentGenerator.repository.TemplateRepository;

/**
 * Spring boot application, start entry point.
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner
{
	@Autowired
	FragmentRepository fragmentRepository;
	
	@Autowired
	TemplateRepository templateRepository;

	public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		fragmentRepository.deleteAll();
		templateRepository.deleteAll();
		
		Fragment fragment1 = new Fragment(null, "Fragmento 1", "<h1>Título 1</2>");
		Fragment fragment2 = new Fragment(null, "Fragmento 2", "<h1>Título 2</2>");
		Fragment fragment3 = new Fragment(null, "Texto 1", "<p>Texto 1 para títulos</p>");
		Fragment fragment4 = new Fragment(null, "Texto 2", "<p>Texto 2 para títulos</p>");
		Fragment fragment5 = new Fragment(null, "Texto 3", "<p>Texto 3 para títulos</p>");
		Fragment fragment6 = new Fragment(null, "Texto 4", "<p>Texto 4 para títulos</p>");
		Fragment fragment7 = new Fragment(null, "Texto 5", "<p>Texto 5 para títulos</p>");
		
		fragmentRepository.saveAll(Arrays.asList(fragment1, fragment2, fragment3, fragment4, fragment5, fragment6, fragment7));
		
		Template template1 = new Template("Template 1");

		RuleDto rule1 = new RuleDto("Rule 1");
		RuleDto rule2 = new RuleDto("Rule 2");
		
		StatementDto statement1 = new StatementDto("code", "eq");
		StatementDto statement2 = new StatementDto("code", "eq");
		StatementDto statement3 = new StatementDto("count", "eq");
		
		statement1.getValues().addAll(Arrays.asList("1"));
		statement2.getValues().addAll(Arrays.asList("2"));
		statement3.getValues().addAll(Arrays.asList("5"));
		
		QueryDto query1 = new QueryDto("or", statement1); 
		QueryDto query2 = new QueryDto("or", statement2); 
		QueryDto query3 = new QueryDto("or", statement3); 

		rule1.getQueries().addAll(Arrays.asList(query1, query2));
		rule2.getQueries().addAll(Arrays.asList(query3));
				
		template1.getFragments().addAll(Arrays.asList(fragment1, fragment3, fragment4));
		template1.getRules().addAll(Arrays.asList(rule1));
		
		TemplateNestedDto template2 = new TemplateNestedDto("Template 2", "REPLACE", fragment3.getId());
		template2.getFragments().addAll(Arrays.asList(fragment5, fragment6));
		template2.getRules().addAll(Arrays.asList(rule2));
		
		template1.getNested().addAll(Arrays.asList(template2));

		templateRepository.saveAll(Arrays.asList(template1));
		
	}
}
