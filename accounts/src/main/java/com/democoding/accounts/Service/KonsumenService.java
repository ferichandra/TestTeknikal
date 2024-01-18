package com.democoding.accounts.Service;

import com.democoding.accounts.Dto.AnyPaginationDto;
import com.democoding.accounts.Dto.EmployeeDto;
import com.democoding.accounts.Dto.KonsumenRequestDto;
import com.democoding.accounts.Dto.MessageDto;
import com.democoding.accounts.Entity.Employee;
import com.democoding.accounts.Entity.Konsumen;
import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import com.democoding.accounts.Exception.ResourceNotFoundException;
import com.democoding.accounts.Repository.KonsumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KonsumenService {
    @Autowired
    private KonsumenRepository konsumenRepository;


    public AnyPaginationDto viewKonsumen(int pageNo, int pageSize, String name) {
        AnyPaginationDto result = new AnyPaginationDto();
        Pageable page = PageRequest.of(pageNo, pageSize);
        name = name.toLowerCase();
        Page<Konsumen> konsumenPage = konsumenRepository.findallKonsumenPagination(page, name);
        List<Konsumen> konsumenList = new ArrayList<>();
        for (Konsumen konsumen : konsumenPage) {
            konsumenList.add(konsumen);
        }
        result.setData(konsumenList);
        result.setCurrentPage(konsumenPage.getNumber());
        result.setTotalRecord(konsumenPage.getTotalElements());
        result.setTotalPage(konsumenPage.getTotalPages());
        return result;
    }

    public MessageDto createKonsumen(KonsumenRequestDto konsumenRequestDto) {
        Konsumen konsumen = new Konsumen();
        MessageDto messageDto = new MessageDto();
        try {
            konsumen.setNama(konsumenRequestDto.getName());
            konsumen.setAlamat(konsumenRequestDto.getAlamat());
            konsumen.setKota(konsumenRequestDto.getKota());
            konsumen.setProvinsi(konsumenRequestDto.getProvinsi());
            konsumen.setTglRegistrasi(konsumenRequestDto.getTglRegistrasi());
            if (konsumenRequestDto.getStatus() == 0) {
                konsumen.setStatus("Aktif");
            } else {
                konsumen.setStatus("Non-aktif");
            }
            konsumenRepository.save(konsumen);
        } catch (Exception e) {
            throw new ResourceNotAcceptableException("Failed Create New Konsumen");
        }
        messageDto.setMessage("Success Create New Konsumen");
        return messageDto;
    }

    public MessageDto updateKonsumen(KonsumenRequestDto konsumenRequestDto, Long id) {
        MessageDto messageDto = new MessageDto();
        try {
            Konsumen konsumen = konsumenRepository.findByIdAAndDeleteAtIsNull(id);
            konsumen.setNama(konsumenRequestDto.getName());
            konsumen.setAlamat(konsumenRequestDto.getAlamat());
            konsumen.setKota(konsumenRequestDto.getKota());
            konsumen.setProvinsi(konsumenRequestDto.getProvinsi());
            konsumen.setTglRegistrasi(konsumenRequestDto.getTglRegistrasi());
            if (konsumenRequestDto.getStatus() == 0) {
                konsumen.setStatus("Aktif");
            } else {
                konsumen.setStatus("Non-aktif");
            }
            konsumenRepository.save(konsumen);
        } catch (Exception e) {
            throw new ResourceNotAcceptableException("Failed Update Konsumen");
        }
        messageDto.setMessage("Success Update Konsumen");
        return messageDto;
    }

    public MessageDto deleteKonsumen(Long id) {
        Konsumen konsumen = konsumenRepository.findByIdAAndDeleteAtIsNull(id);
        MessageDto messageDto = new MessageDto();
        try {
            konsumen.setDeleteAt(new Date());
            konsumenRepository.save(konsumen);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Data Not Found");
        }
        messageDto.setMessage("Success Delete Konsumen");
        return messageDto;
    }
}
