package com.example.android.todo;

/**
 * Created by anuj on 17/2/17.
 */

public class TodoItem {

    public long _id; // for cupboard

    public String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }


    public TodoItem(long _id, String itemName) {
        this._id = _id;
        this.itemName = itemName;

    }

    //public ToDoItem(String name, Integer cuteValue) {
    //  this.itemName = name;
    //}


    @Override
    public String toString() {
        return itemName;
    }
}