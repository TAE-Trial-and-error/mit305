package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TotalController {
    @GetMapping("total")
    public String total() {
        return "total";
    }
}
