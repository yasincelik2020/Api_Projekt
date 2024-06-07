package pojos;

public class AutomationCategoryPojo {
    private  String usertype;
    private  String category;

    public AutomationCategoryPojo() {
    }

    public AutomationCategoryPojo(String usertype, String category) {
        this.usertype = usertype;
        this.category = category;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AutomationCategoryPojo{" +
                "usertype='" + usertype + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
