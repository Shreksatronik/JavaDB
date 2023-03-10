package org.example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person-parents-type",namespace = "org.example", propOrder = {
        "motherRef",
        "fatherRef"
})
public class PersonParentsType {

    @XmlElement(name = "mother-ref")
    protected PersonReferenceType motherRef;
    @XmlElement(name = "father-ref")
    protected PersonReferenceType fatherRef;


    public PersonReferenceType getMotherRef() {
        return motherRef;
    }

    public void setMotherRef(PersonReferenceType value) {
        this.motherRef = value;
    }

    public PersonReferenceType getFatherRef() {
        return fatherRef;
    }


    public void setFatherRef(PersonReferenceType value) {
        this.fatherRef = value;
    }
}