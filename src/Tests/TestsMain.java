package Tests;

import java.util.Scanner;

public class TestsMain {

	private static Scanner sc;

	public static void main(String[] args) {
        String menu = "\n============================================================================================"
                + "\nTester : 1. Utilisateur - 2. Message - 3. Categorrie - 4. Communauté - 0. Fin   Votre choix : ";
        int var;
        sc = new Scanner(System.in);
        TestsUtilisateur utili = new TestsUtilisateur();
        TestsCategorie cat = new TestsCategorie();
        TestsCommunaute comm = new TestsCommunaute();
        TestsMessage msg = new TestsMessage();
        
        a : do {  
            System.out.print(""+menu);
            var=sc.nextInt();
            System.out.println("\n============================================================================================\n");
            switch (var) {
                case 1: utili.testUti();break;
                case 2: cat.testMsg();break;  
                case 3: comm.testCat();break;
                case 4: msg.testComm();break;
                case 0: System.out.println("----------------Fin des tests-------------------");
                        break a;
                default:System.err.println("Erreur de saisie ! \n\n");
            }
 
        } while (true);
    }

}
