package br.com.rfasioli.ContentGenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rfasioli.ContentGenerator.document.Fragment;

/**
 * @author rfasioli
 *
 */
@RepositoryRestResource(collectionResourceRel = "fragment", path = "fragment")
public interface FragmentRepository 
	extends MongoRepository<Fragment, String> 
{
}
