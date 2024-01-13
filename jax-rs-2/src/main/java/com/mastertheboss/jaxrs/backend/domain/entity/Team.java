package com.mastertheboss.jaxrs.backend.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Teams.findAll",
        query = "SELECT t FROM Team t ORDER BY t.id")
@XmlRootElement(name = "team")
public class Team {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(length = 40, nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Column(nullable = false)
    private Short size;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cave_id", referencedColumnName = "id")
    private Cave cave;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "persons_id", referencedColumnName = "id" )
    private List<Person> personList;

}