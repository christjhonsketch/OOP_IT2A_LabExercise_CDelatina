public class Cars{
    private String Color;
    private String PlateNo;
    private String ChassisNo;
    private String Brand;
    private String Model;

    public Cars(){
        this.Color = "no color";
        this.PlateNo = " AB123CD";
        this.ChassisNo = " LTFRB 1234567890";
        this.Brand = " Toyota";
    }
    public Cars(String Color,String PlateNo, String ChassisNo, String Brand, String Model){
        this.Color = Color;
        this.PlateNo = PlateNo;
        this.ChassisNo = ChassisNo;
        this.Brand = Brand;
        this.Model = Model;
    }
    public void displayInfo(){
        String info = "";
        info += "\nColor:" + this.Color;
        info += "\nPlateNumber:" + this.PlateNo;
        info += "\nChassisNumber:" + this.ChassisNo;
        info += "\nBrand:" + this.Brand;
        info += "\nModel:" + this.Model;
        System.out.println(info);
    }
}

