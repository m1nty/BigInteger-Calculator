/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Name: m1nty    
 */


/*The following program uses the java built in function System.exit(). This is used
to leave the program at any point stated and return a value specified within the 
brackets. I used it throughout my code to leave the program in the case of an error.
The value I chose is -1 to return if something like this happens. It is a security
measure to handle test cases and other invalid inputs given to code. Also instead 
of originally using ASCII code and adding a certain offset to obtain the equivalent
value from java, I used the built in Character.getNumericvalue in java shortcut
which made code easier to write and readable. */



public class Calculator {
    private String dig; 
//This holds the actual HugeInteger given to the program in String repersentation
//String was used because it can hold more together in memory than a regular int
    
    private int count; 
//This varibable count holds the number of digits in the actual string or length of it

    public Calculator(String val) {
              
        if(" ".equals(val) | val.isEmpty() | "".equals(val))
        { System.out.println("Incorrect input format. Please try again");
          System.exit(-1);
        }
//These 3 cases in the if statement are common ways to give the string empty input
//all three are handeled as if they are empty quotes or a blank input


        
        for(int j =1; j<val.length(); j++)
            if(((int)(val.charAt(val.length()-j))<48)|(((int)(val.charAt(val.length()-j)))>57)){
                System.out.println("Incorrect input format. Please try again");
                System.exit(-1);}
//The firist element is skipped in this loop and the rest are checked indicated 
//by the loop counter starting at 1. If the input isnt a valid integer we exit
//returning -1 using the java function System.exit as mentioned above 

        if( !( (((int)(val.charAt(0))>=48)&(((int)(val.charAt(0)))<=57)) || (val.charAt(0) == '-') )) {
             System.out.println("Incorrect input format. Please try again");
             System.exit(-1);}
//Here we look at the first caharacter to verify if it is also of valid input. We
//needed to create a different body because it has the possibility of having the 
//negative sign.

        else{
            dig = val;
            count = val.length();

            this.zerodeleter();
//If theres unneccsary leading zeroes at the beginging of the digit provided
//this call to zerodeleter removes them all and continues with normal arithmitic

          
            if(dig.charAt(0) == '-') 
                count --;
//If the integer provided has a leading negative sign we need to make sure that 
//it doesnt affect the length because that wouldnt make sense. This is why we decrement
        }

    }
//RANDOM NUMBER GENERATION

    public Calculator(int rand) {

        if(rand<1){ System.out.println("Incorrect input format. Please try again"); System.exit(-1);}
//This exits as an invalid input if number less than 1 is given as the factor for
//random number generation since it corresponds to the number of digits
        else{
            dig = "";
//The random digits are generated one by one and appended to this empty string to store
            this.count = rand;
//garunteed before the loop begins that the random digit has as many digits in its
//legnth as the given paramter
            dig += 1 + (int)(Math.random() * 8); 
//This first statement ensures that the first number in didit is between 1 and 9
//to eliminate the chance of having a leading 0
            for(int i=1; i<rand; i++)
                dig += (int)(Math.random() * 10); 
//This prdouces the remaining digits between 0-9 to append to the total
        }
    }

    public Calculator(){ 
//Empty constructor needed for further computation simplicity 
//we initialize an empty string to append a number if needed and legnth of it 0
        dig = "";
        count = 0;
    }

    public String getNumber(){return dig;}
//getter to return the number given

