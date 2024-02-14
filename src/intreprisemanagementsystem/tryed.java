/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.util.Scanner;

/**
 *
 * @author Doshe
 */
public class tryed {
    
        public static void main(String[] args) {
    
    int number = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please type a number between 0 and 999 OR type -1 to exit:  ");
            number = scanner.nextInt();
            while(number!=-1){
                if(number>=0 && number<=9999){
                    if(number==0){
                        System.out.print("\tZERO");
                    } else {
                       //0
                     //  System.out.print("\t");
                        numberToWord(((number / 100) % 10), " HUNDRED");
                        numberToWord(((number / 1000) % 100), " KKKKKKKKK");
                        numberToWord((number % 100), " ");
                        numberToWord((number % 1000), " ");
                    }

                } else{
                    System.out.print("NUMBER OUT OF RANGE");
                }
                System.out.print("\nPlease type a number between 0 and 999 OR type -1 to exit:  ");
               
              number = scanner.nextInt();
            }
        }

        
        
        public static void numberToWord(int num, String val) {
            String ones[] = {" ", " ONE", " TWO", " THREE", " FOUR", " FIVE", " SIX", " SEVEN", " EIGHT", " NINE", " TEN", " ELEVEN", " TWELVE", " THIRTEEN", " FOURTEEN", " FIFTEEN", " SIXTEEN", " SEVENTEEN", " EIGHTEEN", " NINETEEN"
            };
            String tens[] = {" ", " ", " TWENTY", " THIRTY", " FOURTY", " FIFTY", " SIXTY", " SEVENTY", " EIGHTY", " NINETY"};
             String cents[] = {" ", " ","HUNDRED", " TWO-HUNDRED","THREE-HUNDRED", " FOUR-HUNDRED", " FIVE-HUNDRED", " SIX-HUNDRED", " SEVEN-HUNDRED", " EIGHT-HUNDRED", " NINE-HUNDRED"};
            if (num > 19) {
                System.out.print(tens[num / 10] + " " + ones[num % 10]);
            } else if (num > 999){
                        System.out.print(tens[num / 100] + " " + ones[num % 100]);
                        }
            else  {
                System.out.print(ones[num]);
            }
          // if (num > 0) {
              //  System.out.print(val);
           //}
        }
    }
    

