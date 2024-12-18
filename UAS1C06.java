// Nama      : Daffa Putra Prasetya
// NIM       : 244107060088
// No Absen  : 6

import java.util.Scanner;

public class UAS1C06 {
    static int NOMOR_ABSEN6 = 6;
    static int DUA_DIGIT_NIM6 = 88;
    static String NAMA_SAYA6 = "Daffa Putra Prasetya";

    static Scanner input6 = new Scanner(System.in);
    static String[] namaTim6;
    static int[][] skorTim6;

    public static void main(String[] args) {
        int opsi6;

        do {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Input Data Skor Tim");
            System.out.println("2. Tampilkan Tabel Skor");
            System.out.println("3. Tentukan Juara");
            System.out.println("4. Keluar");
            
            System.out.print("Pilih opsi: ");
            opsi6 = input6.nextInt();
            input6.nextLine();

            switch (opsi6) {
                case 1:
                    inputDataSkorTim();
                    break;
                case 2:
                    tampilkanTabelSkor();
                    break;
                case 3:
                    tentukanJuara();
                    break;
                case 4:
                    System.out.println("Terima kasih sudah menggunakan layanan kami!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (opsi6 != 4);
    }

    public static void inputDataSkorTim() {
        int jumlahTim6 =  (DUA_DIGIT_NIM6 % 3) + 4;
        namaTim6 = new String[jumlahTim6];
        skorTim6 = new int[jumlahTim6][2];

        for (int i = 0; i < jumlahTim6; i++) {
            System.out.print("\nMasukkan nama tim ke-" + (i+1) + ": ");
            String nama6 = input6.nextLine();
            
            boolean unik6 = true;
            for (int j = 0; j < i; j++) {
                if (nama6.toLowerCase().equals(namaTim6[j].toLowerCase())) {
                    unik6 = false;
                    break;
                }
            }
            
            if (!unik6) {
                System.out.println("Nama tim sudah ada!");
                i--; 
                continue;
            }
            
            namaTim6[i] = nama6;

            while (true) {
                System.out.print("Masukkan skor " + nama6 + " untuk Level 1: ");
                int skor6 = input6.nextInt();
                if (skor6 < 0) {
                    System.out.println("Skor harus bilangan positif atau nol!");
                    continue;
                }
                skorTim6[i][0] = (skor6 < 35) ? 0 : skor6;
                break;
            }

            while (true) {
                System.out.print("Masukkan skor " + nama6 + " untuk Level 2: ");
                int skor6 = input6.nextInt();
                if (skor6 < 0) {
                    System.out.println("Skor harus bilangan positif atau nol!");
                    continue;
                }
                skorTim6[i][1] = skor6;
                break;
            }
            input6.nextLine();
        }
    }

    public static void tampilkanTabelSkor() {
        if (namaTim6 == null || namaTim6.length == 0) {
            System.out.println("\nBelum ada data tim yang diinput!");
            return;
        }

        System.out.println("\nTabel Skor Turnamen");
        System.out.printf("%-15s %-10s %-10s %-10s%n", "Nama Tim", "Level 1", "Level 2", "Total Skor");
        
        for (int i = 0; i < namaTim6.length; i++) {
            int totalSkor6 = skorTim6[i][0] + skorTim6[i][1];
            
            if (totalSkor6 % 2 == 0) {
                totalSkor6 -= 15;
            }
            
            if (skorTim6[i][0] > 50 && skorTim6[i][1] > 50) {
                totalSkor6 += NOMOR_ABSEN6; 
            }
            
            System.out.printf("%-15s %-10d %-10d %-10d%n", 
                namaTim6[i], 
                skorTim6[i][0], 
                skorTim6[i][1], 
                totalSkor6);
        }
    }

    public static void tentukanJuara() {
        if (namaTim6 == null || namaTim6.length == 0) {
            System.out.println("\nBelum ada data tim yang diinput!");
            return;
        }

        int[] totalSkor6 = new int[namaTim6.length];
        int skorMaksimal6 = 0;
        
        for (int i = 0; i < namaTim6.length; i++) {
            totalSkor6[i] = skorTim6[i][0] + skorTim6[i][1];
            
            if (totalSkor6[i] % 2 == 0) {
                totalSkor6[i] -= 15;
            }
            if (skorTim6[i][0] > 50 && skorTim6[i][1] > 50) {
                totalSkor6[i] += 6;
            }
            
            if (totalSkor6[i] > skorMaksimal6) {
                skorMaksimal6 = totalSkor6[i];
            }
        }

        String timTerbaik6 = "";
        int skorLevel2Maksimal6 = 0;

        for (int i = 0; i < namaTim6.length; i++) {
            if (totalSkor6[i] == skorMaksimal6) {
                if (timTerbaik6.equals("")) {
                    timTerbaik6 = namaTim6[i];
                    skorLevel2Maksimal6 = skorTim6[i][1];
                } else {
                    if (skorTim6[i][1] > skorLevel2Maksimal6) {
                        timTerbaik6 = namaTim6[i];
                        skorLevel2Maksimal6 = skorTim6[i][1];
                    } else if (skorTim6[i][1] == skorLevel2Maksimal6) {
                        System.out.println("Turnamen berakhir seri. Tim terbaik adalah " + NAMA_SAYA6);
                        return;
                    }
                }
            }
        }

        System.out.println("Selamat kepada Tim " + timTerbaik6 + " yang telah memenangkan kompetisi");
    }
}