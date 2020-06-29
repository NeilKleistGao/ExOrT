package com.exort.entity;

/**
 * This class represents the data in the table participation
 * create table `participation`()
 *     character_id int,
 *     arrangement_id int,
 *     primary key(character_id, arrangement_id),
 *     foreign key cid(character_id) references `character`(id) on delete no action on update no action,
 *     foreign key aid(arrangement_id) references `arrangement`(id) on delete no action on update no action
 * ) engine=InnoDB default charset=utf8;
 * @author NeilKleist
 * @version 1.0.0
 */
public class Participation {
    private Integer character_id;
    private Integer arrangement_id;

    public void setCharacter_id(Integer character_id) {
        this.character_id = character_id;
    }

    public Integer getCharacter_id() {
        return character_id;
    }

    public void setArrangement_id(Integer arrangement_id) {
        this.arrangement_id = arrangement_id;
    }

    public Integer getArrangement_id() {
        return arrangement_id;
    }
}
