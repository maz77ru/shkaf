package kz.mazur.shkaf_pro;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Dobavka_kuhni {
    SharedPreferences aPer;
    private static final String PORADOK="0";
    private static final String POLKA_1="2";
    private static final String POLKA_2="1";
    private static final String POLKA_3="3";
    private static final String VISOTA_FACADA="200";
    private static final String KOL_SHU="3";
    private static final String VISOTA = "800";
    private static final String SHIRINA = "600";
    private static final String GLUBINA = "450";
    private static final String COKOL = "100";
    private static final String SAVED_TEXT = "16";
    private static final String SAVED_PRIBL = "45";
    private static final String SAVED_LAMINAT = "2200";
    private static final String SAVED_KROMKA = "25";
    private static final String SAVED_DBP = "550";
    private static final String SAVED_NAVESA = "60";
    private static final String SAVED_RUCKI = "350";
    private static final String SAVED_DOSTAVKA ="1200" ;
    private static final String SAVED_NAPRAV ="550";
    private static final String SAVED_TOL_KROMKI ="1";
    private static final String SAVED_ZAZOR ="3";
    String sostav,dopolnit;
    double stoimost, kromka,ploshad;
    public String Rashet_stoimosti(Context context) {
        aPer = context.getSharedPreferences("aPer", MODE_PRIVATE);
        String otvet = "";
        //сначала вытаскиваем все переменные из шара
        int pora = aPer.getInt(PORADOK, 0);
        int laminat = Integer.parseInt(aPer.getString(SAVED_LAMINAT, "2200"));
        int krom = Integer.parseInt(aPer.getString(SAVED_KROMKA, "25"));
        int dvp = Integer.parseInt(aPer.getString(SAVED_DBP, "550"));
        int naves = Integer.parseInt(aPer.getString(SAVED_NAVESA, "60"));
        int rushka = Integer.parseInt(aPer.getString(SAVED_RUCKI, "350"));
        int dostavka = Integer.parseInt(aPer.getString(SAVED_DOSTAVKA, "1200"));
        int napr = Integer.parseInt(aPer.getString(SAVED_NAPRAV, "550"));
        int sir = Integer.parseInt(aPer.getString(SHIRINA, "600"));
        int vis = Integer.parseInt(aPer.getString(VISOTA, "800"));
        int glu = Integer.parseInt(aPer.getString(GLUBINA, "450"));
        int por = Integer.parseInt(aPer.getString(COKOL, "100"));
        int pol1 = Integer.parseInt(aPer.getString(POLKA_1, "2"));
        int pol2 = Integer.parseInt(aPer.getString(POLKA_2, "1"));
        int pol3 = Integer.parseInt(aPer.getString(POLKA_3, "3"));
        int tol = Integer.parseInt(aPer.getString(SAVED_TEXT, "16"));
        int zazor = Integer.parseInt(aPer.getString(SAVED_ZAZOR, "3"));
        int vis_fas = Integer.parseInt(aPer.getString(VISOTA_FACADA, "200"));
        int kol_shu = Integer.parseInt(aPer.getString(KOL_SHU, "3"));
        int vis_dver = vis - (por + (tol + zazor)); //загатовка на высоту двери
        sostav = "шкаф высотой: " + vis + " шириной: " + sir + " глубиной: " + glu + "\n";
        sostav = sostav + "размеры деталей" + "\n";
        String karkas=Karkas(sir,vis,glu,por,tol);
        sostav=sostav+karkas;
        int sto=(int)stoimost;
        switch (pora){
            case 101:
                break;
        }
        sostav=sostav+"Итого : "+sto;
        otvet=sostav;
        return otvet;
    }

    public String Karkas(int sir, int vis, int glu, int por, int tol) {
        String otvet = "";
        int poradok = 0;
        int result = sir - (tol * 2);
        if (por > 0) {
            poradok++;
            otvet = otvet + poradok + ") " + vis + "/ - " + glu + " - 2 шт - бок" + "\n";
            poradok++;
            otvet = otvet + poradok + ") " + result + "/ - " + glu + " - 1 шт - низ" + "\n";
            poradok++;
            otvet = otvet + poradok + ") " + por + " - " + result + " - 1 шт - цоколь" + "\n";
            poradok++;
            otvet = otvet + poradok + ") " + 70 + " - " + result + "/ - 2 шт - упор" + "\n";
        }
        else {
            int vis_korpusa=vis-(tol+100);
            poradok++;
            otvet=otvet+ poradok + ") " + vis_korpusa + "/ - " + glu + " - 2 шт - бок" + "\n";
            poradok++;
            otvet = otvet + poradok + ") " + sir + "/ - " + glu + "// - 1 шт - низ" + "\n";
            poradok++;
            otvet = otvet + poradok + ") " + 70 + " - " + result + "/ - 2 шт - упор" + "\n";
        }
        return otvet;
    }

}
