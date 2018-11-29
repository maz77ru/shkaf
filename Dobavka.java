package kz.mazur.shkaf_pro;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Dobavka {

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
    public String Rashet_stoimosti(Context context){
        aPer=context.getSharedPreferences("aPer",MODE_PRIVATE);
        String otvet="";
        //сначала вытаскиваем все переменные из шара
        int pora=aPer.getInt(PORADOK,0);
        int pribl= Integer.parseInt(aPer.getString(SAVED_PRIBL,"45"));
        int laminat= Integer.parseInt(aPer.getString(SAVED_LAMINAT,"2200"));
        int krom= Integer.parseInt(aPer.getString(SAVED_KROMKA,"25"));
        int dvp= Integer.parseInt(aPer.getString(SAVED_DBP,"550"));
        int naves= Integer.parseInt(aPer.getString(SAVED_NAVESA,"60"));
        int rushka= Integer.parseInt(aPer.getString(SAVED_RUCKI,"350"));
        int dostavka= Integer.parseInt(aPer.getString(SAVED_DOSTAVKA,"1200"));
        int napr= Integer.parseInt(aPer.getString(SAVED_NAPRAV,"550"));
        int sir= Integer.parseInt(aPer.getString(SHIRINA,"600"));
        int vis= Integer.parseInt(aPer.getString(VISOTA,"800"));
        int glu= Integer.parseInt(aPer.getString(GLUBINA,"450"));
        int por= Integer.parseInt(aPer.getString(COKOL,"100"));
        int pol1= Integer.parseInt(aPer.getString(POLKA_1,"2"));
        int pol2= Integer.parseInt(aPer.getString(POLKA_2,"1"));
        int pol3= Integer.parseInt(aPer.getString(POLKA_3,"3"));
        int tol= Integer.parseInt(aPer.getString(SAVED_TEXT,"16"));
        int zazor= Integer.parseInt(aPer.getString(SAVED_ZAZOR,"3"));
        int vis_fas= Integer.parseInt(aPer.getString(VISOTA_FACADA,"200"));
        int kol_shu= Integer.parseInt(aPer.getString(KOL_SHU,"3"));
        int vis_dver = vis-(por+(tol+zazor)); //загатовка на высоту двери
        sostav="шкаф высотой: "+vis+" шириной: "+sir+ " глубиной: "+glu+"\n";
        sostav=sostav+"размеры деталей"+"\n";
        String otvet_karkasa=Karkas(sir,vis,glu,por,tol);
        sostav=sostav+otvet_karkasa;
        int stoika=vis-(por+tol*2);//средняя стойка шкафа

        switch (pora){
            case 0:
                sostav=sostav+"5) "+vis_dver+"// - "+sir+ "// - 1 шт дверь"+"\n";
                if (pol1>0){ sostav=sostav+"6) "+glu+" - " +(sir-(tol*2))+"/ - "+pol1+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 3 шт  * " + naves+" = "+(naves*3)+"\n" +
                        "ручки: - 1 шт  * " + rushka+" = "+(rushka*1)+"\n";
                stoimost=stoimost+(3*naves+rushka);//стоимость 3х навесов и одной ручки
                ploshad=(sir*glu)*pol1;
                kromka=kromka+(vis+sir)*2;
                kromka=kromka+sir*pol1;
                break;
            case 1:
                sostav=sostav+"5) "+vis_dver+"// - "+((sir/2)-zazor)+ "// - 2 шт дверь"+"\n";
                if (pol1>0){ sostav=sostav+"6) "+glu+" - " +(sir-(tol*2))+"/ - "+pol1+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 6 шт * " + naves+" = "+(naves*6)+"\n" +
                        "ручки: - 2 шт  * " + rushka+" = "+(rushka*2)+"\n";
                stoimost=stoimost+(3*naves+rushka)*2;
                ploshad=(sir*glu)*pol1;
                kromka=kromka+((vis*2)+sir)*2;
                kromka=kromka+sir*pol1;
                break;
            case 2:
                sostav=sostav+(Narug_shuf_polnie(1,vis_fas,sir,glu,5,zazor,tol));
                sostav=sostav+"8) "+(vis_dver-(vis_fas+zazor))+"// - "+(sir-zazor)+ "// - 1 шт дверь"+"\n";
                dopolnit= "навесы: - 2 шт * " + naves+" = "+(naves*2)+"\n"  +
                        "ручки: - 2 шт  * " + rushka+" = "+(rushka*2)+"\n"+
                        "направляющих: - 1 пар  * " + napr+" = "+napr+"\n";;
                stoimost=stoimost+2*(naves+rushka)+napr;
                stoimost=stoimost+((glu*sir)/1000000)*dvp;//стоимость двп
                ploshad=ploshad+((sir+glu)*2)*(vis_fas-50);
                kromka=kromka+((vis*2)+(sir*6)+(glu*2));
                break;
            case 3:
                sostav=sostav+(Narug_shuf_polnie(1,vis_fas,sir,glu,5,zazor,tol));
                sostav=sostav+"8) "+(vis_dver-(vis_fas+zazor))+"// - "+((sir/2)-zazor)+ "// - 2 шт дверь"+"\n";
                dopolnit= "навесы: - 4 шт * " + naves+" = "+(naves*4)+"\n"  +
                        "ручки: - 3 шт  * " + rushka+" = "+(rushka*3)+"\n"+
                        "направляющих: - 1 пар  * " + napr+" = "+napr+"\n";;
                stoimost=stoimost+napr+(3*rushka)+(4*naves);
                stoimost=stoimost+((glu*sir)/1000000)*dvp;//стоимость двп
                ploshad=ploshad+((sir+glu)*2)*(vis_fas-50);
                kromka=kromka+((vis*4)+(sir*6)+(glu*2));
                break;
            case 4:
                sostav=sostav+(Narug_shuf_polnie(3,(((vis-(por+tol))/3)-zazor),sir,glu,5,zazor,tol));
                dopolnit= "ручки: - 3 шт  * " + rushka+" = "+(rushka*3)+"\n"+
                        "направляющих: - 3 пар  * " + napr+" = "+(napr*3)+"\n";;
                stoimost=stoimost+3*(napr+rushka);
                stoimost=stoimost+(((glu*sir)/1000000)*3)*dvp;//стоимость двп
                ploshad=ploshad+(((sir+glu)*2)*(vis_fas-50)*3);
                kromka=kromka+((vis*2)+(sir*12)+(glu*6));
                break;
            case 5: sostav=sostav+"5) "+vis_dver+"// - "+((sir/2)-zazor)+ "// - 2 шт дверь"+"\n";
                sostav=sostav+"6) "+ stoika+"/ - "+glu+" - 1 шт стенка"+"\n";
                if (pol1>0 ||pol2>0){ sostav=sostav+"7) "+glu+" - " +((sir-(tol*3))/2)+"/ - "+(pol1+pol2)+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 6 шт * " + naves+" = "+(naves*6)+"\n" +
                        "ручки: - 2 шт  * " + rushka+" = "+(rushka*2)+"\n";;
                stoimost=stoimost+(3*naves+rushka)*2;
                ploshad=((sir/2)*glu)*(pol1+pol2);
                kromka=kromka+((vis*5)+(sir*2))+((sir/2)*(pol1+pol2));
                break;
            case 6:  sostav=sostav+"5) "+ stoika+"/ - "+glu+" - 1 шт стенка"+"\n";
                sostav=sostav+"6) "+vis_dver+"// - "+((sir/2)-zazor)+ "// - 2 шт дверь"+"\n";
                sostav=sostav+(Narug_shuflatki(kol_shu,(((vis-(por+tol))/kol_shu)-zazor),(sir/2),glu,7,zazor,tol));
                if (pol1>0){ sostav=sostav+"10) "+glu+" - " +((sir-(tol*3))/2)+"/ - "+pol1+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 3 шт * " + naves+" = "+(naves*3)+"\n"  +
                        "ручки: - "+(kol_shu+1) +" шт  * " + rushka+" = "+(rushka*(kol_shu+1))+"\n"+
                        "направляющих: - "+(kol_shu)+" пар  * "   + napr+" = "+(napr*kol_shu)+"\n";;
                stoimost=stoimost+(1+kol_shu)*rushka+napr*kol_shu;
                stoimost=stoimost+(((glu*(sir/2))/1000000)*kol_shu)*dvp;//стоимость двп
                kromka=kromka+((vis*5)+(glu*(kol_shu*2))+(sir/2)*(kol_shu+2));
                ploshad=ploshad+((((sir/2)+glu)*2)*(vis-(por+(kol_shu*50))));
                break;
            case 7:  sostav=sostav+"5) "+ stoika+"/ - "+glu+" - 1 шт стенка"+"\n";
                String ret=Narug_shuflatki(2,vis_fas,(sir/2),glu,6,zazor,tol);
                sostav=sostav+ret;
                sostav=sostav+"10) "+(vis_dver-(vis_fas+zazor))+"// - "+((sir/2)-zazor)+ "// - 2 шт дверь"+"\n";
                if (pol1>0 ||pol2>0){ sostav=sostav+"11) "+glu+" - " +((sir-(tol*3))/2)+"/ - "+(pol1+pol2+2)+" шт. полок"+"\n"; }
                else { sostav=sostav+"11) "+glu+" - " +((sir-(tol*3))/2)+"/ - 2 шт. полок"+"\n"; }
                dopolnit= "навесы: - 6 шт * " + naves+" = "+(naves*6)+"\n"  +
                        "ручки: - 4 шт  * " + rushka+" = "+(rushka*4)+"\n"+
                        "направляющих: - 2 пар  * "   + napr+" = "+(napr*2)+"\n";;
                stoimost=stoimost+(3*naves+rushka)*2+napr*2;
                kromka=kromka+((vis*5)+(sir*6)+(glu*4));
                ploshad=ploshad+(((sir/2)*glu)*(pol1+pol2+2))+((((sir+glu)*2)*(vis_fas-50))*2);
                break;
            case 8:  sostav=sostav+"5) "+ stoika+"/ - "+glu+" - 1 шт стенка"+"\n";
            sostav=sostav+(Narug_shuflatki((kol_shu*2),(((vis-(por+tol))/kol_shu)-zazor),(sir/2),glu,6,zazor,tol));
                dopolnit= "ручки: - "+(kol_shu*2) +" шт  * " + rushka+" = "+(rushka*(kol_shu*2))+"\n"+
                        "направляющих: - "+(kol_shu*2)+" пар  * "   + napr+" = "+(napr*(kol_shu*2))+"\n";
                stoimost=stoimost+(rushka*(kol_shu*2))+(napr*(kol_shu*2));
                kromka=kromka+((vis*5)+(glu*(kol_shu*4))+((sir/2)*(kol_shu*8)));
                ploshad=ploshad+(vis*glu)+((((sir/3)+glu)*2)*(vis_dver-(kol_shu*50)))*2;
                break;
            case 9: sostav=sostav+"5) "+vis_dver+"// - "+((sir/3)-zazor)+ "// - 3 шт дверь"+"\n";
                sostav=sostav+"6) "+ stoika+"/ - "+glu+" - 1 шт стенка"+"\n";
                int dop=7;
                if (pol1>0){ sostav=sostav+(dop++)+") "+glu+" - " +((sir-(tol*3))/3)+"/ - "+pol1+" шт. полок"+"\n"; }
                if (pol2>0){ sostav=sostav+dop+") "+glu+" - " +((sir-(tol*3))-((sir-(tol*3))/3))+"/ - "+pol2+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 9 шт * " + naves+" = "+(naves*9)+"\n"  +
                        "ручки: - 3 шт  * " + rushka+" = "+(rushka*3)+"\n";
                stoimost=stoimost+(naves*9)+(rushka*3);
                kromka=kromka+((vis*7)+(sir*2));
                ploshad=ploshad+(vis*glu)+((glu*(sir/3))*pol1)+((glu*(sir/1.5))*pol2);
                break;
            case 10:  sostav=sostav+"5) "+ stoika+"/ - "+glu+" - 1 шт стенка"+"\n";
                sostav=sostav+"6) "+(vis_dver-(vis_fas+zazor))+"// - "+((sir/3)-zazor)+ "// - 3 шт дверь"+"\n";
                sostav=sostav+(Narug_shuflatki(1,vis_fas,(sir/3),glu,7,zazor,tol));
                String iop=Narug_shuflatki(1,vis_fas,(sir-(sir/3)),glu,10,zazor,tol);
                sostav=sostav+iop;
                int dopi=13;
                if (pol1>0){ sostav=sostav+(dopi++)+") "+glu+" - " +((sir-(tol*3))/3)+"/ - "+(pol1+1)+" шт. полок"+"\n"; }
                else {sostav=sostav+(dopi++)+") "+glu+" - " +((sir-(tol*3))/3)+"/ - 1 шт. полок"+"\n";}
                if (pol2>0){ sostav=sostav+dopi+") "+glu+" - " +((sir-(tol*3))-((sir-(tol*3))/3))+"/ - "+(pol2+1)+" шт. полок"+"\n"; }
                else {sostav=sostav+dopi+") "+glu+" - " +((sir-(tol*3))-((sir-(tol*3))/3))+"/ - 1 шт. полок"+"\n";}
                dopolnit= "навесы: - 9 шт * " + naves+" = "+(naves*9)+"\n"  +
                        "ручки: - 5 шт  * " + rushka+" = "+(rushka*5)+"\n"+
                        "направляющих: - 2 пар  * "   + napr+" = "+(napr*2)+"\n";
                stoimost=stoimost+((naves*9)+(rushka*5)+(napr*2));
                kromka=kromka+((vis*7)+(glu*4)+(sir*4));
                ploshad=ploshad+(vis*glu)+((glu*(sir/3))*pol1)+((glu*(sir/1.5))*pol2)+((((sir+glu)*2)*(vis_fas-50))*2);
                break;
            case 11:  sostav=sostav+"5) "+ stoika+"/ - "+glu+" - 1 шт стенка"+"\n";
                sostav=sostav+"6) "+vis_dver+"// - "+((sir/3)-zazor)+ "// - 2 шт дверь"+"\n";
                sostav=sostav+(Narug_shuflatki(kol_shu,(((vis-(por+tol))/kol_shu)-zazor),(sir/3),glu,7,zazor,tol));
                if (pol1>0){ sostav=sostav+"10) "+glu+" - " +((sir-(tol*3))-((sir-(tol*3))/3))+"/ - "+pol1+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 6 шт * " + naves+" = "+(naves*6)+"\n"  +
                        "ручки: - "+(kol_shu+2) +" шт  * " + rushka+" = "+(rushka*(kol_shu+2))+"\n"+
                        "направляющих: - "+(kol_shu)+" пар  * "   + napr+" = "+(napr*kol_shu)+"\n";
                stoimost=stoimost+((naves*6)+(rushka*(kol_shu+2))+(napr*kol_shu));
                kromka=kromka+((vis*7)+(glu*(kol_shu*2))+((sir/3)*(kol_shu*4)+4));
                ploshad=ploshad+(vis*glu)+((((sir/3)+glu)*2)*(vis_dver-(kol_shu*50)))+((sir/1.5)*pol1);
                break;
            case 12:  sostav=sostav+"5) "+vis_dver+"// - "+((sir/3)-zazor)+ "// - 3 шт дверь"+"\n";
                sostav=sostav+"6) "+ stoika+"/ - "+glu+" - 2 шт стенка"+"\n";
                if (pol1>0 || pol2>0 || pol3>0){
                    sostav=sostav+"7) "+glu+" - " +(((sir-(tol*4))/3))+"/ - "+(pol1+pol2+pol3)+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 9 шт * " + naves+" = "+(naves*9)+"\n"  +
                        "ручки: - 3 шт  * " + rushka+" = "+(rushka*3)+"\n";
                stoimost=stoimost+((naves*9)+(rushka*3));
                kromka=kromka+((vis*8)+(sir*2))+((sir/3)*(pol1+pol2+pol3));
                ploshad=ploshad+((vis*glu)*2)+(((sir/3)*glu)*(pol1+pol2+pol3));
                break;
            case 13: sostav=sostav+"5) "+ stoika+"/ - "+glu+" - 2 шт стенка"+"\n";
                sostav=sostav+(Narug_shuflatki((kol_shu*2),(((vis-(por+tol))/kol_shu)-zazor),(sir/3),glu,6,zazor,tol));
                if (pol1>0){ sostav=sostav+"9) "+glu+" - " +(((sir-(tol*4))/3))+"/ - "+pol1+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 3 шт * " + naves+" = "+(naves*3)+"\n"  +
                        "ручки: - "+((kol_shu*2)+1) +" шт  * " + rushka+" = "+(rushka*((kol_shu*2)+1))+"\n"+
                        "направляющих: - "+(kol_shu*2)+" пар  * "   + napr+" = "+(napr*(kol_shu*2))+"\n";
                stoimost=stoimost+((naves*3)+(rushka*((kol_shu*2)+1))+(napr*(kol_shu*2)));
                kromka=kromka+((vis*8)+(sir*2)+(glu*(kol_shu*4))+((sir/3)*(kol_shu*8)))+((sir/3)*pol1);
                ploshad=ploshad+((vis*glu)*2)+((glu*(sir/3))*pol1)+((((sir/3)+glu)*2)*(vis_dver-(kol_shu*50)))*2;
                break;
            case 14: sostav=sostav+"5) "+ stoika+"/ - "+glu+" - 2 шт стенка"+"\n";
                sostav=sostav+(Narug_shuflatki(kol_shu,(((vis-(por+tol))/kol_shu)-zazor),(sir/3),glu,6,zazor,tol));
                if (pol1>0||pol2>0){ sostav=sostav+"9) "+glu+" - " +(((sir-(tol*4))/3))+"/ - "+(pol1+pol2)+" шт. полок"+"\n"; }
                dopolnit= "навесы: - 6 шт * " + naves+" = "+(naves*6)+"\n"  +
                        "ручки: - "+(kol_shu+2) +" шт  * " + rushka+" = "+(rushka*(kol_shu+2))+"\n"+
                        "направляющих: - "+(kol_shu)+" пар  * "   + napr+" = "+(napr*kol_shu)+"\n";
                stoimost=stoimost+((naves*6)+(rushka*(kol_shu+2))+(napr*kol_shu));
                kromka=kromka+((vis*8)+(sir*2)+(glu*(kol_shu*2))+((sir/3)*(kol_shu*4)))+((sir/3)*(pol1+pol2));
                ploshad=ploshad+((glu*(sir/3))*(pol1+pol2))+((((sir/3)+glu)*2)*(vis_dver-(kol_shu*50)))+((vis*glu)*2);
                break;
        }
        sostav=sostav+"Расходы на материал : "+"\n";
       sostav=sostav+(Dvp(sir,vis,por,pora,glu,tol,kol_shu));
        sostav=sostav+"Себестоимость: "+"\n";
        sostav=sostav+dopolnit;
        ploshad=ploshad+(sir+(glu*2))*vis;
        ploshad=ploshad+(sir*glu)*2;
        ploshad=ploshad/1000000;
        sostav=sostav+"ЛДСП : " +ploshad+" кв.м * "+laminat+" = "+(ploshad*laminat)+"\n";
        stoimost=stoimost+ploshad*laminat;//стоимость лдсп
        kromka=kromka+(vis+sir+glu)*2;
        kromka = kromka/ 1000;
        sostav=sostav+"кромка : "+kromka+" метра * "+krom+" = "+(kromka*krom)+"\n";
        stoimost=stoimost+kromka*krom;//стоимость кромки
        sostav=sostav+"ДВП : " +((vis*sir)/1000000)+" кв.м * "+dvp+" = "+((vis*sir)/1000000)*dvp+"\n";
        stoimost=stoimost+((vis*sir)/1000000)*dvp;//стоимость двп
        sostav=sostav+"доставка 2 раза по "+dostavka+ " = "+(dostavka*2)+"\n";
        stoimost=stoimost+dostavka*2;//доставка
        int sto=(int)stoimost;
        sostav=sostav+"Итого : "+sto;
        otvet=sostav;
  return otvet;
    }
    public String Ctoim(Context context){
        aPer=context.getSharedPreferences("aPer",MODE_PRIVATE);
        int pribl= Integer.parseInt(aPer.getString(SAVED_PRIBL,"45"));
        String resh=Rashet_stoimosti(context);
        int inde=resh.indexOf("Итого");
        inde=inde+7;
        String otre=resh.substring(inde);
        otre=otre.trim();
        int otve= Integer.parseInt(otre);
         otve=otve+(otve/100)*pribl;
         otre=""+otve;
        return otre;
    }

    public String Vnut_suflatki(int kol_shu, int vis_fa, int sir, int glu, int poradok, int zazor, int tol){
         int kol_bokStenok=kol_shu*2;
         int vis_bokStenok=vis_fa-40;
         int zad_stenka=sir-(tol*6+26);
         String otvet="";
         otvet=otvet+poradok+")"+(kol_shu*vis_fa)+"/  - "+glu+" - "+ 2+"шт боковые добавки на стенки"+"\n";
         poradok++;
         otvet=otvet+poradok+") "+(vis_fa-zazor)+"// - "+(sir-(tol+zazor)*2)+" - "+kol_shu+" шт фасады шуфляток"+"\n";
         poradok++;
         otvet=otvet+poradok+") "+vis_bokStenok+" - "+(glu-50)+"/ - "+kol_bokStenok+" шт бок шуфляток"+"\n";
         poradok++;
         otvet=otvet+poradok+") "+vis_bokStenok+" - "+zad_stenka+"/ - "+kol_bokStenok+" шт зад, перед шуфляток"+"\n";
         return otvet;
     }

     public String Narug_shuflatki(int kol_shu, int vis_fa, int sir, int glu, int poradok, int zazor, int tol){
        String otvet="";
        int kol_bokStenok=kol_shu*2;
        int vis_bokStenok=vis_fa-50;
        int zad_stenka=sir-(tol*3+26+tol/2);
        otvet=otvet+poradok+") "+vis_fa+"// - "+(sir-zazor)+"// - "+kol_shu+" шт фасады шуфляток"+"\n";
         poradok++;
         otvet=otvet+poradok+") "+vis_bokStenok+" - "+glu+"/ - "+kol_bokStenok+" шт бок шуфляток"+"\n";
         poradok++;
         otvet=otvet+poradok+") "+vis_bokStenok+" - "+zad_stenka+"/ - "+kol_bokStenok+" шт зад, перед шуфляток"+"\n";
        return otvet;
     }

     public String Narug_shuf_polnie(int kol_shu, int vis_fa, int sir, int glu, int poradok, int zazor, int tol){
         String otvet="";
         int kol_bokStenok=kol_shu*2;
         int vis_bokStenok=vis_fa-50;
         int zad_stenka=sir-(tol*4+26);
         otvet=otvet+poradok+") "+vis_fa+"// - "+(sir-zazor)+"// - "+kol_shu+" шт фасады шуфляток"+"\n";
         poradok++;
         otvet=otvet+poradok+") "+vis_bokStenok+" - "+glu+"/ - "+kol_bokStenok+" шт бок шуфляток"+"\n";
         poradok++;
         otvet=otvet+poradok+") "+vis_bokStenok+" - "+zad_stenka+"/ - "+kol_bokStenok+" шт зад, перед шуфляток"+"\n";
         return otvet;
     }

    public String Karkas(int sir, int vis, int glu, int por, int tol){
        String otvet="";
        int poradok=0;
        int result=sir-(tol*2);
        if (por==0){poradok++;
            int visa=vis-tol*2;
            otvet = otvet + poradok+") "+visa + "-" + glu + " - 2шт-бок" + "\n";
            poradok++;
            otvet = otvet +poradok+") "+sir + "/ - " + glu + "// - 1 шт - низ" + "\n";
        }
        else {poradok++;
            int visa=vis-tol;
            otvet = otvet + poradok+") "+visa + "-" + glu + " - 2шт-бок" + "\n";
            poradok++;
            otvet = otvet + poradok+") "+result + "/ - " + glu + "// - 1 шт - низ" + "\n";
            poradok++;
            otvet=otvet +poradok+") "+por+ " - " +result+ " - 2 шт цоколь"+"\n";
        }
        int verh=glu+20;
        poradok++;
        otvet=otvet+poradok+") "+sir+ "/ - " +verh+ "// - 1 шт верх"+"\n";
        return otvet;
    }

    public String Dvp(int sir, int vis, int por, int pora, int glu, int tol, int kol_shu){
        String revers="ДВП :"+(sir-2)+" мм * "+(vis-por)+" мм"+"\n";
        switch (pora){
            case 2: revers=revers+"ДВП :"+(glu-2)+" мм * "+(sir-(tol*2+28))+" мм"+"\n";
                break;
            case 3:  revers=revers+"ДВП :"+(glu-2)+" мм * "+(sir-(tol*2+28))+" мм"+"\n";
               break;
            case 4:   revers=revers+"ДВП :"+(glu-2)+" мм * "+(sir-(tol*2+28))+" мм - 3 штуки"+"\n";
                break;
            case 6: revers=revers+"ДВП :"+(glu-2)+" мм * "+((sir/2)-(tol*1.5+28))+" мм - "+kol_shu+" штуки"+"\n";
                break;
            case 7:  revers=revers+"ДВП :"+(glu-2)+" мм * "+((sir/2)-(tol*1.5+28))+" мм - 2 штуки"+"\n";
                break;
            case 8: revers=revers+"ДВП :"+(glu-2)+" мм * "+((sir/2)-(tol*1.5+28))+" мм - "+(kol_shu*2)+" штуки"+"\n";
                break;
            case 10:revers=revers+"ДВП :"+(glu-2)+" мм * "+((sir/3)-(tol*1.5+28))+" мм - 1 штуки"+"\n";
                revers=revers+"ДВП :"+(glu-2)+" мм * "+((sir/1.5)-(tol*1.5+28))+" мм - 1 штуки"+"\n";
                break;
            case 11: revers=revers+"ДВП :"+(glu-2)+" мм * "+((sir/3)-(tol*1.5+28))+" мм - "+kol_shu+" штуки"+"\n";
                break;
            case 13:revers=revers+"ДВП :"+(glu-2)+" мм * "+((sir/3)-(tol*1.5+28))+" мм - "+(kol_shu*2)+" штуки"+"\n";
                break;
            case 14: revers=revers+"ДВП :"+(glu-2)+" мм * "+((sir/3)-(tol*1+28))+" мм - "+kol_shu+" штуки"+"\n";
                break;
                default:
                    break;

        }
        return revers;
    }
	public String Kol_navesov(){
		String naves ="";
		return naves;
	}

}
