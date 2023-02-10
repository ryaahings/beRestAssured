package Assignment;

public class PostPOJO {
    String name;
    int tech_type_id;
    String doc_link;
    String description;
    String[] assoc_tags;
    int logo;

    public String[] getAssoc_tags() {
        return assoc_tags;
    }

    public void setAssoc_tags(String[] assoc_tags) {
        this.assoc_tags = assoc_tags;
    }

    public int getTech_type_id() {
        return tech_type_id;
    }

    public void setTech_type_id(int tech_type_id) {
        this.tech_type_id = tech_type_id;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoc_link(String doc_link) {
        this.doc_link = doc_link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDoc_link() {
        return doc_link;
    }

    public String getDescription() {
        return description;
    }



}
