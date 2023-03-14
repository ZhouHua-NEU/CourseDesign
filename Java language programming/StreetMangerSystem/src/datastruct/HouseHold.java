//新建一个household实体类，字段有户主姓名，户主电话，户主身份证号，户主所属房屋编号

package datastruct;

public class HouseHold {
    private String householdname;
    private String householdphone;
    private String householdid;
    private String householdhouseid;
    
    //get方法
    public String getHouseholdname() {
        return householdname;
    }
    
    //set方法
    public void setHouseholdname(String householdname) {
        this.householdname = householdname;
    }
    
    //get方法
    public String getHouseholdphone() {
        return householdphone;
    }
    
    //set方法
    public void setHouseholdphone(String householdphone) {
        this.householdphone = householdphone;
    }
    
    //get方法
    public String getHouseholdid() {
        return householdid;
    }
    
    //set方法
    public void setHouseholdid(String householdid) {
        this.householdid = householdid;
    }
    
    //get方法
    public String getHouseholdhousenumber() {
        return householdhouseid;
    }
    
    //set方法
    public void setHouseholdhouseid(String householdhouseid) {
        this.householdhouseid = householdhouseid;
    }
    
}