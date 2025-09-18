public class TransfortationTester {
    public static void main(String[] args) {
        // Air Transport
        Helicopter heli = new Helicopter();
        Airplane plane = new Airplane();
        SpaceShuttle shuttle = new SpaceShuttle();

        // Land Transport
        Truck truck = new Truck();
        SUV suv = new SUV();
        Tricycle tri = new Tricycle();
        Motorcycle moto = new Motorcycle();
        Kariton kariton = new Kariton();

        // Water Transport
        Boat boat = new Boat();
        Submarine sub = new Submarine();

        // Test moving
        System.out.println("AirTransport");
        heli.move();
        plane.move();
        shuttle.move();
        System.out.println("");
        System.out.println("LandTransport");
        truck.move();
        suv.move();
        tri.move();
        moto.move();
        kariton.move();
        System.out.println("");
        System.out.println("WaterTransport");
        boat.move();
        sub.move();
    }
}