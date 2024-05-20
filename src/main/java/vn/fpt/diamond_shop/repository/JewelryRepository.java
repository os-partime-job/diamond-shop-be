package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.fpt.diamond_shop.model.Jewelry;
import vn.fpt.diamond_shop.response.GetDetailJewelryResponse;

@Repository
@Transactional
public interface JewelryRepository extends JpaRepository<Jewelry, Long> {

//    @Query(value = "select new vn.fpt.diamond_shop.response.GetDetailJewelryResponse("+
//            "j.id as id_jewelry,\n" +
//            "j.name as jewelry_title,\n" +
//            "j.jewelryCode as jewelry_code,\n" +
//            "jt.jewelryTypeName as jewelry_type,\n" +
//            "jt.id  as jewelry_type_id,\n" +
//            "j.quantity as quantity,\n" +
//            "j.materialPrices as price,\n" +
//            "j.createdAt as created_at,\n" +
//            "j.createdBy as created_by,\n" +
//            "j.updatedAt as updated_at,\n" +
//            "j.updatedBy as updated_by,\n" +
//            "j.description as description,\n" +
//            "j.imageId as imageId,\n" +
//            "j.typeEnum as type_enum,\n" +
//            "d.id as diamond_id \n" +
//            ") FROM Jewelry as j left join  JewelryType as jt on (j.jewelryTypeId = jt.id) left join  Diamond as d on (j.idDiamond  = d.id) "+
//            " WHERE 1 = 1 and j.id = :id ")
////            "WHERE 1 = 1 and j.id = 3")
//    GetDetailJewelryResponse getDetailJewelry(Long id);
}