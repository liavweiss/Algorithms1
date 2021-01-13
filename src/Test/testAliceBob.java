package Test;

public class testAliceBob {
    //**************************************************
//************************************************
//********** method returns 0 or 1 randomly **********
//************************************************
    public static int CoinFlips(){
        long result = 0;
        result = Math.round( Math.random());
        return (int)result;
    }
//**************************************************

    //**********************************************
//********** method returns result of Alice **********
//**********************************************
    public static int AliceGame(){
        return CoinFlips();
    }
    //**************************************************
//**********************************************
//********** method returns result of Bob **********
//**********************************************
    public static int BobGame(){
        return CoinFlips();
    }

    public static boolean game1(){
        boolean flag = false;
        if(BobGame() == 0 || AliceGame()==1){
            flag=true;
        }
        return flag;
    }

    public static boolean game2(){
        boolean flag = false;
        int resultBob=BobGame();
        int resultBobOpposite;
        if(resultBob==0){
            resultBobOpposite=1;
        }
        else{
            resultBobOpposite=0;
        }

        int resultAlice=AliceGame();
        int resultAliceOpposite;
        if(resultAlice==0){
            resultAliceOpposite=1;
        }
        else{
            resultAliceOpposite=0;
        }
        if(resultAlice==resultBobOpposite || resultBob==resultAliceOpposite){
            flag=true;
        }
        return flag;
    }

    public static boolean game3(){
        boolean flag = false;
        int resultBob=BobGame();
        int resultBobOpposite;
        if(resultBob==0){
            resultBobOpposite=1;
        }
        else{
            resultBobOpposite=0;
        }
        int resultAlice=AliceGame();

        if(resultAlice==resultBob || resultBobOpposite==resultAlice){
            flag=true;
        }
        return flag;
    }

    public static void main(String[] args) {
        int count = 10000000;
        int GameStrategy1 = 0;
        int GameStrategy2 = 0;
        int GameStrategy3 = 0;

        boolean result1 = false;
        boolean result2 = false;
        boolean result3 = false;
       
        for (int i = 0; i < count; i++){
            result1 = game1();
            if (result1 == true)
                GameStrategy1++;
            result2 = game2();
            if (result2 == true)
                GameStrategy2++;
            result3 = game3();
            if (result3 == true)
                GameStrategy3++;


        }
        System.out.println("Alice & Bob game : Strategy1 probability =" + (double)GameStrategy1/(double)count);
        System.out.println("Alice & Bob game : Strategy2 probability = " + (double)GameStrategy2/(double)count);
        System.out.println("Alice & Bob game : Strategy3 probability = " + (double)GameStrategy3/(double)count);


    }
}
