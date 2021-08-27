package com.example.cybercrucibleproject;

import com.example.cybercrucibleproject.ShoppingList;
import com.example.cybercrucibleproject.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import org.springframework.beans
        .factory.annotation.Autowired;
import org.springframework.http
        .ResponseEntity;
import org.springframework.web.bind
        .annotation.GetMapping;
import org.springframework.web.bind
        .annotation.PostMapping;
import org.springframework.web.bind
        .annotation.RequestBody;
import org.springframework.web.bind
        .annotation.RequestMapping;
import org.springframework.web.bind
        .annotation.RestController;
import org.springframework.web.servlet
        .support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@RestController
public class ShoppingListController {
    @Autowired
    private ShoppingListRepository repository;

    //POST          /ShoppingList
    @RequestMapping(method= RequestMethod.POST,value="/ShoppingList")
    public ResponseEntity<String> createShoppingList(@RequestBody ShoppingList shoppinglist)
    {
        try {
            repository.save(shoppinglist);
            return new ResponseEntity("Successfully added ShoppingList" + shoppinglist.getName(), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    //GET       /ShoppingList
    @RequestMapping(method=RequestMethod.GET,value="/ShoppingList")
    public ResponseEntity getAllShoppingLists()
    {
        List<ShoppingList> ShoppingLists=repository.findAll();
        if(ShoppingLists.size()>0) {
            return new ResponseEntity(ShoppingLists, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("No Shopping Lists found", HttpStatus.NOT_FOUND);
        }
    }

    //DELETE       /ShoppingList/{id}
    @RequestMapping(method=RequestMethod.DELETE,value="/ShoppingList/{id}")
    public ResponseEntity deleteShoppingListById(@PathVariable("id") String id)
    {
        try{
            repository.deleteById(id);
            return new ResponseEntity("Successfully deleted ShoppingList with id" +id,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    //PUT       /ShoppingList{id}
    @RequestMapping(method=RequestMethod.PUT,value="/ShoppingList/{id}")
    public ResponseEntity updateById(@PathVariable("id") String id,@RequestBody ShoppingList newShoppingList)
    {
        Optional<ShoppingList> shoppingListOptional = repository.findById(id);
        if(shoppingListOptional.isPresent())
        {
            ShoppingList shoppinglistToSave=shoppingListOptional.get();
            //shoppinglistToSave.setName(newShoppingList.getName());
            List<String>temp = new ArrayList<String>(Arrays.asList(shoppinglistToSave.getItems()));
            String [] temp2 = newShoppingList.getItems();
            int i = 0;
            while (i < temp2.length)
            {
                temp.add(temp2[i]);
                i = i+1;
            }
            String [] temp3 = new String[temp.size()];
            temp3 = temp.toArray(temp3);
            shoppinglistToSave.setItems(temp3);
            //shoppinglistToSave.setItems(newShoppingList.getItems());
            repository.save(shoppinglistToSave);
            return new ResponseEntity("Updated ShoppingList with id"+id,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity("No ShoppingList with id"+id+"found",HttpStatus.NOT_FOUND);
        }
    }



}
