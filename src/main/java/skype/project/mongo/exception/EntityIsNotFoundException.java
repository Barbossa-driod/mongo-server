package skype.project.mongo.exception;

public class EntityIsNotFoundException extends RuntimeException{

    public EntityIsNotFoundException(String message){
        super(message);
    }
}
