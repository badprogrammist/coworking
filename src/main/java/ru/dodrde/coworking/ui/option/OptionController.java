/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.option;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dodrde.coworking.application.OptionService;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.option.OptionDescription;
import ru.dodrde.coworking.ui.option.dto.ListOptionData;
import ru.dodrde.coworking.ui.option.dto.NewOptionData;
import ru.dodrde.coworking.ui.option.dto.ViewOptionData;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/option")
public class OptionController {
    
    @Inject
    private OptionService optionService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ListOptionData> getOptions() {
        List<ListOptionData> options = new ArrayList<>();
        for(Option option : optionService.getAll()) {
            options.add(new ListOptionData(option.getId(), option.getDescription().getTitle()));
        }
        return options;
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addOption(@RequestBody NewOptionData data) {
        OptionDescription description = new OptionDescription(data.getTitle(), data.getDescription());
        optionService.createOption(description);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ViewOptionData getOption(@PathVariable("id") Long id) {
        Option option = optionService.get(id);
        if(option != null) {
            ViewOptionData data = new ViewOptionData(option.getId(), option.getDescription().getTitle(),option.getDescription().getDescription());
            return data;
        }
        return new ViewOptionData();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteOption(@PathVariable("id") Long id) {
        optionService.remove(id);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateOption(@RequestBody ViewOptionData data) {
        Option option = optionService.get(data.getId());
        if(option != null) {
            option.getDescription().setTitle(data.getTitle());
            option.getDescription().setDescription(data.getDescription());
            optionService.update(option);
        }
    }
}
