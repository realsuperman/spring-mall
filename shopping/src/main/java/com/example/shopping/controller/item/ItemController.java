package com.example.shopping.controller.item;

import com.example.shopping.domain.item.Item;
import com.example.shopping.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public String insertItem(@Valid @ModelAttribute Item item, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            model.addAttribute("errorMessages", errorMessages);
            return "/admin";
        }

        item.setItemImagePath(item.getImage1Name()+";"+item.getImage2Name()+";"+item.getImage3Name()+";" +item.getImage4Name()+";"+item.getImage5Name()+";"+item.getImage6Name());
        itemService.insertItem(item, item.getItemQuantity());
        return "redirect:/admin";
    }
}
