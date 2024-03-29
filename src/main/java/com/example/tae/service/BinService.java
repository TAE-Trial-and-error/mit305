package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.entity.StatusManagement.StatusManagementDTO;

import java.util.Date;
import java.util.List;

/**
 * 현황 관리 에 관한 서비스및 입고처리에 관한 서비스
 */
public interface BinService {
    //----------------------------------현황관리------------------------------------------
    //현황관리 리포트 날짜 요청을 받으면 리스트
    List<ReceivingProcessingDTO> procurementPlanListbyStatement(Date start ,Date end) throws Exception;
    
    //날짜별로 발주상태로 묶어 각각  몇개인지
    List<StatusManagementDTO> statusManagementDTOList(Date start , Date end);

    //----------------------------------입고처리---------------------------------------------
    //입고처리 홈페이지 요청시 조달계획의 품목 리스트 불러오기
    List<ReceivingProcessingDTO> procurementPlanList();


    //입고처리 홈페이지에서 검색 요청시 검색된 조달계획의 품목 리스트 불러오기
    List<ReceivingProcessingDTO>  search (String inputData, int searchStatenum);//품목명으로 검수 완료된 리스트 불러오기


    //해당 품목에대해 입고처리시 쿼리로 입고수량 데이터 베이스에 넣어어주는것  받는갑 조달계획 코드 , // 추후에입고수량, 페이지 상태
    List<ReceivingProcessingDTO> ReceivingProcessStore(int procurementplan_code, int store ,int pageState,String formInputData);
}
