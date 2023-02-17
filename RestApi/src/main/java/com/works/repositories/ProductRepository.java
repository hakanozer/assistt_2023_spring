package com.works.repositories;

import com.works.entities.Product;
import com.works.ifeings.IAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select admin.AID, ADMIN.USERNAME, r.NAME from ADMIN\n" +
            "    inner join ADMIN_ROLES AR on ADMIN.AID = AR.ADMINS_AID\n" +
            "    inner join ROLE R on R.RID = AR.ROLES_RID", nativeQuery = true)
    List<IAdmin> allAdmin();


}