package skype.project.mongo.repository;

import org.bson.types.ObjectId;
import skype.project.mongo.entity.UserEntity;

import java.util.List;

public interface UserRepositoryJDBC {

    void save(UserEntity userEntity);

    UserEntity getById(ObjectId id);

    List<UserEntity> findAll();

    void update(UserEntity userEntity);

    void delete(ObjectId id);

}
