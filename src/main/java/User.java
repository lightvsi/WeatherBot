public class User {
    private long id;
    private String city = "moscow";
    public User(long id){
        this.id = id;
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
