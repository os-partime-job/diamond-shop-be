package vn.fpt.diamond_shop.service;

import org.springframework.web.multipart.MultipartFile;
import vn.fpt.diamond_shop.model.Jewelry;
import vn.fpt.diamond_shop.model.JewelryType;
import vn.fpt.diamond_shop.request.CreateDiamondRequest;
import vn.fpt.diamond_shop.request.GetListJewelryRequest;
import vn.fpt.diamond_shop.response.GetDetailJewelryResponse;

import java.util.List;

public interface JewelryService {
    List<Jewelry> jewelries(GetListJewelryRequest request);

    GetDetailJewelryResponse detailJewelry(Long id);

    List<JewelryType> jewelryType();

    boolean createJewelry(CreateDiamondRequest request);
}