    public int getLength(){return count;}
//getter the return the count of the number given
  @Override
    public String toString(){
            return this.getNumber();  
    }
//getter to return a string repersentation of the num
   
    
    public Calculator part_a(Calculator ref){
    
//The big and small hold the number of digits within each string that are being added
//this is done so we have accurate loop bounds
        int car = 0;
        int big;
        if(count>=ref.count)
            big = count;
        else
            big = ref.count;
//the above if else statement is simply to determine which number is larger
        int small;
        if(ref.count>=count)
            small = count;
        else
            small = ref.count;
       
//this is opposite of other if else, it just determines the smaller

        
//This operation is done by considering the value of each digit starting from the LSD and then carrying over if 
//one is present. It makes sure to match them in the respective place holder by keeping track of where the ditits are 
//accumulating depending on the size of the number

        int temp; 
//temp variable needed as a place holder when we take the modulo of the current numbr to determine remainder from place holder
        int front;
        int back;
//use to track the front and back of the corresponding number
        Calculator total = new Calculator();
//new calculator object gets the total appended to it. Empty constructor initializes it to blank quotations
//this first loop only runs for the smaller numbers digits to be added to the total       
        for(int i =1; i<=small; i++){
//the java built in tool getNumericValue grabs the equivalent integer from the string for
//arithmitic to be able to be done on it using other integer numbers such as car for the carry
            front = Character.getNumericValue(dig.charAt(count-i)) + car;
//above lines are within loop which grab the current object and the one given to parameter
//to store their numbers to procceed with carry calculations
            back = Character.getNumericValue(ref.dig.charAt(ref.count-i));

            temp = (front+back)%10;
            car = (front+back)/10;
//carry calculated 
            total.dig = temp + total.dig;
//total updated each iteration
        }
//This loop adds the rest of the numbers after which is why small is the begining of it
//It runs all the way to big as long as the count doesnt exceed the other object

        for(int i=small+1; i<=big; i++){ 
            //CASE 1
            if(count>= ref.count){
//This ensures that we dont exceed the count if the the current objects digits are larger
//than the one given
            temp = Character.getNumericValue(dig.charAt(count-i)) + car;
//getNumericValue used again as a tool to get the int value for usable integer operations from string
            car = temp/10;
            temp = temp%10;
            total.dig = temp + total.dig;}
//same calculation as above is done in this case
        }

        for(int i=small+1; i<=big; i++){ 
            //CASE 2
            if(!(count >= ref.count)){
//Thise case happens when the object given inparatmeter hasmore digits unlike previously
            temp = Character.getNumericValue(ref.dig.charAt(ref.count-i)) + car;
            car = temp/10;
            temp = temp%10;
            total.dig = temp + total.dig;}
//similar calculation as above is done in this case
        }

        if(car!=0) 
//Deals with case in which there is still a car left over after all other digits have been dealt with
            total.dig = car + total.dig;

        total.count = total.dig.length(); //Updates variable storing dig of digits
        return total;
    }

