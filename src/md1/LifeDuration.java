package md1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LifeDuration {

    public static void main(String[] args) {


        Random random = new Random();
        List<DeadPerson> mirusieCilveki = new ArrayList<DeadPerson>();
        List<DeadPerson> mirusieViriesi = new ArrayList<DeadPerson>();
        List<DeadPerson> mirusieSievietes = new ArrayList<DeadPerson>();

//        Generejam datus
        for (int i = 0; i < 2000; i++) {
            mirusieCilveki.add(new DeadPerson(1900 + random.nextInt(51),
                    1951 + random.nextInt(51), Gender.randomGender()));
        }

//        Sadalam datus uz virisiem un sievietem
        for (DeadPerson mirusaisCilveks : mirusieCilveki
        ) {
            if (mirusaisCilveks.gender == Gender.MALE) {
                mirusieViriesi.add(mirusaisCilveks);
            } else mirusieSievietes.add(mirusaisCilveks);
        }

        System.out.println("Mirušo cilvēku skaits: " + mirusieCilveki.size());
        System.out.println("Mirušo vīriešu skaits: " + mirusieViriesi.size());
        System.out.println("Mirušo sieviešu skaits: " + mirusieSievietes.size());

//        Izrekinam videjo dzives ilgumu virisiem un sievietem
        videjaisDzivesIlgums(mirusieCilveki);
        System.out.println("Izrēķinām vidējo dzīves ilgumu virušiem");
        videjaisDzivesIlgums(mirusieViriesi);
        System.out.println();
        System.out.println("Izrēķinām vidējo dzīves ilgumu sievietēm");
        videjaisDzivesIlgums(mirusieSievietes);

    }

    public static void videjaisDzivesIlgums(List<DeadPerson> mirusieCilveki) {
        System.out.println("Mirušo skaits: " + mirusieCilveki.size());

        int maksimalaisVecums = 0;
        for (DeadPerson deadPerson : mirusieCilveki) {
            if (deadPerson.age > maksimalaisVecums) {
                maksimalaisVecums = deadPerson.age;
            }
        }
        maksimalaisVecums += 1; // +1 - lai ieskatītu 0 gadu
        System.out.println("Maksimālais vecums: " + (maksimalaisVecums - 1));

//        Inicializējam vecuma masīvu no 0 gadu līdz maksimālajam vecumam + 1 gads
        int[] vecums = new int[maksimalaisVecums + 1];
        for (int i = 0; i < maksimalaisVecums + 1; i++) {
            vecums[i] = i;
        }

//        Saskaitām cik cilvēku nomira noteikta vecuma
        int[] nomiraVecuma = new int[maksimalaisVecums];
        for (DeadPerson mirusaisCilveks : mirusieCilveki) {
            nomiraVecuma[mirusaisCilveks.age]++;
        }

//        Saskaitām cik cilvēku vel ir dzīvi noteikta vecuma
        int[] dzivsVecuma = new int[maksimalaisVecums];
        dzivsVecuma[0] = mirusieCilveki.size();

        for (int j = 1; j < maksimalaisVecums; j++) {
            dzivsVecuma[j] = dzivsVecuma[j - 1] - nomiraVecuma[j - 1];
        }

//        Aprēķinam nosacīto varbūtību, ka cilvēks nomirs "navesVecums" gados, ja nodzīvoja jau "nodzivojaGadus" gadus
        double[][] nosacitaVarbutibaNomirtVecuma = new double[maksimalaisVecums][maksimalaisVecums];

        for (int nodzivojaGadus = 0; nodzivojaGadus < maksimalaisVecums; nodzivojaGadus++) {
            double nl = dzivsVecuma[nodzivojaGadus];
            for (int navesVecums = nodzivojaGadus; navesVecums < maksimalaisVecums; navesVecums++) {
                double nv = nomiraVecuma[navesVecums];
                nosacitaVarbutibaNomirtVecuma[nodzivojaGadus][navesVecums] = nv / nl;
            }
        }

//        Aprēķinam vidējo vērtību, mūsu gadījumā tas ir katra gada pusgads
        double[] videjaVertiba = new double[maksimalaisVecums];

        for (int j = 0; j < maksimalaisVecums; j++) {
            double v1 = vecums[j + 1];
            double v = vecums[j];
            videjaVertiba[j] = ((v1 - v) / 2) + v;
        }

//      Aprēķinam vidējo dzīves ilgumu, ka cilvēks ja nodzīvoja "nodzivojaGadus" gadus
        double[] videjaisDzivesIlgumi = new double[maksimalaisVecums];

        for (int nodzivojaGadus = 0; nodzivojaGadus < maksimalaisVecums; nodzivojaGadus++) {
            videjaisDzivesIlgumi[nodzivojaGadus] = 0.0;
            for (int prognozejamaisNavesVecums = nodzivojaGadus; prognozejamaisNavesVecums < maksimalaisVecums; prognozejamaisNavesVecums++) {
                videjaisDzivesIlgumi[nodzivojaGadus] += nosacitaVarbutibaNomirtVecuma[nodzivojaGadus][prognozejamaisNavesVecums]
                        * videjaVertiba[prognozejamaisNavesVecums];
            }
        }

        double[] videjaisDzivesIlgumiVeselosGados = new double[maksimalaisVecums];
        for (int i = 0; i < maksimalaisVecums; i++) {
            videjaisDzivesIlgumiVeselosGados[i] = Math.round(videjaisDzivesIlgumi[i]);
        }

//      Noapaļojam līdz veselam gadam
        System.out.println("Vidējais dzīves ilgums:");
        int i = 0;
        for (double videjaisDzivesIlgums : videjaisDzivesIlgumiVeselosGados
        ) {
            System.out.println("ja cilvēks nodzīvoja gadus " + i + ", tad prognozējamais dzīves ilgums ir " + videjaisDzivesIlgums);
            i++;
        }

    }
}

