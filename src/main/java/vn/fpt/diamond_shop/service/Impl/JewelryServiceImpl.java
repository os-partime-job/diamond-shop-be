package vn.fpt.diamond_shop.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.fpt.diamond_shop.model.Jewelry;
import vn.fpt.diamond_shop.model.JewelryType;
import vn.fpt.diamond_shop.repository.JewelryRepository;
import vn.fpt.diamond_shop.repository.JewelryTypeRepository;
import vn.fpt.diamond_shop.request.CreateDiamondRequest;
import vn.fpt.diamond_shop.request.GetListJewelryRequest;
import vn.fpt.diamond_shop.response.GetDetailJewelryResponse;
import vn.fpt.diamond_shop.response.ImageInformation;
import vn.fpt.diamond_shop.service.ImageService;
import vn.fpt.diamond_shop.service.JewelryService;

import java.sql.Date;
import java.util.List;

@Slf4j
@Service
public class JewelryServiceImpl implements JewelryService {
    @Autowired
    private JewelryRepository jewelryRepository;

    @Autowired
    private JewelryTypeRepository jewelryTypeRepository;


    private static String JEWELRY_CODE_DEFAULT = "DMS_";
    private static Integer ACTIVE_VALUE = 1;

    @Autowired
    private ImageServiceImpl imageService;

    @Override
    public List<Jewelry> jewelries(GetListJewelryRequest request) {
        return jewelryRepository.findAll();
    }

    @Override
    public GetDetailJewelryResponse detailJewelry(Long id) {
        return null;
//        return jewelryRepository.getDetailJewelry(id);
    }

    @Override
    public List<JewelryType> jewelryType() {
        return jewelryTypeRepository.findAll();
    }

    @Override
    public boolean createJewelry(CreateDiamondRequest request) {
        ImageInformation imageInformation = imageService.push(request.getMultipartFile());

        Jewelry jewelry = new Jewelry();
        BeanUtils.copyProperties(request, jewelry);
        jewelry.setJewelryCode(jewelryCode());
        jewelry.setCreatedBy("Khoa Tran");
        jewelry.setJewelryTypeId(request.getJewelryTypeId());
        jewelry.setIdGuide(1L);
        jewelry.setImageId(imageInformation.getImageId());
        jewelry.setIsActive(ACTIVE_VALUE);
        java.util.Date date = new java.util.Date();
        jewelry.setCreatedAt(new Date(date.getTime()));
        jewelryRepository.save(jewelry);
        return true;
    }

    private String jewelryCode() {
        long count = jewelryRepository.count();
        return JEWELRY_CODE_DEFAULT + count;
    }

}
