package MovieBooking.entities;

public class Customer {
    private final String id;
    private final String name;
    private final String mail;
    private String mobile;
    
    public String getId() {
        return id;
    }
    public String getMail() {
        return mail;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getName() {
        return name;
    }
    public Customer(String id, String name, String mail) {
        this.name = name;
        this.id = id;
        this.mail = mail;
    }

    public Customer(String id, String name, String mail, String mobile) {
        this.name = name;
        this.id = id;
        this.mail = mail;
        this.mobile = mobile;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    

}
