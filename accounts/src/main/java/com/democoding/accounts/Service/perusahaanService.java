package com.democoding.accounts.Service;

import com.democoding.accounts.Entity.SalesReport;
import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class perusahaanService {
    public void reportPerusahaan() {
        try{
        String tanggalPenjualan = "2023-11-27";
        int jumlahQty = 1000;
        double harga = 20000;
        String namaSopir = "Sunardi";
        String alamatTujuan = "Bengkulu";
        double totalHarga = jumlahQty * harga;
        if(namaSopir.equals("") && alamatTujuan.equals("") && jumlahQty == 0 && totalHarga == 0){
            System.out.println(tanggalPenjualan);
            System.out.println("namaSopir :"+namaSopir);
            System.out.println("alamat :"+alamatTujuan);
            System.out.println("jumlahQty :"+jumlahQty);
            System.out.println("totalHarga :"+totalHarga);
        }
        }catch (Exception e){
            throw new ResourceNotAcceptableException("Cant Delivery Semen");
        }
    }

}
