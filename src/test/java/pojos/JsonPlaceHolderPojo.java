package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //Bu annotaion ile bu class'ta olmayan field'lar gözardi edilir.
public class JsonPlaceHolderPojo {
    //Pojo class adimlari

    //1. Adim: Private variable'lar
    private Integer userId; // Default degeri null olmasini istedigim icin Wrapper class'dan Integer kullaniyorum.
    private String title;
    private Boolean completed; // Default degeri null olmasini istedigim icin Wrapper class'dan Boolean kullaniyorum.

    //2. Adim: Parametreli ve parametresiz constructor olusturacagim.


    public JsonPlaceHolderPojo() { // De-serialization islemleri icin default constructor'a ihtiyac duyuyoruz.
    } // Bir java class'inda herhangi bir constructor olusturuldugunda, default constructor kayboldugundan, olusturma geregi hissederiz.

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) { //Bu constructor ile olusturulan objede tüm field'lar atanmis olacak.
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    //3.Adim: Getter ve Setter methodlari olusturacagiz.

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //4.Adim: toString() methodu --> Bu class'dan olusan objeyi direk yazdirmek icin.

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

