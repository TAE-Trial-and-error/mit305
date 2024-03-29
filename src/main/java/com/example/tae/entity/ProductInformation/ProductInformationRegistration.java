package com.example.tae.entity.ProductInformation;

import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductForProject.ProductForProject;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductInformationRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_code; //품목코드

    @ManyToOne // 소분류
    Part part;

    private String product_name; //품목명

    private String product_abbreviation; //약칭

    private String texture; //재질

    private int width;
    private int length;
    private int height;
    private int weight;

    private String image_name;
}
