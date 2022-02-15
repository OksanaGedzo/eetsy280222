package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.picture.Picture;
import ee.bcs.eetsy.domain.primary_group.PrimaryGroup;

import javax.persistence.*;

@Entity
@Table(name = "sub_group")
public class SubGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "primary_group_id", nullable = false)
    private PrimaryGroup primaryGroup;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "picture_id", nullable = false)
    private Picture picture;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public PrimaryGroup getPrimaryGroup() {
        return primaryGroup;
    }

    public void setPrimaryGroup(PrimaryGroup primaryGroup) {
        this.primaryGroup = primaryGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}