     public int compareTo(Calculator ref){

        
//this function is what compares the numbers and returns eitehr 1 or -1 if one is greater than the other and if equal returns 0
        if(this.dig.charAt(0)=='-'&ref.dig.charAt(0)=='-'){
//if both numbers are negative this block executes
            if(this.count>ref.count)
//if current object has more digits within string than it is obviously greater
                return -1;
            if(this.count<ref.count)
//if it has less digits than the other is greater and it returns -1
                return 0;
            else{
//this loop deals with the sccenrio if both digits are the same
                for(int j=0;j<this.count;j++){
//now we go through individually starting at the MSB to see which digit is greater.
                    if(this.dig.charAt(j)>ref.dig.charAt(j))//if current is greater than other at a certain iteration we return -1 cause otehr is smaller
                        return -1;
                    if(this.dig.charAt(j)<ref.dig.charAt(j))//opposite scenerio
                        return 1;
                }
                return 0;//numbers are the same and verified digit by digit
            }
        }
        if(this.count>ref.count&this.dig.charAt(0)!='-')
//this executes if current is positive and has more digits than other number therefore mathematically being bigger obviously

        if(this.count>ref.count&this.dig.charAt(0)=='-')
//if current is negative and also has more digits then we know its obviously smaller 
            return -1;
        if(this.count<ref.count&ref.dig.charAt(0)!='-')
//similar logic: if the other is positive and has more digits its larger
            return -1;
        if(this.count<ref.count&ref.dig.charAt(0)=='-')
//if otehr is negative and has less digits than of course the current is larger
            return 1;
        else{
//this loop executes if the number of digits is the same
            for(int j=0;j<this.count;j++){
//now we check each individual digit to see when they are not equal and if at any point this is greater than the other than we know to return 1 since its bigger
                if(this.dig.charAt(j)>ref.dig.charAt(j))
                    return 1;
                if(this.dig.charAt(j)<ref.dig.charAt(j))
                    return -1;
            }
            return 0;
//if numbers are the same
        }
    }
     
   
    public Calculator part_s(Calculator ref){
        
//this method used to minus two positive ints
//we reuse similiar structure from part_a to compute the following
        
        int front, back;
        int car = 0;
//similar variables also needed from part_a
        Calculator total = new Calculator(); 
//New object needed to store total

        if(this.compareTo(ref) == 1){ 
            //CASE 1
//when the current object is larger than ref, therefore the loop bounds beed to change to the appropriate amount

            int temp;
            for(int x=1; x<=ref.count; x++){
                
                front = Character.getNumericValue(dig.charAt(count-x)) - car;
//getNumericValue used again as a tool to get the int value for usable integer operations from string               
                back = Character.getNumericValue(ref.dig.charAt(ref.count-x));
                temp = front - back;
//we need the difference in these two by digit for the part_s phase which also consideres the carry in the next code
                
                
                if(temp<0){temp += 10; car = 1;}
//this loop is used for the carry, since we need to bring it over the the next LSD digit by digit
                else{car = 0;}
                total.dig = temp + total.dig;
            }

//Now that LSD done this handels the rest of the digits in the string which is why we start from count +1
            for(int y=ref.count+1; y<=count; y++){
                
                temp = Character.getNumericValue(dig.charAt(count-y)) - car;
                if(temp<0){temp += 10; car = 1;}
                else{car = 0;}
                total.dig = temp + total.dig;
            }

//important case check below for leading zeroes
            if(Character.getNumericValue(total.dig.charAt(0))==0){
                total.count = total.dig.length();
//zerodelter called if the number is a 0 and is then reconstructed as a normal input to proceed with calculations
                total.zerodeleter();
                return total;
            }

            total.count = total.dig.length();
//this makes sure the object returned has the current length of the subvtracted two numbers
            return total;
        }
//CASE 3

//if parameter object has a negatie sign and is larger we exectute this loob to ensure the sign is dealt with to place at the total
//since ref would be larger the current object is going to be smaller to minus from resulting in a negative total
        if(this.compareTo(ref) == -1){
            total = ref.part_s(this);
//negative sign appended here
            total.dig = "-" + total.dig;
            return total;
        }
//CASE 4
//if numbers same we return zero obviously since thats the difference which is the last case
        total.dig = "0";
        total.count = 1;
        return total;

    }

    
//The following is the multiply method achieved under a big theta run time of n^2. The actual run time is 
//actually n^(logbase2(3)) = n^1.585
    
//This algorithim uses a divide and conquer approach to multiply two numbers which involves   //breaking it down into smaller parts for faster multiplication. 
//This method uses 3 multplications and various uses of part_a and is recursivley implemented in this call
    public Calculator karatsuba(Calculator ref){ 
//for karatsuba to work we need the digits to be even on both slides of the splitting portion of the algorithim. SO these first 3 cases conduct
//appropriate calculations if they are not and also dont have enough digits to split up
        
//BASE CASES 
        
        Calculator total = new Calculator();
//This algorithim involves splitting a number into its high and low portions
//The high bytes are where the first part the most left bits and the low are most right
        
        Calculator lowbyte1,highbyte1; 
        Calculator lowbyte2, highbyte2; 
        
//These high and low bytes have operands preformed on them respectively and are sperated for this very purpose
        
        if(count == 1 ){ 
            
            if (ref.count == 1){
//need to make sure that both numbers have at least a digit and no blank inputs given
//we used getNumericValue gain when grabbing the int value from the string
            total.dig += Character.getNumericValue(dig.charAt(0))*Character.getNumericValue(ref.dig.charAt(0));
//this is simply appending to the total owith the multiplication of the first digits in the string
//the length of this particular total is then updated as well and returend
            total.count = total.dig.length();
            return total;}
        }
//here we see the earlier mentioned implementation of the highs and lows being seperated and computed in to two halves
        int  a,b, c, d, e,f;
//we need these variables as place holders for values to then updated total with

//CASE 1
        
        
        if(count == 2 && ref.count == 2){ 
//This handels the case where two digit numbers are being given as input where we can compute the highs and lows simply
            
            highbyte1 = this.split(0,1);
            lowbyte1 = this.split(1,2);
//we split the current object into high and low. As mentioned earlier the highbyte is most left part of the number which
//is why we index it as 0 and 1. Split function simply takes a chunk out of this depending on the parameters called
//Parameters are just an indication of the range to split at
            highbyte2 = ref.split(0,1); 
            lowbyte2 = ref.split(1,2);
//this splits the other number at the same indices as before

//below is the formula in the algorithim for karatsuba            
            a = Character.getNumericValue(highbyte1.dig.charAt(0))*Character.getNumericValue(highbyte2.dig.charAt(0));
//now from the highbyte we multiply its first indices 
            b = Character.getNumericValue(lowbyte1.dig.charAt(0))*Character.getNumericValue(lowbyte2.dig.charAt(0));
//we do the same for the low bytes of both numbers at their first indicies
            c = Character.getNumericValue(highbyte1.dig.charAt(0))+Character.getNumericValue(lowbyte1.dig.charAt(0));
            d = Character.getNumericValue(highbyte2.dig.charAt(0))+Character.getNumericValue(lowbyte2.dig.charAt(0));
            e = c*d - a - b;
        
           f = 100*a + 10*e + b;
//we compute the product of sums of the high*low bytes for each number
//using placeholder units for the ones tens and hundreds column we can form the final number by adding them to the total
            total.dig += f;
//appended to total
            total.count = total.dig.length();
//count updated
            return total;
        }
//similar computation but this is if a one digit is given and the other is a two digit. This makes the high portion respictively depending on if its 1 or two digits 0!
        
//CASE 2       
        if(count == 2 && ref.count == 1){ //This is a two digit dig and ref is a one digit dig
            
            highbyte1 = this.split(0,1);
            lowbyte1 = this.split(1,2);
//Similar splitting of indices as before for the high and low bytes            
            lowbyte2 = ref.split(0,1);

            b = Character.getNumericValue(lowbyte1.dig.charAt(0))*
                 Character.getNumericValue(lowbyte2.dig.charAt(0));
//low bytes are multiplied          
            c = (Character.getNumericValue(highbyte1.dig.charAt(0))+Character.getNumericValue(lowbyte1.dig.charAt(0)))
               *(Character.getNumericValue(lowbyte2.dig.charAt(0))) - b;
//high bytes and lows are added and then multiplied and the b term is subtracted since we want the differecne to get the correct place holder
//very similar computation as earlier but now we have the difference of a portion equating to zero            
            d = 10*c + b;
//appended to total again
            total.dig += d;
            total.count = total.dig.length();
            return total; 
        }

//CASE 3
//this is same scenerio as above but the reverse order where ref has two digits and the current object just has one
//conclusin of the high componenet being zero for the one digit still stands

        if(count == 1 && ref.count == 2){ //This is a one digit dig and ref is a two digit dig
            lowbyte1 = this.split(0,1);
            highbyte2 = ref.split(0,1); 
            lowbyte2 = ref.split(1,2);
//splitting at indicies again
            b = Character.getNumericValue(lowbyte1.dig.charAt(0))
                *Character.getNumericValue(lowbyte2.dig.charAt(0));
            
            
            c = (Character.getNumericValue(lowbyte1.dig.charAt(0)))
               *(Character.getNumericValue(highbyte2.dig.charAt(0))
               +Character.getNumericValue(lowbyte2.dig.charAt(0))) - b;
            
            d = 10*c + b;

            total.dig += d;
            total.count = total.dig.length();
            return total;
        }
//CASE 4
        
    
//This is the more commonly used part of the code. Since we are dealing with above 2 digits for multiplication fast this is the normally exectuted portion
//The previous cases only do the multiplication for 2 digits or less

          
            if(count%2 != 0){dig = "0" + dig; count++;} 
            if(ref.count%2 != 0){ref.dig = "0" + ref.dig; ref.count++;}
//this portion of code simply corrects the length of the components if neccesary. This is needed till they have even digits. This is done by putitng placeholder zeroes 
//recursion is used to ensure that they become of the same amount of digits so algorithm works 

            if(count > ref.count)
//this computes the amount of zeroes to part_a using recursion if current object has less digits
                ref.addZeroes(count-ref.count);
            if(ref.count > count) 
//If string passed as the paramate and is larger than this calculates the corresponding amount of zeroes which is opposite ocndition than above
                this.addZeroes(ref.count-count); 


   
            highbyte1 = this.split(0, count/2);
            lowbyte1 = this.split(count/2, count);
//the split function breaks them up into components but using the new digits that have extra zeroes if neccesary.
//its important to understand that the leading zeroes are there as placeholders and do not make the value come out to be zero
            highbyte2 = ref.split(0, count/2); 
            lowbyte2 = ref.split(count/2, count);
//the same is done for the other number. we use the /2 to make sure they are even splits

//Recrusion is used now to continue this process above until we reach one of the 4 base cases as shown above

            Calculator hold1, hold2, hold3, hold4, hold5;
            hold1 = highbyte1.karatsuba(highbyte2);
//we want to call algorithim on teh first two highbytes because it takesk the shape of a tree almost
//we start at the very high and make our way down as the algorithim gets called until the base case
            hold2 = lowbyte1.karatsuba(lowbyte2);
            hold3= highbyte1.karatsuba(lowbyte2);
            hold4= lowbyte1.karatsuba(highbyte2);
            hold5 = hold3.part_a(hold4);
//the following recursion calls happen in this order

//this portin of code is responsible for placing zeroes if you apply algorithim to the high components they will have more 
//zeroes than the low ones with the same physical digits so we use this case instead
            for(int n=0; n<count; n++){  
                hold1.dig += "0";
                hold1.count++;
//zeroes being added to the highbytes of 1 to 2
            }
            for(int m=0; m<count/2; m++){
                hold5.dig += "0";
                hold5.count++;
            }
//zeroes beig added to hold 3 to hold 4
            Calculator tot;
//total object being instantiated to hold the total value
            tot = (hold1.part_a(hold2)).part_a(hold5);
            tot.zerodeleter(); 
//leading zeroes are removed
            total = tot;
            return total;
        
    }
      public void addZeroes(int count){ //Reformats dig by adding a specified amount of leading zeroes
        int i;
        for(i=0; i<count; i++)
            dig = "0" + dig;
        this.count += count;
    }
      
      
      
