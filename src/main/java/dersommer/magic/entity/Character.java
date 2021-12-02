package dersommer.magic.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Getter
@Setter
@MongoEntity(database = "make-magic", collection = "Characters")
public class Character {

  @BsonId
  public ObjectId id;
  public String name;
  public String role;
  public String school;
  public String house;
  public String patronus;
}


