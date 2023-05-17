package com.forbiddenkey.resources;

import com.forbiddenkey.dto.banner.BannerDTO;
import com.forbiddenkey.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/banners")
public class BannerResource {

    @Autowired
    private BannerService bannerService;

    @GetMapping
    public ResponseEntity<List<BannerDTO>> findAll(){
        List<BannerDTO> list = bannerService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