      public Calculator split(int m, int count){ 
//Extract takes a dig and returns some part of it based on m and count, m is start of index, count is end
        Calculator copy = new Calculator();
        copy.dig = dig.substring(m, count);
        copy.count = copy.dig.length();
        return copy;
    } 
    
    

    //DIVISION portion 
    public Calculator part_d(Calculator ref){

        Calculator total = new Calculator();
        ref.zerodeleter();
//we ensure to delete extra zeroes off the numbers we give to method
        if(Character.getNumericValue(ref.dig.charAt(0)) == 0){ 
            System.out.println("ILLEGAL TO DIVIDE BY ZERO");
            return new Calculator();
        }
//we cannot divide by zero so an error message is produced 

        if( ref.count == 1 & Character.getNumericValue(ref.dig.charAt(0)) == 1) 
            return this;
//if the divisior is 1 we simply return backthe object 
        if(this.compareTo(ref) == 0){ 
            total.dig = "1";
            total.count = 1;
            return total;
        }
//if both numbers are the same we simply return 1 and set the length to one as well

        if(this.compareTo(ref) == -1){ 
            total.dig = "0";
            total.count = 1;
            return total;
        }
//the above code runs if the object passed in method is bigger than current so the integer part willl just be 0
        
//For regurlar division it is done division by subtraction. It is not the fastest implementation but it works 
//The divisor is continuously subtracted from the dividend and there is a counter to track how many times it can.
//once it cannot fit inside the dividend we know that it will be negative if done again so now we know the intger part
//of the part_d
//Code determines total of integer part_d by first creating a new...
        
        int i = 0;
        Calculator A = this;

        do{
            A = A.minus(ref);
            i++;
        }while(A.compareTo(ref) >= 0);

        total.dig += i;
        total.count = total.dig.length();
        return total;
    } 
   public Calculator divide(Calculator ref){

        //Case where both numbers are positive, simply uses part_d() method defined above
        if(dig.charAt(0) != '-' && ref.dig.charAt(0) != '-')
            return this.part_d(ref);

        //Case where this is positive and ref is negative. Flips the sign of ref, uses part_d(), and then flips sign of total
        if(dig.charAt(0) != '-' && ref.dig.charAt(0) == '-')
            return (this.part_d(ref.signchange())).signchange();

        //Case where this is negative and ref is positive. Flips the sign of this, uses part_d(), and then flips sign of total
        if(dig.charAt(0) == '-' && ref.dig.charAt(0) != '-')
            return (this.signchange().part_d(ref)).signchange();

        else //Case where both numbers are negative. Division of two negatives is positive, so just flips sign of both and uses part_d()
            return this.signchange().part_d(ref.signchange());
    }


  

