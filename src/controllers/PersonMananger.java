package src.controllers;

import src.datastructures.Person;

public class PersonMananger {
  private Person[] persons;

  public PersonMananger() {
  }

  public Person create(String name, String ssn, String email, String phoneNumber) {
    Person p = new Person(name,ssn,email,phoneNumber);
    return p;
  }

  public void delete(Person p) {
    
  }
}