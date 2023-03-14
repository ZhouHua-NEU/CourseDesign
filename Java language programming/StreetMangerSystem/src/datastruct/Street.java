//生成一个实体类street，字段有街道名，街道负责人，街道负责人电话，主键街道名

package datastruct;

public class Street {
    private String streetname;
    private String streetmanager;
    private String streetphone;
    
    //get方法
    public String getStreetname() {
        return streetname;
    }
    
    //set方法
    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }
    
    //get方法
    public String getStreetmanager() {
        return streetmanager;
    }
    
    //set方法
    public void setStreetmanager(String streetmanager) {
        this.streetmanager = streetmanager;
    }
    
    //get方法
    public String getStreetphone() {
        return streetphone;
    }
    
    //set方法
    public void setStreetphone(String streetphone) {
        this.streetphone = streetphone;
    }
}

