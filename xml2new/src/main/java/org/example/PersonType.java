package org.example;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person-type",namespace = "org.example",propOrder = {
        "spouse",
        "parents",
        "siblings",
        "children"
})
public class PersonType {

    protected PersonReferenceType spouse;
    protected PersonParentsType parents;
    protected PersonSiblingType siblings;
    protected PersonChildrenType children;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "person-name", required = true)
    protected String personName;
    @XmlAttribute(name = "person-gender", required = true)
    protected PersonGenderType personGender;

    public PersonReferenceType getSpouse() {
        return spouse;
    }


    public void setSpouse(PersonReferenceType value) {
        this.spouse = value;
    }

    public PersonParentsType getParents() {
        return parents;
    }


    public void setParents(PersonParentsType value) {
        this.parents = value;
    }

    public PersonSiblingType getSiblings() {
        return siblings;
    }

    public void setSiblings(PersonSiblingType value) {
        this.siblings = value;
    }


    public PersonChildrenType getChildren() {
        return children;
    }


    public void setChildren(PersonChildrenType value) {
        this.children = value;
    }


    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }


    public String getPersonName() {
        return personName;
    }


    public void setPersonName(String value) {
        this.personName = value;
    }


    public PersonGenderType getPersonGender() {
        return personGender;
    }


    public void setPersonGender(PersonGenderType value) {
        this.personGender = value;
    }

}