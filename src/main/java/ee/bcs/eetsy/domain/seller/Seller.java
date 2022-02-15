package ee.bcs.eetsy.domain.seller;

import ee.bcs.eetsy.domain.picture.Picture;

import javax.persistence.*;

@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "logo_picture_id")
    private Picture logoPicture;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "index", nullable = false, length = 10)
    private String index;

    @Column(name = "aadress", nullable = false, length = 100)
    private String aadress;

    @Column(name = "website", length = 100)
    private String website;

    @Column(name = "validated", nullable = false)
    private Boolean validated = false;

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAadress() {
        return aadress;
    }

    public void setAadress(String aadress) {
        this.aadress = aadress;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getLogoPicture() {
        return logoPicture;
    }

    public void setLogoPicture(Picture logoPicture) {
        this.logoPicture = logoPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}