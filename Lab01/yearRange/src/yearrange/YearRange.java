package yearrange;

public class YearRange {

    /**
     * @param args the command line arguments
     */
    public int Range,myFirstRange,mySecondRange;
    public int YearRange;

    public static int i = 3150;
	
    public YearRange() {
       int a = 0;
       int b = 0;
             
    }
     public int setRangeType()
      {
        int a = 0;
        int b = 0;
     
       return 10;   
      }
     public int getFirstDivisor()
     {
        return 10;
         
     }
      public int getSecondDivisor()
     {
        return 10;
         
     }
       public int printYears()
     {
        return 10;
         
     }
        public int getFirstYear()
     {
        return i;
         
     }
         public int getSecondYear()
     {
        return 1110;
         
     }
          static void result(int N) 
    {      
        // iterate from 0 to N 
        for (int num =2000; num < N; num++) 
        {      
            // Short-circuit operator is used  
            if (num % 14 == 0 && num % 20 == 0) 
                System.out.print(num + " "); 
        } 
    } 
    public static void main(String[] args) {
         int N = 3000; 
           
        // Calling function 
        result(N); 
        // TODO code application logic here
        
       YearRange myFirstRange=new YearRange();     
        myFirstRange.setRangeType();
        
	// Get all leap years.
	YearRange mySecondRange = new YearRange();
	mySecondRange.setRangeType();

	// Get all Olympic years divisible by 3
	YearRange myThirdRange = new YearRange();
	myThirdRange.setRangeType();

	System.out.println("List of all years divisible by" +
	myFirstRange.getFirstDivisor() + " and" +
	myFirstRange.getSecondDivisor());
	myFirstRange.printYears();

	System.out.println("List of all leap years between " +
	mySecondRange.getFirstYear() + " and " +
	mySecondRange.getSecondYear());
	mySecondRange.printYears();

	System.out.println("List of all Olympic years between " +
	myThirdRange.getFirstYear() + " and " + myThirdRange.getSecondYear() +
	"that are divisible by "+ myThirdRange.getFirstDivisor());
	myThirdRange.printYears();

	int n;
	for (n=1110; n<=i ; n++){
	  int l=n%4;
	  if (l==0){
		  System.out.println("leap year: "+n);
	  }
	}
  }
}