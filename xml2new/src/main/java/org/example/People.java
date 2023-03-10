package org.example;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",namespace = "org.example", propOrder = {
        "person"
})
@XmlRootElement(name = "people", namespace = "org.example")
public class People {
    protected List<PersonType> person;

    public List<PersonType> getPerson() {
        if (person == null) {
            person = new ArrayList<PersonType>();
        }
        return this.person;
    }

}
