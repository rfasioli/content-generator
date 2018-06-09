package br.com.rfasioli.ContentGenerator.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rfasioli.ContentGenerator.document.Template;

/**
 * @author rfasioli
 *
 */
@RepositoryRestResource(collectionResourceRel = "template", path = "templates")
public interface TemplateRepository 
	extends MongoRepository<Template, String> 
{
		
	Iterable<Template> findByRulesQueriesStatementFieldAndRulesQueriesStatementOperatorAndRulesQueriesStatementValuesIn(
			String field, String operator, List<String> value);

	Iterable<Template> findByRulesQueriesStatementFieldAndRulesQueriesStatementOperatorAndRulesQueriesStatementValues(
			String field, String operator, String value);
	
}
