package Model;

public class PokemonData {

    private String name, type;
    private String image;
    public PokemonData(String name, String type, String image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public PokemonData(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }
}
