/*
         String hex ="7400";
        Integer.parseInt(hex,16);    
System.out.print(Integer.toBinaryString(Integer.parseInt(hex,16)));
 */
package javaapplication109;

import java.util.Scanner;

public class JavaApplication109 {
    
static String ac,AR,IR;
static char e='0',s='0',I='0',SC;
static int pc=0;
static char sign_flipflop;

    public static void main(String[] args) {
      Scanner input=new Scanner(System.in);
      int check=0,j=0,int_ac=0,temp=0;
      boolean excute=true;
      String n,instruction,decode, address_field;
      
      
       String instruction_arr[]={  "AC<-0",
                                   "E-<0",
                                   "AC<-AC'",
                                   "E<-E'",
                                   "AC<-SHR(AC),AC(15)<-E,E<-AC(0)",
                                   "AC<-SHL(AC),AC(0)<-E,E<-AC(15)",
                                   "AC<-AC+1","IF(AC(15)=0)THEN PC<-PC+1",
                                   "IF(AC(15)=1)THEN PC<-PC+1",
                                   "IF(AC=0)THEN PC<-PC+1",
                                   "IF(E=0)THEN PC<-PC+1",
                                   "s<-0"
                               };
       
      String arr[]={"t0","t1","t2","t3d7I'","AC clr","AC inc","AC ld","pc inc"};
      
      int timing[][] = new  int[arr.length][arr.length];
      
      
     
      while(excute){
       System.out.println("choose\n[1]start\n[2]exite");
       int n_num = input.nextInt();
       if(n_num==1)
    {   
       System.out.println("ENTER REGISTEN INSTRUCTION IN HEXADECIMAL:");
       n=input.next();
       if (n.charAt(0)=='7') {
                System.out.println("this is register instruction");
                instruction=Integer.toBinaryString(Integer.parseInt(n,16));
                address_field=instruction.substring(4);
                System.out.println( "instruction is in binary: "+ I+instruction);
                
          
        System.out.println("ENTER THE INTIAL VALUE OF AC IN DECIAML");     
        int_ac = input.nextInt();
        ac=Integer.toString(int_ac, 2);
       
        System.out.println("THE ENTER VALUE IN BINARY EQUAVIELNT IS: "+ac);
      
         int i=ac.length();
          if(ac.length()<16)
             {
                 
               
                        while(i!=16)
                        {
                            
                            ac="0"+ac;
                            i++;
                        
                        }
                        
             }
            System.out.println("THE CONTENT OF AC IS: "+ac); 
      
      //start
       System.out.println("SC<-"+SC); 
       SC='0';
       System.out.println("\nSC<-"+SC); 
      //fetch decode 
      //t0
      timing[0][0]=1;
      System.out.println("\nT0 IS activate");
      AR=String.valueOf(pc);
     System.out.println("the content of pc is: "+pc+" after AR<-PC  the AR is: "+AR);
      //t1
      timing[1][1]=1;
      System.out.println("\nT1 IS activate");
      IR =instruction;
      pc++;
      timing[7][3]=1;
      System.out.println("AFTER IR<-M[AR], PC<-PC+1 THE IR: "+(I+IR)+" THE PC: "+pc);
      //t2
      timing[2][2]=1;
      I='0';//BECUASE WE IMPLEMENT REGISTER INSTRUCTION SO NO NEED TO CHECK IF I =0 OR I=1 IT IS ALWAYSE I=0
      AR=IR.substring(3);
      decode=IR.substring(0, 3);
     System.out.println("\nAR<-IR(0-11): "+AR+"  I<-IR(15):"+I+" DECODE CODE IN IR(12-14): "+decode);
      
      
      //excute
        timing[3][3]=1;
        System.out.println("T3 IS activate");
        for(int A=0;A< AR.length();A++)
           {
              if( AR.charAt(A) == '1')
              {
                 
                  j=A;

              }
       
           }
        System.out.println("THE INSTRUCTION IS: "+instruction_arr[j]);

      switch(j)
      {
      
          case 0:
              
              String t="";
              for(int m=0;m<ac.length();m++)
              {
                if(ac.charAt(m)=='1')
                  t=t+'0';
                else
                 t=t+'0';  
              
              }
            ac=t;
          timing[4][3]=1;
          break;
          case 1:
               e='0';
          break; 
          case 2:
              ac=complement_operation(ac);
               // timing[6][3]=1;
               break; 
          case 3:
               if(e=='0') 
                     {
                     e='1';
                     }
                     else
                     {
                     e='0';
                     }
              
               break; 
          case 4:
                e=ac.charAt(0);
                ac=ac.substring(1);
                ac=  ac+e;
             // timing[6][3]=1;
               break; 
          case 5:
               e=ac.charAt((ac.length())-1);
               ac=ac.substring(0,(ac.length())-1);
               ac= e+ac;
            //   timing[6][3]=1;
                break; 
          case 6:
               temp=temp+(int_ac+1);
                     ac=Integer.toString(temp, 2);
                    
                      if(ac.length()<16)
                    {
                        int o=ac.length();
                        while(o!=16)
                        {
                            
                            ac="0"+ac;
                            o++;
                        
                        }
                    }
                   timing[5][3]=1;
                    break; 
          case 7:
                     sign_flipflop=ac.charAt(0);
                       if(sign_flipflop=='0')
                       {
                          
                       pc++;
                       
                       }
                 timing[7][3]=1;
                  break; 
          case 8:
                    sign_flipflop=ac.charAt(0);
                       if(sign_flipflop=='1')
                       {
                          
                       pc++;
                       
                       }
                 timing[7][3]=1;
                  break; 
                       
          case 9:
               if(ac=="0000000000000000")
                       {
                          
                       pc++;
                       
                       }
               timing[7][3]=1;
                break; 
          case 10:
                if(e=='0')
                       {
                          
                       pc++;
                       
                       }
                timing[7][3]=1;
                 break; 
          case 11:
                s='0';
                 break; 
                 
          default:
              
       System.out.println("error");
       System.exit(0);
       break;
               
      
      }
                
      System.out.println("after excute the instruction ........");
      System.out.println("after excute the instruction the content of ac is: "+ac);
      System.out.println("after excute the instruction the content of e is: "+e);
      System.out.println("after excute the instruction the content of s: "+s); 
      
      for(int r=0;r<arr.length;r++) 
      {
         System.out.print(arr[r]+"\t\t");
        for(int k=0;k<arr.length;k++) 
      {
         
          System.out.print(timing[r][k]+"\t") ; 
              
      
      }
         System.out.println();
      
      }
        //--------------------        

      }
       
       else
       {
       
          System.out.println( "incorrect entry :(");
         
          break;
       
       }
       timing[0][0] =0;
       timing[1][1] =0;
       timing[2][2] =0;
       timing[3][3] =0;
       timing[4][3] =0;
       timing[5][3] =0;
       timing[6][3] =0;
       timing[7][3] =0;
    }else if (n_num==2){System.out.println( "thank you");
            excute=false;
          break;}
       else
       {  
        System.out.println( "something went wronge :(");
       
       }
       
      }
   
    
    
    
}
     public static String complement_operation(String ac)
    {
        String temp="";
        
        for(int i=0;i<ac.length();i++)
        {
            if(ac.charAt(i)=='1')
              temp=temp+'0';
            else
            temp=temp+'1';
                
             
        }
   

     return temp;
    }
}
