package com.example.tae.repository;

import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReleaseRepository extends JpaRepository<ReleaseProcess, Integer> {

    ReleaseProcess findTop1ByOrderByModDateDesc();

    @Query("select rp from ReleaseProcess rp " +
        " where  rp.regDate between :date1 and :date2 " +
        "order by rp.regDate")
    List<ReleaseProcess> findByReleaseProcessWithRegDate(@Param("date1") Date date1, @Param("date2") Date date2);

    @Query(value = "select * from tae.release_process " +
            "where procurement_plan_procurementplan_code = :pcmPlanCode " +
            "order by mod_date desc limit 1", nativeQuery = true
    )
    Optional<ReleaseProcess> findTop1ByOrderByModDateDesc(@Param("pcmPlanCode") int pcmPlanCode);
}
