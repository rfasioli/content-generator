package br.com.rfasioli.ContentGenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rfasioli.ContentGenerator.document.Template;

/**
 * @author rfasioli
 *
 */
@RepositoryRestResource(collectionResourceRel = "template", path = "template")
public interface TemplateRepository 
	extends MongoRepository<Template, String> 
{
	Iterable<Template> findByContentId(String contentId);	
}
