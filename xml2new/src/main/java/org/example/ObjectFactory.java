package org.example;

import javax.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public People createPeople() {
        return new People();
    }


    public PersonType createPersonType() {
        return new PersonType();
    }


    public PersonReferenceType createPersonReferenceType() {
        return new PersonReferenceType();
    }


    public PersonParentsType createPersonParentsType() {
        return new PersonParentsType();
    }


    public PersonChildrenType createPersonChildrenType() {
        return new PersonChildrenType();
    }

    public PersonSiblingType createPersonSiblingsType() {
        return new PersonSiblingType();
    }

}