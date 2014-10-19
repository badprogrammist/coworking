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
public class OptionListData extends DTO {
    private String title;

    public OptionListData(Long id,String title) {
        super(id);
        this.title = title;
    }

    public OptionListData(String title, Long id) {
        super(id);
        this.title = title;
    }
    
    public OptionListData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
