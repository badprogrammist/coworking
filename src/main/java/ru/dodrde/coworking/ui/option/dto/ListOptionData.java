/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.option.dto;

import java.math.BigDecimal;
import ru.dodrde.coworking.ui.DTO;

/**
 *
 * @author Ильдар
 */
public class ListOptionData extends DTO {
    private String title;
    private String price;

    public ListOptionData(Long id,String title) {
        super(id);
        this.title = title;
    }

    public ListOptionData(String title, String price, Long id) {
        super(id);
        this.title = title;
        this.price = price;
    }
    
    public BigDecimal generatePrice() {
        return new BigDecimal(getPrice());
    }

    public ListOptionData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}
