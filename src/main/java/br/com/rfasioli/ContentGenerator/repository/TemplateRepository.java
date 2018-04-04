package br.com.rfasioli.ContentGenerator.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.rfasioli.ContentGenerator.document.Template;

public interface TemplateRepository 
	extends MongoRepository<Template, String> 
{
		
	Iterable<Template> findByRulesQueriesStatementFieldAndRulesQueriesStatementOperatorAndRulesQueriesStatementValuesIn(
			String field, String operator, List<String> value);

	Iterable<Template> findByRulesQueriesStatementFieldAndRulesQueriesStatementOperatorAndRulesQueriesStatementValues(
			String field, String operator, String value);
	
}
