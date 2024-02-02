package com.example.tae.controller;

import com.example.tae.entity.Order.dto.OrderDTO;

import com.example.tae.entity.dto.ImageDTO;
import com.example.tae.service.PurchaseService.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    /*발주 아닌 모든 조달 계획 정보 가져오기*/
    @GetMapping("/orderList")
    public String orderList(Model model) {
        model.addAttribute("oList", orderService.getAllOrders());
        return "orderList";
    }


    @GetMapping("orderInspect")
    public String orderInspect(@RequestParam("productCode") int productCode, @RequestParam("procurementPlanCode") int procurementPlanCode,@RequestParam("orderIndex") int orderIndex,Model model) {
        model.addAttribute("orderInspect", orderService.getOrderInspectData(productCode, procurementPlanCode,orderIndex));
        return "orderInspect";
    }

    @GetMapping("orderRegister")
    public String orderRegister(Model model) {
    List<OrderDTO> oList = orderService.oListSend();
    model.addAttribute("oList", oList);
    return "orderRegister";
    }

    @PostMapping("orderRegisterData")
    public String orderRegisterData(@RequestBody OrderDTO orderDTO) {
        orderService.orderRegister(orderDTO.getProcurementPlanCode());
        return "orderRegister";
    }

    @DeleteMapping("/cancelOrder/{procurementplan_code}")
    @ResponseBody
    public ResponseEntity<?> cancelOrder(@PathVariable int procurementplan_code) {
        orderService.cancelOrder(procurementplan_code);
        return ResponseEntity.ok().body(Map.of("msg",procurementplan_code+"번 조달 계획 발주서 취소 완료"));
    }

    @GetMapping("order-list-with-date")
    @ResponseBody
    public ResponseEntity<?> getOrderListWithDate(@RequestParam("date1") String stringDate1, @RequestParam("date2") String stringDate2) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime date1 = LocalDateTime.parse(stringDate1+ " 00:00", format);
        LocalDateTime date2 = LocalDateTime.parse(stringDate2+ " 00:00", format);

        List<OrderDTO> oList = orderService.getOrderListWithDate(date1, date2);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("oList", oList));
    }

    @PutMapping("putOrderNum")
    public ResponseEntity<?> putOrderPlan(@RequestBody OrderDTO orderDTO) {
        orderService.putOrderPlan(orderDTO.getProcurementPlanCode(), orderDTO.getNum());
        return ResponseEntity.ok().body(Map.of("msg","발주서 수정을 성공하였습니다."));
    }

    @GetMapping("open-order/{procurementPlanCode}")
    public String openOrder(@PathVariable int procurementPlanCode, Model model) {
       OrderDTO order =  orderService.getOrderPopup(procurementPlanCode);
       model.addAttribute("order", order);
        return "orderListPopup";
    }

    @PostMapping("/order-imageURl")
    public void orderUpload(@RequestBody ImageDTO imageDTO){
        orderService.orderUpload(imageDTO);
    }
}
