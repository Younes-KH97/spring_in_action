package com.taco_cloud.api;

import com.taco_cloud.data.OrderRepository;
import com.taco_cloud.domain.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
@CrossOrigin("/**")
public class TacoOrderController {
    private OrderRepository orderRepository;

    @Autowired
    public TacoOrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public Iterable<TacoOrder> getAllOrders(){
        PageRequest pageRequest = PageRequest.of(
                0, 12, Sort.by("createdAt").descending()
        );
        return orderRepository.findAll(pageRequest).getContent();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder addNewTacoOrder(@RequestBody TacoOrder tacoOrder){
        return orderRepository.save(tacoOrder);
    }

    @PutMapping
    public TacoOrder updateOrder(@PathVariable Long id, @RequestBody TacoOrder tacoOrder){
        TacoOrder tacoOrderIn = orderRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException("Order not found"));
        tacoOrderIn.setId(id);
        return orderRepository.save(tacoOrderIn);
    }


    @PatchMapping(path="/{orderId}", consumes="application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId,
                                @RequestBody TacoOrder patch) {

        TacoOrder order = orderRepository.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long order_id){
        try{
            orderRepository.deleteById(order_id);
        }
        catch (EmptyResultDataAccessException e){};
    }
}
