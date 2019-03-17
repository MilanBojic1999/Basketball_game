import java.util.Random;

public class Mec {
    private Tim tim1;
    private Tim tim2;
    private final int rezultati[][]={    {0,0,0,1,1,2,2,2,2,3},
                                            {0,0,1,1,2,2,2,2,3,3},
                                            {0,0,1,2,2,2,2,3,3,3},
                                            {0,1,2,2,2,2,3,3,3,4}   };
    public Mec(Tim tim1,Tim tim2){
        this.tim1=tim1;
        this.tim2=tim2;
    }
    public void odigrajMec(){
        this.ispisiMec();
        int napad1=this.tim1.getNapad()-this.tim2.getOdbrana()+50;
        int napad2=this.tim2.getNapad()-this.tim1.getOdbrana()+50;
        int rezultat1=0,rezultat2=0;
        Random r=new Random();
        for (int vreme=0;vreme<100;vreme++){
            if(vreme%2==0){
                rezultat1+=rezultati[napad1/25][r.nextInt(10)];
            }else{
                rezultat2+=rezultati[napad2/25][r.nextInt(10)];
            }
           /* if(vreme%25==0){
                System.out.println("------------");
                System.out.println("Kraj cetvrtine");
                System.out.println("------------");

            }*/
        }
        System.out.println(" "+rezultat1+" : "+rezultat2);
        if(rezultat1 > rezultat2){
            System.out.println("Pobedio je prvi tim");
            tim1.dodajRezultat(2,rezultat1,rezultat2);
            tim2.dodajRezultat(1,rezultat2,rezultat1);
        }else{
            System.out.println("Pobedio je drugi tim");
            tim2.dodajRezultat(2,rezultat2,rezultat1);
            tim1.dodajRezultat(1,rezultat1,rezultat2);
        }
    }

    public void ispisiMec(){
        System.out.println(tim1.toString()+" protiv "+tim2.toString());
    }
}
