public class Cars{
    private String Color;
    private String PlateNo;
    private String ChassisNo;
    private String Brand;
    private String Model;

    public Cars(){
        this.Color = "no color";
        this.PlateNo = " no plate number";
        this.ChassisNo = " no chassis number";
        this.Brand = " no brand";
        this.Model = " no model";
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

