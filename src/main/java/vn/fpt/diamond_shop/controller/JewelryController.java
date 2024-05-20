package vn.fpt.diamond_shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.fpt.diamond_shop.constants.UrlConstants;
import vn.fpt.diamond_shop.request.CreateDiamondRequest;
import vn.fpt.diamond_shop.request.GetDetailJewelryRequest;
import vn.fpt.diamond_shop.request.GetListJewelryRequest;
import vn.fpt.diamond_shop.service.ImageService;
import vn.fpt.diamond_shop.service.Impl.ImageServiceImpl;
import vn.fpt.diamond_shop.service.JewelryService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(UrlConstants.BASIC_JEWELRY_URL)
public class JewelryController extends BaseController {

    @Autowired
    private JewelryService jewelryService;

    @PostMapping("list")
    public ResponseEntity<Object> list(@Valid @RequestBody GetListJewelryRequest request) {
        return ok(jewelryService.jewelries(request), null);
    }

    @PostMapping("detail")
    public ResponseEntity<Object> detail(@Valid @RequestBody GetDetailJewelryRequest request) {
        return ok(jewelryService.detailJewelry(request.getIdJewelry()), request.getRequestId());
    }

    @GetMapping("jewelry_type")
    public ResponseEntity<Object> type() {
        return ok(jewelryService.jewelryType(), null);
    }

    @PostMapping(value = "create", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> create(@RequestPart("request") CreateDiamondRequest request,
                                         @RequestPart("image") MultipartFile file) {
        request.setMultipartFile(file);
        return ok(jewelryService.createJewelry(request), null);
    }
//    @PostMapping("upload/file")
//    public ResponseEntity<Object> uploadFile(@RequestParam("image") MultipartFile file) {
//        return ok(imageService.push(file), null);
//    }
}
