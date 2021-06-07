package skype.project.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import skype.project.mongo.entity.PropertyEntity;

@Repository
public interface PropertyRepository extends MongoRepository<PropertyEntity, String> {

}
