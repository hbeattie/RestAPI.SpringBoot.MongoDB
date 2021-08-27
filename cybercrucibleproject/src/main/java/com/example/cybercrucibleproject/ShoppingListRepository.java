package com.example.cybercrucibleproject;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingListRepository extends MongoRepository<ShoppingList, String> {

}
