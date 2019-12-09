package lab02;


public interface Automobile{
  public void testDrive();
}
class Sedan implements Automobile {
   public void testDrive(){
     System.out.println("Sedan Method Implemented");
  }
   public static void main(String args[]){
     Automobile p = new Sedan();
     p.testDrive();
  }
}
class SportsCar implements Automobile{
   public void testDrive(){
     System.out.println("Sportscar Method Implemented");
  }
   public static void main(String args[]){
     Automobile p = new SportsCar();
     p.testDrive();
  }
}
