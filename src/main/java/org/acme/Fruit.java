package org.acme;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Fruit {

    @Id
    public UUID id;
}
