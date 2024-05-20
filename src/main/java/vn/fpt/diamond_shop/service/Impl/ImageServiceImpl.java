package vn.fpt.diamond_shop.service.Impl;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.fpt.diamond_shop.model.Image;
import vn.fpt.diamond_shop.repository.ImageRepository;
import vn.fpt.diamond_shop.response.ImageInformation;
import vn.fpt.diamond_shop.service.ImageService;
import vn.fpt.diamond_shop.util.UUIDUtil;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${minio.host}")
    private String host;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String scretKey;
    private final String bucket = "diamondshop";
    private MinioClient minioClient;

    @Autowired
    private ImageRepository imageRepository;

    @PostConstruct
    void setup() {
        minioClient = MinioClient.builder().endpoint(host).credentials(accessKey, scretKey).build();
    }

    @Override
    public ImageInformation push(MultipartFile multipartFile) {
        try {
            ImageInformation imageInformation = new ImageInformation();
            String uuid = UUIDUtil.generateUUID();
            String imageName = multipartFile.getOriginalFilename() + uuid;
            minioClient.putObject(PutObjectArgs.builder().bucket(bucket).stream(multipartFile.getInputStream(), multipartFile.getSize(), -1).object(imageName).contentType(multipartFile.getContentType()).build());
            String feImgUrl = get(imageName);
            imageInformation.setImageName(imageName);
            imageInformation.setUrl(feImgUrl);
            imageInformation.setImageId(imageRepository.save(new Image(imageName, feImgUrl)).getId());
            return imageInformation;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String get(Long imageId) {
        Optional<Image> image = imageRepository.findById(imageId);
        if (!image.isPresent()) {
            throw new RuntimeException("Image not found");
        }
        return get(image.get().getImageName());
    }

    private String get(String imageFile) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucket).object(imageFile).method(Method.GET).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void delete(Long imageId) {
        try {
            Optional<Image> image = imageRepository.findById(imageId);
            if (!image.isPresent()) {
                throw new RuntimeException("Image not found");
            }
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(image.get().getImageName() + image.get().getId()).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws Exception {
//        String path = "C:\\Users\\Thanh Dev\\Desktop\\diamond-shop-api\\src\\main\\resources\\images\\img.png";
//        InputStream inputStream = Files.newInputStream(Paths.get(path));
//        MinioClient minioClient1 = MinioClient.builder().endpoint("http://178.128.111.191:9000").credentials("LhvzlYoqSbl1Go3U2tRF", "muIyi1QKM27OXxLofmCUk4C8cR82Qe9N7Ess2M6R").build();
//        minioClient1.putObject(PutObjectArgs.builder().contentType("images/png").bucket("diamondshop").stream(inputStream, Files.size(Paths.get(path)), -1).object("test1.png").build());
////        System.out.println(minioClient1.getObject(GetObjectArgs.builder().bucket("diamondshop").object("test.png").build()).readAllBytes().length);
//    }
}
