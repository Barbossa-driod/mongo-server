package skype.project.mongo.repository.impl;

import com.mongodb.DBRef;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import skype.project.mongo.entity.UserEntity;
import skype.project.mongo.repository.UserRepositoryJDBC;
import skype.project.mongo.utils.ClientConnectorJDBC;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Filters.eq;


public class UserRepositoryJDBCImpl implements UserRepositoryJDBC {


    private static final String DATABASE = "skypeProject"; // сделать в properties
    private static final String COLLECTION = "user";


    @Override
    public void save(UserEntity userEntity) {
        MongoClient mongoClient = ClientConnectorJDBC.getClient();

        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<UserEntity> databaseCollection = database.getCollection(COLLECTION, UserEntity.class);

        DBRef dbRef = new DBRef(DATABASE,"property","propertyId");



        databaseCollection.insertOne(userEntity);
        mongoClient.close();
    }

    @Override
    public UserEntity getById(ObjectId id) {
        MongoClient mongoClient = ClientConnectorJDBC.getClient();

        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<UserEntity> databaseCollection = database.getCollection(COLLECTION, UserEntity.class);

        System.out.println(id);
        UserEntity userEntity = databaseCollection.find(eq("_id", id)).first();


        mongoClient.close();
        return userEntity;
    }

    @Override //перебирать не через цикл может есть дургой вариант
    public List<UserEntity> findAll() {
        MongoClient mongoClient = ClientConnectorJDBC.getClient();

        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<UserEntity> databaseCollection = database.getCollection(COLLECTION, UserEntity.class);

        FindIterable<UserEntity> itUsers = databaseCollection.find();

        List<UserEntity> userEntities = new ArrayList<>();
        for (UserEntity u: itUsers){
            userEntities.add(u);
        }

        mongoClient.close();
        return userEntities;
    }

    @Override   //если будут добавлять новые поля в докуменете, что делать (reflection)
    public void update(UserEntity userEntity) {
        MongoClient mongoClient = ClientConnectorJDBC.getClient();

        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<UserEntity> databaseCollection = database.getCollection(COLLECTION, UserEntity.class);

        UpdateResult updateResult = databaseCollection.updateOne(eq("_id", userEntity.getId()),
                combine(set("name", userEntity.getName()),
                        set("surname", userEntity.getSurname()),
                        set("age", userEntity.getAge()),
                        set("sex", userEntity.getSex()),
                        set("propertyId", userEntity.getProperty())
                ));
        System.out.println(updateResult);

        mongoClient.close();
    }

    @Override
    public void delete(ObjectId id) {
        MongoClient mongoClient = ClientConnectorJDBC.getClient();

        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<UserEntity> databaseCollection = database.getCollection(COLLECTION, UserEntity.class);

        DeleteResult deleteResult = databaseCollection.deleteOne(eq(id));
        System.out.println(deleteResult);

        mongoClient.close();
    }
}
