package pojos;

public class UserTypePojo {

    private String usertype;

    public UserTypePojo() {
    }

    public UserTypePojo(String usertype) {
        this.usertype = usertype;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "UserTypePojo{" +
                "usertype='" + usertype + '\'' +
                '}';
    }
}
