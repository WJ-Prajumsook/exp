package org.wj.prajumsook.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "user")
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

}
