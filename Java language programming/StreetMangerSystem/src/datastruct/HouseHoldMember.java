///新建一个householdMember实体类，字段有成员姓名，成员电话，成员身份证号，成员所属户主身份证号

package datastruct;

public class HouseHoldMember {
    private String membername;
    private String memberphone;
    private String memberid;
    private String memberhouseholdid;
    
    //get方法
    public String getMembername() {
        return membername;
    }
    
    //set方法
    public void setMembername(String membername) {
        this.membername = membername;
    }
    
    //get方法
    public String getMemberphone() {
        return memberphone;
    }
    
    //set方法
    public void setMemberphone(String memberphone) {
        this.memberphone = memberphone;
    }
    
    //get方法
    public String getMemberid() {
        return memberid;
    }
    
    //set方法
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    
    //get方法
    public String getHouseholdid() {
        return memberhouseholdid;
    }
    
    //set方法
    public void setHouseholdid(String memberhouseholdid) {
        this.memberhouseholdid = memberhouseholdid;
    }
}
