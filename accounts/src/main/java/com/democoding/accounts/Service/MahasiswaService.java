package com.democoding.accounts.Service;

import com.democoding.accounts.Dto.*;
import com.democoding.accounts.Entity.Employee;
import com.democoding.accounts.Entity.Mahasiswa;
import com.democoding.accounts.Entity.Matakuliah;
import com.democoding.accounts.Entity.Nilai;
import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import com.democoding.accounts.Repository.MahasiswaRepository;
import com.democoding.accounts.Repository.MatakuliahRepository;
import com.democoding.accounts.Repository.NilaiRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MahasiswaService {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    @Autowired
    private NilaiRepository nilaiRepository;
    @Autowired
    private MatakuliahRepository matakuliahRepository;

    public MahasiswaDto listMahasiswa(String nim) {
        nim = nim.toLowerCase();
        MahasiswaDto mahasiswaDto = new MahasiswaDto();
        Mahasiswa mahasiswa = mahasiswaRepository.findMahasiswaByNim(nim);
        mahasiswaDto.setNama(mahasiswa.getNama());
        mahasiswaDto.setNim(mahasiswa.getNim());
        mahasiswaDto.setAge(mahasiswa.getAge());

        List<Nilai> nilaiList = nilaiRepository.findNilaiByIdMahasiswa(mahasiswa.getId().intValue());
        List<NilaiDto> nilaiDtos = new ArrayList<>();
        for (Nilai nilai : nilaiList) {
            NilaiDto nilaiDto = new NilaiDto();
            Matakuliah matakuliah = matakuliahRepository.findMatakuliahById(nilai.getIdMatakuliah().longValue());
            nilaiDto.setMatakuliah(matakuliah.getNama());
            nilaiDto.setSks(matakuliah.getSks());
            nilaiDto.setNilai(nilai.getNilai());
            nilaiDtos.add(nilaiDto);
        }
        mahasiswaDto.setNilai(nilaiDtos);
        return mahasiswaDto;
    }

    @Transactional
    public MessageDto inputDataMahasiswa(RequestMahasiswaDto request) {
        Mahasiswa mahasiswa = new Mahasiswa();
        MessageDto messageDto = new MessageDto();
        try {
            mahasiswa.setNama(request.getNama());
            Mahasiswa mahasiswaNim = mahasiswaRepository.findMahasiswaByNim(request.getNim());
            if (mahasiswaNim == null) {
                mahasiswa.setNim(request.getNim());
            } else {
                throw new ResourceNotAcceptableException("Nim Sudah Ada");
            }
            mahasiswa.setAge(request.getAge());
            mahasiswaRepository.save(mahasiswa);

        } catch (Exception e) {
            throw new ResourceNotAcceptableException("Failed Save Data Mahasiswa");
        }
        messageDto.setMessage("Success Save Data Mahasiswa");
        return messageDto;
    }

    @Transactional
    public MessageDto IpkMahasiswa(UpdateIpkRequestDto request) {
        MessageDto messageDto = new MessageDto();
        String nim = request.getNim().toLowerCase();
        try {
            Mahasiswa mahasiswa = mahasiswaRepository.findMahasiswaByNim(nim);
            mahasiswa.setIpk(request.getIpk());
            mahasiswaRepository.save(mahasiswa);

        } catch (Exception e) {
            throw new ResourceNotAcceptableException("IPK MAHASISWA GAGAL DI DAPATKAN");
        }
        messageDto.setMessage("IPK DENGAN NIM :" + request.getNim() + " Mendapatkan IPK " + request.getIpk());
        return messageDto;
    }
}