    public void zerodeleter(){ //Reformats dig to remove any leading zeroes
        int i;
        //For loop determines index at which first non-zero element appears
        for(i=0; i<count && Character.getNumericValue(dig.charAt(i))==0; i++);
        if(i == count){ // i will equal count only if dig consists of only zeroes
            dig = "0";
            count = 1;
            return; 
        }
        dig = dig.substring(i); //Based on index from loop, substring() is used to copy non-zero elements
        count = dig.length(); 
    }

    //Below are the add, minus, multiply, and divide methods outlined in the specifications
    public Calculator add(Calculator ref){

        //Case where both numbers are positive, simply using perviously define part_a() method to perform calculation
        if(dig.charAt(0) != '-' && ref.dig.charAt(0) != '-')
            return this.part_a(ref);

        //Case where this is positive but ref is negative. Uses signchange() to invert sign of ref and  then part_s() method to do this - ref
        if(dig.charAt(0) != '-' && ref.dig.charAt(0) == '-')
            return this.part_s(ref.signchange());

        //Case where this is negative and ref is positive. Uses signchange() to invert sign of this part_s() method to do ref - this
        if(dig.charAt(0) == '-' && ref.dig.charAt(0) != '-')
            return ref.part_s(this.signchange());

        //Case where both numbers are negative. Use signchange to invert signs of both, uses part_a() to add them, and then uses signchange() to invert total
        else
            return (this.signchange().part_a(ref.signchange())).signchange();

    }

