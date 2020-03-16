import java.io.Serializable;


public class User implements Serializable {

    private long id;
    private String city = "moscow";

    public User(){ }

    public User(Long id){
        this.id = id;
    }

    public User(Long id, String city){
        this.id = id;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString(){
        return getClass().getName() + "[ id = " + id + ", city = " + city + " ]";
    }

    @Override
    public int hashCode(){
        return Long.hashCode(id);
    }
}
