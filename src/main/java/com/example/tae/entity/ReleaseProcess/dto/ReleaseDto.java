package com.example.tae.entity.ReleaseProcess.dto;

import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReleaseDto {
    private String productName;
    private int product_code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime departureDate;
    private String texture;
    private int weight;
    private int height;
    private int length;
    private int contract_pay;
    private int release;
    private int store;
    private int existence;
    private int existence_price;
    private int procurementPlan_code;


    public ReleaseDto releaseProcessDTO(ReleaseProcess releaseProcess, ProductInformationRegistration productInformationRegistration, String productName, int contract_pay, Existence existenceInfo, int procurementPlan_code) {
       int existence = existenceInfo.getReleaseCNT();
       int existence_price = existence * contract_pay;
        return ReleaseDto.builder()
                .productName(productName)
                .product_code(productInformationRegistration.getProduct_code())
                .departureDate(releaseProcess.getRegDate())
                .texture(productInformationRegistration.getTexture())
                .weight(productInformationRegistration.getWeight())
                .height(productInformationRegistration.getHeight())
                .length(productInformationRegistration.getLength())
                .contract_pay(contract_pay)
                .release(releaseProcess.getReleaseCNT())
                .existence(existence)
                .existence_price(existence_price)
                .procurementPlan_code(procurementPlan_code)
                .build();
    }
}