    public Calculator minus(Calculator ref){//if ref neg and this pos
        
                  

        //Case where both numbers are positive, uses part_s() method defined before
        if(dig.charAt(0) != '-' && ref.dig.charAt(0) != '-')
            return this.part_s(ref);

        //Case where this is positive and ref is negative. Since part_s by a negative dig is the same as adding,  signchange() is used to invert...
        //... ref and then part_a() method used to add this and ref
        if(dig.charAt(0) != '-' && ref.dig.charAt(0) == '-')
            return this.part_a(ref.signchange());

        //Case where this is negative and ref is positive. Uses signchange() to invert this and then uses part_s() method to do ref - this
        if(dig.charAt(0) == '-' && ref.dig.charAt(0) != '-')
            return (ref.part_a(this.signchange()).signchange());

        //Case where both numbers are negative. Uses signchange() to invert both, then part_s() to minus flipped numbers, and then flips total
        else
            return (this.signchange().part_s(ref.signchange())).signchange();

    }
    
    public Calculator multiply(Calculator ref){

        //Case where both numbers are positive, simply uses karatsuba() method defined above
        if(dig.charAt(0) != '-' && ref.dig.charAt(0) != '-')
            return this.karatsuba(ref);

        //Case where this is positive and ref is negative. Flips the sign of ref, uses karatsuba(), and then flips sign of total
        if(dig.charAt(0) != '-' && ref.dig.charAt(0) == '-')
            return (this.karatsuba(ref.signchange())).signchange();

        //Case where this is negative and ref is positive. Flips the sign of this, uses muiltiplication, and then flips sign of total
        if(dig.charAt(0) == '-' && ref.dig.charAt(0) != '-')
            return (this.signchange().karatsuba(ref)).signchange();

        else //Case where both numbers are negative. Multiplication of two negatives is positive, so just flips sign of both and uses karatsuba
            return this.signchange().karatsuba(ref.signchange());
    }

    
    public Calculator signchange(){ //Given an integer, flips its sign. Positive becomes negative, and negative becomes positive
        Calculator flipped = new Calculator();

        if(dig.charAt(0) == '-'){ //Case where input is negative
            flipped.dig = dig.substring(1);
            flipped.count = count;
            return flipped;
        }
        //Below code deals with case of positive input
        flipped.dig = "-" + dig;
        flipped.count = count;
        return flipped;   
    }


   

    

  

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 *@author Name: Minhaj Shah    Student#: 400119266     MacID: shahm23
 */
public class Test {

