public class Dealer {
    private String dealerCode;
    private String name;
    private int phone;
    private String location;

    public Dealer(String dealerCode,String name,int phone, String location){
        this.dealerCode = dealerCode;
        this.name = name;
        this.phone = phone;
        this.location = location;
    }

    public String getDealerCode(){return dealerCode;}
    public String getName(){return name;}
    public int getPhone(){return phone;}
    public String getLocation(){return location;}

    public void setDealerCode(String dealerCode){this.dealerCode=dealerCode;}
    public void setName(String name){this.name=name;}
    public void setPhone(int phone){this.phone=phone;}
    public void setLocation(String location){this.location=location;}

    public String toFileLine(){return dealerCode + "," + name + "," + phone + "," + location;}


}
