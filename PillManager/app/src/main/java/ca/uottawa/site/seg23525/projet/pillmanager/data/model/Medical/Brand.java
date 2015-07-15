package ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical;

/**
 * A representation of a Brand
 * @author faeriol
 */
public abstract class Brand {
    private String name;

    public Brand(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