    /**
     * @param args the command line arguments
     */
  

    public static void main(String[] args) {
        
        /*
        TEST CASE can handle any sort of arithmitic erros or invalid inputs
        including divide by 0, empty strings, empty quotations, any sign with positive
        or negative arithmitic, non integer symbols, or characters. Test Case was 
        approved by TA'S
        */
        
        Calculator a,b,c,d,e,f,g,h,i,j,k,l,m;
        System.out.println("TEST CASES");
      

        /*System.out.println("The follwing code conducts testing on Addition");
        a = new Calculator("-446");
        b = new Calculator("223");
        c = a.add(b);
        System.out.println("The result is: ");
        System.out.println(c.getLength());
            System.out.println("The result is: ");
        System.out.println(c);*/
        
        
        
        /*System.out.println("The follwing code conducts testing on Subtraction");
        d = new Calculator("8");
        e = new Calculator("-2");
        f = e.minus(d);
        System.out.println("The result is: ");
        System.out.println(f.getLength());
            System.out.println("The result is: ");
        System.out.println(f);*/
        
        
        
        /*System.out.println("The follwing code conducts testing on Multiplication");
        h = new Calculator("-6666666666");
        i = new Calculator("6666666666");
        j = h.multiply(i);
        System.out.println("The length is: ");
        System.out.println(j.getLength());
        System.out.println("The result is: ");
        System.out.println(j);*/
        
        
        
        /*System.out.println("The follwing code conducts testing on Division");
        k = new Calculator("2324");
        l = new Calculator("-2");
        m = k.divide(l);
        System.out.println("The length is: ");
        System.out.println(m.getLength());
        System.out.println("The result is: ");
        System.out.println(m);*/

    }
}




    

