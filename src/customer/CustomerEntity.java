package customer;

public class CustomerEntity {

    private Integer id;
    private String name;
    private String password;

    public CustomerEntity(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public CustomerEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
