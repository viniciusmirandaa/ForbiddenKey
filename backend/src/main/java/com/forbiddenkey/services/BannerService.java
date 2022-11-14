package com.forbiddenkey.services;

import com.forbiddenkey.dto.banner.BannerDTO;
import com.forbiddenkey.entities.Banner;
import com.forbiddenkey.repositories.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public List<BannerDTO> findAll(){
        List<Banner> list = bannerRepository.findAll();
        return list.stream().map(BannerDTO::new).collect(Collectors.toList());
    }
}
