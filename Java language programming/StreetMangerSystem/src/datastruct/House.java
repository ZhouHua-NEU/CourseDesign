//新建一个house实体类，字段有房屋编号，房屋地址，房屋面积，房屋类型，房屋所属社区，房屋所属街道

package datastruct;

public class House {
    private String houseid;
    private String houseaddress;
    private String housearea;
    private String housetype;
    private String housecommunity;
    private String housestreet;
    
    //get方法
    public String getHouseid() {
        return houseid;
    }
    
    //set方法
    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }
    
    //get方法
    public String getHouseaddress() {
        return houseaddress;
    }
    
    //set方法
    public void setHouseaddress(String houseaddress) {
        this.houseaddress = houseaddress;
    }
    
    //get方法
    public String getHousearea() {
        return housearea;
    }
    
    //set方法
    public void setHousearea(String housearea) {
        this.housearea = housearea;
    }
    
    //get方法
    public String getHousetype() {
        return housetype;
    }
    
    //set方法
    public void setHousetype(String housetype) {
        this.housetype = housetype;
    }
    
    //get方法
    public String getHousecommunity() {
        return housecommunity;
    }
    
    //set方法
    public void setHousecommunity(String housecommunity) {
        this.housecommunity = housecommunity;
    }
    
    //get方法
    public String getHousestreet() {
        return housestreet;
    }
    
    //set方法
    public void setHousestreet(String housestreet) {
        this.housestreet = housestreet;
    }
}
