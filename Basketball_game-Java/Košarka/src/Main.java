import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
	private static List<Klub> klubovi;
	static {
		klubovi=new ArrayList<>();
	}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AbsLiga liga = new LigaGrupe("Super liga", 4,10);
        int i;
        System.out.println(liga.toString().toUpperCase());
        do {
            Main.getInfo();
            i = sc.nextInt();
            switch (i) {
                case 1: {
                    Tim tim = Main.upisiKlub(sc);
                    liga.dodajTim(tim);
                    break;
                }
                case 2: {
                    liga.napraviMeceve();
                    break;
                }
                case 3: {
                    liga.ispisiTabelu();
                    break;
                }
                case 4: {
                    liga.ispisiMeceve();
                    break;
                }
                case 5: {
                    liga.odigrajMeceve();
                    break;
                }
                case 6: {
                    liga.odigrajSpecMec(sc);
                    break;
                }
                case 7:{
                	RadSaFajlom.ucitajTimove(liga);
                	break;
                }
                case 8:{
                	RadSaFajlom.upisiTimove(liga);
                	break;
                }
                case 9:{
                	liga.odigrajPlayOff();
                	break;
                }
                case 0: {
                	sc.nextLine();
                	sc.close();
                    break;
                }
                default:
                    System.out.println("Nema takvih komandi");
            }
        } while (i != 0);
    }

    private static void getInfo() {
        System.out.print("1) Napravite tim\n" + 
        		"2) Napravite meceve\n" + 
        		"3) Ispisite tabelu\n" + 
        		"4) Ispisite meceve\n" + 
        		"5) Odigraj sve meceve\n" + 
        		"6) Odigrajte specifični mec\n" + 
        		"7) Ucitajte Timova sa fajla\n" +
        		"8) Upisite Timove u Fajl\n" +
        		"9) Odigraj pley-off\n" +
        		"0) Izađite iz programa\n");
    }
    public static void dodajKlub(Klub klub) {
    	if(klubovi.contains(klub)) {
    		System.out.println("Već postoji klub u sistemu");
    		return;
    	}
        klubovi.add(klub);
    }
    
    private static Tim upisiKlub(Scanner sc) {
        sc.nextLine();
        System.out.print("Kako se zove vas tim: ");
        String ime = sc.nextLine();
        System.out.print("Kakav je napad vaseg tima: ");
        int nap = sc.nextInt();
        System.out.print("Kakva je odbrana vaseg tima: ");
        int odb = sc.nextInt();
        sc.nextLine();
        Klub klub=new Klub(ime, nap, odb);
        dodajKlub(klub);
        return new Tim(klub);
        
    }
    private static Tim napraviTim(String linija) {
    	String[] clan=linija.split(" - ");
    	String[] stats=clan[1].split(" : ");
    	int napad=Integer.parseInt(stats[0]);
    	int odbrana=Integer.parseInt(stats[1]);
    	Klub klub=new Klub(clan[0], napad,odbrana);
    	return new Tim(klub);
    }
    
   public static class RadSaFajlom{
    	private static boolean ucitano=false;
    	static void ucitajTimove(AbsLiga liga) {
    		File ulaz=new File("Košarka"+File.separator+"Timovi.txt");
    		try(BufferedReader bReader=new BufferedReader(new FileReader(ulaz))){
    			System.out.println(bReader.readLine());
    			String linija=bReader.readLine();
    			do {
    				liga.dodajTim(napraviTim(linija));
        			linija=bReader.readLine();
    			}while(linija!=null);
    		}catch (IOException e) {
    			e.printStackTrace();
			}finally {
				ucitano=true;
			}
    	}
    	static void upisiTimove(AbsLiga liga) {
    		File izlaz=new File("Košarka"+File.separator+"Timovi.txt");
    		if(!ucitano)	
    			ucitajTimove(liga);
    		try(BufferedWriter bWriter=new BufferedWriter(new FileWriter(izlaz))) {
    			liga.SortByName();
    			bWriter.write(liga.toString());
    			bWriter.newLine();
                for (Tim tim : liga.getTimovi()) {
                    bWriter.write(tim.toString());
                    bWriter.newLine();
                }
    		}catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
}
